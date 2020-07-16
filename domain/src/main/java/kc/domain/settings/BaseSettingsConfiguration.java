package kc.domain.settings;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;


@Service
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BaseSettingsConfiguration {


    @Autowired
    JacksonConfiguration jacksonConfiguration;


    @Autowired
    private BaseSetting baseSetting;

    public void loadSettingsFromFile(String path) throws IOException {
        baseSetting = jacksonConfiguration.objectMapper().readValue(new File(path), BaseSetting.class);
    }


}
