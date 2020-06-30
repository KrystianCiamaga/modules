package kc.domain.CommandRunner;


import kc.domain.settings.BaseSetting;
import kc.domain.settings.BaseSettingsConfiguration;
import kc.domain.settings.RestClientConfig;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Getter
@Service
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


        //baseSettingsConfiguration.loadSettingsFromFile(path);

       // baseSettingsConfiguration.setBaseSetting(LocalBaseSettings.getLocalBaseSettings());

        }

    }





