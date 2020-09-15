package kc.domain.prices;





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
    private static int numberOfMiliseconds=100;
    private static int numberOfProducts=10;
    private Random generator = new Random();
    private static int count = 0;




    public static void main(String[] args) throws InterruptedException {


        PriceUpdater priceUpdater = new PriceUpdater();
        priceUpdater.readFromFile();

        LocalKafkaProducer localKafkaProducer = new LocalKafkaProducer();


        ScheduledExecutorService service = Executors.newScheduledThreadPool(numberOfThreads);




        ScheduledFuture<?> scheduledFuture = service.scheduleWithFixedDelay(
                priceUpdater.sendToKafkaBroker(localKafkaProducer,
                        priceUpdater.getRandomProducts()), 1, numberOfMiliseconds, TimeUnit.MILLISECONDS);





        while (true) {

            if (count == numberOfProducts) {

                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" + PriceUpdater.count);
                scheduledFuture.cancel(true);
                service.shutdown();
                break;
            }


        }



        }



/*
    public Runnable sendToKafkaBroker(LocalKafkaProducer producer,ProductPrice product){

        double leftLimit = -0.1D;
        double rightLimit = 0.1D;
        double generatedDouble = leftLimit + new Random().nextDouble() * (rightLimit - leftLimit);

        return () -> {
                producer.sendMessage(
                        product.id+" "+product.price +(product.price* generatedDouble));
                count++;

        };
    }
*/


    public Runnable sendToKafkaBroker(LocalKafkaProducer producer,List<ProductPrice> products){

        double leftLimit = -0.1D;
        double rightLimit = 0.1D;
        double generatedDouble = leftLimit + new Random().nextDouble() * (rightLimit - leftLimit);


        return () -> {

            for(ProductPrice s : products){

                producer.sendMessage(
                        s.id+" "+s.price +(s.price* generatedDouble));
                count++;

            }
        };
    }


    public List<ProductPrice> getRandomProducts(){

        Map<String,Double> productsPrices=readFromFile();

        List<String> keys= new ArrayList<>(productsPrices.keySet());

        List<ProductPrice> randomProducts=new ArrayList<>();


        for(int i=0;i<numberOfProducts;i++){

            String key =  keys.get(generator.nextInt(keys.size()));

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
