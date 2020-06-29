package kc.domain.settings;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;


@Configuration
public class RestClientConfig extends AbstractElasticsearchConfiguration {

    BaseSettingsConfiguration baseSettingsConfiguration;

    public RestClientConfig(BaseSettingsConfiguration baseSettingsConfiguration) {
        this.baseSettingsConfiguration = baseSettingsConfiguration;

    }

    @Override
    @DependsOn("command")
    public RestHighLevelClient elasticsearchClient() {
        ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo(baseSettingsConfiguration.getBaseSetting().getDatabaseHost()
                +":"+baseSettingsConfiguration.getBaseSetting().getDatabasePort())
                .build();

        return RestClients.create(clientConfiguration).rest();
    }

}
