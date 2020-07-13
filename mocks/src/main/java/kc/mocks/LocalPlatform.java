package kc.mocks;


import com.fasterxml.jackson.databind.ser.Serializers;
import kc.domain.CommandRunner.CommandRunner;
import kc.domain.SpringBootApplicationn;
import kc.domain.enums.Environment;
import kc.domain.settings.BaseSetting;
import kc.domain.settings.BaseSettingsConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.node.NodeValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.Extension;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@Slf4j
public class LocalPlatform implements Extension {



    public static void main(String[] args) throws NodeValidationException {

        CommandRunner.environment=Environment.LOCAL;

        SpringApplication springApplication = new SpringApplication(SpringBootApplicationn.class);

        springApplication.run();

    }




}





