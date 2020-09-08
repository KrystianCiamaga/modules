package kc.mocks;

import java.nio.file.Files;
import java.util.Properties;

public class LocalKafkaProperties {


    public static Properties getLocalKafkaProperties(){

        Properties kafkaProp = new Properties();
        kafkaProp.put("zookeeper.connect", "localhost:2181");
        kafkaProp.put("host.name", "localhost");
        kafkaProp.put("broker.id", "0");
        kafkaProp.put("port", "9092");
        kafkaProp.put("log.dirs", "/tmp/kafka-logs");
        kafkaProp.put("offsets.topic.replication.factor", "1");

        return kafkaProp;
    }

}
