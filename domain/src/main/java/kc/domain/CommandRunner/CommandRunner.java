package kc.domain.CommandRunner;


import kc.domain.settings.BaseSetting;
import kc.domain.settings.BaseSettingsConfiguration;
import kc.domain.settings.RestClientConfig;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Getter
@Service
@Slf4j
public class CommandRunner implements CommandLineRunner {

    @Value("${path}")
    public String path;

    @Autowired
    BaseSettingsConfiguration baseSettingsConfiguration;

    @Autowired
    RestClientConfig restClientConfig;

    @Autowired
    private Environment environment;



    @Override
    public void run(String... args) throws Exception {



        if(baseSettingsConfiguration.getBaseSetting().getEnvironment() == kc.domain.enums.Environment.LOCAL){

            baseSettingsConfiguration.loadSettingsFromFile(path);

            log.info("JESTEM Z DOMAIN");
        }
        else{

            baseSettingsConfiguration.getBaseSetting().setDatabaseHost("localhost");
            baseSettingsConfiguration.getBaseSetting().setPort(1212);
            log.info("JESTEM Z LOCALPLATFORM");

        }

        }

    }





