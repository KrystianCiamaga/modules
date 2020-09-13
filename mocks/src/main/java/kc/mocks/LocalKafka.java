package kc.mocks;

import com.google.common.io.Files;
import kafka.server.KafkaConfig;
import kafka.server.KafkaServerStartable;
import kafka.zk.KafkaZkClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.utils.Time;
import org.apache.zookeeper.client.ZKClientConfig;
import scala.Some;

import java.io.File;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

@Slf4j
public class LocalKafka{

    private final String zookeeper;
    private final int port;
    private final Properties kafkaBrokerConfig = new Properties();

    private File logDir;
    private File stateDir;
    private KafkaServerStartable broker;
    private KafkaZkClient client;





    public LocalKafka(int zkPort, int brokerPort){

        log.info("Zookeeper port{} ",zkPort);
        log.info("Kafka port{} ", brokerPort);

        this.port = brokerPort;
        this.zookeeper = "localhost:" + zkPort;


    }

    public void startup(){
        try {
            logDir = Files.createTempDir();
            stateDir = Files.createTempDir();

            kafkaBrokerConfig.setProperty("zookeeper.connect",zookeeper);
            kafkaBrokerConfig.setProperty("broker.id", "0");
            kafkaBrokerConfig.setProperty("host.name","localhost");
            kafkaBrokerConfig.setProperty("advertised.host.name","localhost");
            kafkaBrokerConfig.setProperty("port",Integer.toString(port));
            kafkaBrokerConfig.setProperty("log.dir",logDir.getPath());
            kafkaBrokerConfig.setProperty("state.dir",stateDir.getPath());
            kafkaBrokerConfig.setProperty("log.flush.interval.messages",String.valueOf(1000));
            kafkaBrokerConfig.setProperty("delete.topic.enable",String.valueOf(false));
            kafkaBrokerConfig.setProperty("offsets.topic.replication.factor",String.valueOf(1));
            kafkaBrokerConfig.setProperty("auto.create.topics.enable",String.valueOf(true));
            kafkaBrokerConfig.setProperty("group.initial.rebalance.delay.ms",String.valueOf(1000));
            kafkaBrokerConfig.setProperty("heartbeat.interval.ms",String.valueOf(1000));
            kafkaBrokerConfig.setProperty("log.cleaner.enable",String.valueOf(false));

            broker = new KafkaServerStartable(new KafkaConfig(kafkaBrokerConfig));
            broker.startup();
            client = KafkaZkClient.apply(
                    zookeeper,false,
                    (int) TimeUnit.MINUTES.toMillis(1), (int) TimeUnit.MINUTES.toMillis(1), 100,
                            Time.SYSTEM,"test","test", null,  Some.apply(new ZKClientConfig()));

        }catch (Throwable t) {
            log.info("test kafka failed to start", t);
            System.exit(1);
        }
    }


}
