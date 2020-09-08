package kc.mocks;

import kafka.server.KafkaConfig;
import kafka.server.KafkaServerStartable;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.test.TestingServer;


import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class LocalKafka {

    public static KafkaServerStartable kafka;
    private static TestingServer zkServer;
    private static CuratorFramework curatorClient;

    public static void KafkaLocal(Properties kafkaProperties) throws Exception {

        KafkaConfig kafkaConfig = new KafkaConfig(kafkaProperties);


        //start local zookeeper
        System.out.println("starting local zookeeper...");

        File file=new File("/tmp/zookeeper");

        zkServer = new TestingServer(2181, file);
        ExponentialBackoffRetry retryPolicy = new ExponentialBackoffRetry(1000, 3);
        curatorClient = CuratorFrameworkFactory.newClient(zkServer.getConnectString(), retryPolicy);
        curatorClient.start();

        //zookeeper = new ZooKeeperLocal(zkProperties);
        System.out.println("done");

        //start local kafka broker
        kafka = new KafkaServerStartable(kafkaConfig);
        System.out.println("starting local kafka broker...");
        kafka.startup();
        System.out.println("done");
    }


    public void stop(){
        //stop kafka broker
        System.out.println("stopping kafka...");
        kafka.shutdown();
        System.out.println("done");
    }

}