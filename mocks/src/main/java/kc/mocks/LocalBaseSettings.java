package kc.mocks;


import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class LocalBaseSettings {


    @Bean
    public static BaseSetting getLocalBaseSettings(){
        BaseSetting baseSetting = new BaseSetting();

        baseSetting.setDatabaseHost("localhost");
        baseSetting.setDatabasePort(9300);

     return baseSetting;
    }

}