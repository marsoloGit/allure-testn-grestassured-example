package tests;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.PropertiesReader;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.logging.LogManager;

public class BasicTest {
    private static final String PATH_SETTINGS = "./resources/settings";
    private static final String PATH_LOG = "./resources/settings";
    protected static final Logger logger = LoggerFactory.getLogger(BasicTest.class);
    protected static Properties settings = PropertiesReader.load(PATH_SETTINGS);


    static {
        try(FileInputStream insLog = new FileInputStream(PATH_LOG)){
            LogManager.getLogManager().readConfiguration(insLog);
              logger.info("Test run started...\n");
            System.setProperty("webdriver.chrome.driver", "src/chromedriver");
        }catch (Exception e){
            e.printStackTrace();
        }
    }


	 
}
