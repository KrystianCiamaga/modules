package kc.domain.prices;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.*;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Stream;

public class PriceUpdater {


    private static int numberOfThreads=2;
    private static int productPriceUpdatesInvervalMs=100;
    private static int numberOfProducts=1000;

    public static void main(String[] args) throws InterruptedException {


        PriceUpdater priceUpdater = new PriceUpdater();
        priceUpdater.readFromFile();

        LocalKafkaProducer localKafkaProducer = new LocalKafkaProducer();

        ScheduledExecutorService service = Executors.newScheduledThreadPool(numberOfThreads);


        service.scheduleAtFixedRate(
                priceUpdater.sendToKafkaBroker(localKafkaProducer), 1,productPriceUpdatesInvervalMs, TimeUnit.MILLISECONDS);

        }



    public Runnable sendToKafkaBroker(LocalKafkaProducer producer){

        double percentageOfPriceModification = ThreadLocalRandom.current().nextDouble(-0.1, 0.1);

        return () -> {
            List<ProductPrice> productsToSend = getRandomProducts();

            for(ProductPrice s : productsToSend){

                s.price = s.price * percentageOfPriceModification;

                try {
                    producer.sendMessage(new ObjectMapper().writeValueAsString(s));
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }

            }
        };
    }



    public List<ProductPrice> getRandomProducts(){

        Map<String,Double> productsPrices=readFromFile();

        List<String> keys= new ArrayList<>(productsPrices.keySet());

        List<ProductPrice> randomProducts=new ArrayList<>();


        for(int i=0;i<numberOfProducts;i++){

            String key =  keys.get( ThreadLocalRandom.current().nextInt(keys.size()));

            randomProducts.add(new ProductPrice(key,productsPrices.get(key)));

        }
        return randomProducts;
    }


    public Map<String, Double> readFromFile() {

        Map<String, Double> productsPrices = new HashMap<>();

        InputStream is = getClass().getClassLoader().getResourceAsStream("Products_Prices.txt");

        if(is!=null) {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));

                String line;

                while ((line = reader.readLine()) != null) {

                    String[] s = line.split(" ");
                    productsPrices.put(s[0], Double.valueOf(s[1]));
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return productsPrices;

    }
}
