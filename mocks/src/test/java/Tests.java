import kc.domain.controller.AssetController;
import kc.domain.entity.Asset;
import kc.mocks.LocalDatabases;
import kc.mocks.LocalPlatform;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import java.io.IOException;
import java.sql.SQLOutput;

@ExtendWith({LocalPlatform.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class Tests {


    static Logger logger=LoggerFactory.getLogger(Tests.class);

    @BeforeAll

    public static void start(){
        LocalDatabases.start();
logger.error("###############################");
    }

    @Autowired
    AssetController assetController;



    @Test
    public void postExample() throws IOException {


        logger.info("wykonuje test");

/*

                logback

                slf4j


                        resthighclient

*/



        Asset asset = new Asset();
        asset.setCategory("kategoria");
        asset.setName("imie");

        AssetController assetController = Mockito.mock(AssetController.class);


       // assert(assetController.addAsset(asset))
    }


}
