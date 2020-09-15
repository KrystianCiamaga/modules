package kc.domain.prices;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.List;
import java.util.concurrent.*;

public class PriceUpdater {


    private static int numberOfThreads=2;
    private static int numberOfMiliseconds;
    private static int numberOfProducts=10;
    private Random generator = new Random();
    private static int count = 0;

    public List<Long> lista=new ArrayList<>();



    public static void main(String[] args) throws InterruptedException {


        PriceUpdater priceUpdater = new PriceUpdater();
        priceUpdater.readFromFile();

        LocalKafkaProducer localKafkaProducer = new LocalKafkaProducer();



        ScheduledExecutorService service = Executors.newScheduledThreadPool(numberOfThreads);


        Map<String,Double> products = priceUpdater.getRandomProducts();



        ScheduledFuture<?> scheduledFuture = service.scheduleAtFixedRate(
                priceUpdater.sendToKafkaBroker(localKafkaProducer,products), 1, 100, TimeUnit.SECONDS);


     /*   while(true){

            System.out.println("LICZNIK = "+count);
            Thread.sleep(1000);

            if(count >= numberOfProducts){
                System.out.println("Count is 5, cancel the scheduledFuture!");
                scheduledFuture.cancel(true);
                service.shutdown();
                break;
            }*/
        //}
        System.out.println(priceUpdater.lista);



    }



    public Runnable sendToKafkaBroker(LocalKafkaProducer producer,Map<String,Double> products){


        return () -> {

            while(count!=numberOfProducts) {

                for (Map.Entry<String, Double> entry : products.entrySet()) {

                    producer.sendMessage(entry.getKey() + " " + entry.getValue()+"         ");
                    lista.add(Thread.currentThread().getId());


                    count++;

                }
            }

        };
    }


    public Map<String,Double> getRandomProducts(){

        Map<String,Double> productsPrices=readFromFile();

        List<String> keys= new ArrayList<>(productsPrices.keySet());

        Map<String,Double> randomProducts = new HashMap<>();

        for(int i=0;i<numberOfProducts;i++){

            String key =  keys.get(generator.nextInt(keys.size()));

           randomProducts.put(key,productsPrices.get(key));

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
