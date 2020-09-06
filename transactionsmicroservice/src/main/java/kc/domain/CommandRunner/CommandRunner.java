package kc.domain.CommandRunner;


import kc.domain.entity.Asset;
import kc.domain.enums.Environment;
import kc.domain.repository.AssetRepository;
import kc.domain.settings.BaseSetting;
import kc.domain.settings.BaseSettingsConfiguration;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
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
    AssetRepository assetRepository;

    public static Environment environment;

    @Override
    public void run(String... args) throws Exception {


  /*      if (environment == Environment.LOCAL) {

            baseSettingsConfiguration.getBaseSetting().setDatabaseHost("localhost");
            baseSettingsConfiguration.getBaseSetting().setPort(1212);
            baseSettingsConfiguration.getBaseSetting().setDatabasePort(9200);
            log.info("JESTEM Z LOCALPLATFORM");


        } else {

            baseSettingsConfiguration.loadSettingsFromFile(path);

            log.info("JESTEM Z DOMAIN");
        }

    }
*/
    }
}





