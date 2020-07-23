package kc.domain.settings;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic adviceTopic() {
        short replicationFactor = 1;
        int numPartitions       = 3;
        return new NewTopic("users", numPartitions, replicationFactor);
    }





}
