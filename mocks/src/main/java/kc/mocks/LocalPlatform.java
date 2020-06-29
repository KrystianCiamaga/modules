package kc.mocks;

import kc.domain.SpringBootApplicationn;
import kc.domain.settings.BaseSettingsConfiguration;
import org.elasticsearch.node.NodeValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;


public class LocalPlatform {


        public static void main(String[] args) throws NodeValidationException {


            LocalDatabases.start();
            SpringApplication springApplication = new SpringApplication(SpringBootApplicationn.class);


            springApplication.run();


        }

    }





