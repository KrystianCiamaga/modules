package kc.mocks;


import com.fasterxml.jackson.databind.ser.Serializers;
import kc.domain.SpringBootApplicationn;
import kc.domain.enums.Environment;
import kc.domain.settings.BaseSetting;
import kc.domain.settings.BaseSettingsConfiguration;
import org.elasticsearch.node.NodeValidationException;
import org.junit.jupiter.api.extension.Extension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class LocalPlatform implements Extension {



    @Autowired
     BaseSettingsConfiguration baseSettingsConfiguration;


    @PostConstruct
    public  void init(){

        BaseSetting baseSetting=baseSettingsConfiguration.getBaseSetting();
        baseSetting.setEnvironment(Environment.LOCAL);

        baseSettingsConfiguration.setBaseSetting(baseSetting);

    }

    public static void main(String[] args) throws NodeValidationException {

        LocalDatabases.start();

        SpringApplication springApplication = new SpringApplication(SpringBootApplicationn.class);

        springApplication.run();


    }

}





