/*
package kc.domain;


import kc.domain.settings.Producer;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.*;

@Service
@NoArgsConstructor
@EnableScheduling
public class ProductsFileReader {

    private File file;
    private  Random generator = new Random();
    //private final String filePath="/Users/macosmojave/Desktop/products_prices.txt";
    private Map<String, Double> productsPrices;


    @Autowired
  private Producer producer ;

*/
/* @PostConstruct
    public void readFromFile() {

     InputStream is = getClass().getClassLoader().getResourceAsStream("Products_Prices.txt");

        productsPrices = new HashMap<>();

        *//*
*/
/*if(file.exists() && file.length()>0){*//*
*/
/*

            try {
                //FileReader fileReader = new FileReader(String.valueOf(is));
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));

                String line;

                while( (line=reader.readLine()) != null ){

                    String[] s = line.split(" ");
                    productsPrices.put(s[0],Double.valueOf(s[1]));
                }

            }catch (Exception e){

                System.out.println(e.getMessage());
            }
        }

    //}*//*



    @Scheduled(fixedDelay = 1000)
    public void sendToKafkaBroker(){

        List<String> keys= new ArrayList<>(productsPrices.keySet());

        for(int i=0;i<3;i++){

           String key =  keys.get(generator.nextInt(keys.size()));

           productsPrices.put(key, productsPrices.get(key)+20);

           producer.sendMessage(key+" "+productsPrices.get(key).toString());

      }

    }







}
*/
