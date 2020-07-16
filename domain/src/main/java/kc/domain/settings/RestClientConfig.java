package kc.domain.settings;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;

import java.io.IOException;


@Configuration
@Slf4j
public class RestClientConfig extends AbstractElasticsearchConfiguration {

    BaseSettingsConfiguration baseSettingsConfiguration;

    public RestClientConfig(BaseSettingsConfiguration baseSettingsConfiguration) {
        this.baseSettingsConfiguration = baseSettingsConfiguration;

    }




    @Override
    @Bean
    public RestHighLevelClient elasticsearchClient()  {

     /*   ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo(baseSettingsConfiguration.getBaseSetting().getDatabaseHost()
                +":"+baseSettingsConfiguration.getBaseSetting().getDatabasePort())
                .build();*/

       ClientConfiguration clientConfiguration = ClientConfiguration.builder()
               .connectedTo("localhost:9200").build();


       /*try {
           restHighLevelClient.ping(RequestOptions.DEFAULT);


       }catch (IOException ex){
           log.error(ex.getMessage());
           throw new RuntimeException(ex);
       }*/

        return RestClients.create(clientConfiguration).rest();
    }

}
