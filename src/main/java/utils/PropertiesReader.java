package utils;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertiesReader {

    public static Properties load(String path){
        Properties properiesObj = new Properties();

        try (FileInputStream insSettings = new FileInputStream(path)) {
            properiesObj.load(insSettings);
            } catch (Exception e) {
                e.printStackTrace();
            }
        return properiesObj;
    }
}
