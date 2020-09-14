package kc.domain;


import kc.domain.settings.ProductsFileReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class SpringBootApplicationn {




    public static void main(String[] args) {

        //prod.readFromFile("/Users/macosmojave/Desktop/products_prices.txt");

        SpringApplication.run(SpringBootApplicationn.class);

    }
}
