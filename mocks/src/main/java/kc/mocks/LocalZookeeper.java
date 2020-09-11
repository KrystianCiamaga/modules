package kc.mocks;

import com.google.common.io.Files;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.test.InstanceSpec;
import org.apache.curator.test.TestingServer;

import java.io.File;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@Slf4j
public class LocalZookeeper {

    int port;
    private  TestingServer zkServer;

    public LocalZookeeper(int port) {
        this.port = port;
    }

    public void startup(){


        try{
            Map props = Collections.singletonMap("cnxTimeout",1);
            System.setProperty("zookeeper.cnxTimeout","1");

            File tempDit = Files.createTempDir();

            log.info("Zookeeper temp dir{}", tempDit.getAbsolutePath());

            InstanceSpec spec = new InstanceSpec(tempDit,port,
                    ThreadLocalRandom.current().nextInt(7000,8000),
                    ThreadLocalRandom.current().nextInt(7000,8000),
                    true,1,300,16,props);
            zkServer = new TestingServer(spec,true);



        }catch (Throwable e){
            log.error("Failed to start zookeeper",e);
            System.exit(1);
        }


    }
}
