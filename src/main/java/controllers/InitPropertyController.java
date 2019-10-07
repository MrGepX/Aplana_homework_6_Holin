package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class InitPropertyController {

    private static InitPropertyController instance = null;
    private static Properties initProperties = new Properties();

    public static String getInitProperty(String key) {
        return initProperties.getProperty(key);
    }

    public static String getInitPropertyOrDefault(String key, String defaultData) {
        return initProperties.getProperty(key, defaultData);
    }

    private InitPropertyController() {
        try {
            initProperties.load(new FileInputStream(new File("C:\\Users\\MrGep\\IdeaProjects\\Aplana_homework_6_Holin\\src\\main\\resources\\InitData.properties")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static InitPropertyController getInstance() {
        if (instance == null) {
            instance = new InitPropertyController();
        }
        return instance;
    }

}
