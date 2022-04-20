package org.javeriana.util.cropData;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.javeriana.util.WorldConfiguration;
import org.json.JSONArray;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class WorldCrops {

    private static final Logger logger = LogManager.getLogger(WorldCrops.class);

    private static WorldCrops cropsInstance = null;

    private HashMap<String,CropCoefficient> worldCropCoefficients;

    private WorldCrops() throws IOException {
        WorldConfiguration worldSettings = WorldConfiguration.getPropsInstance();
        ClassLoader classLoader = getClass().getClassLoader();
        File jsonFile = new File(classLoader.getResource(worldSettings.getProperty("crop.properties")).getFile());
        String jsonContent = new String(Files.readAllBytes(Paths.get(jsonFile.toURI())), StandardCharsets.UTF_8);
        System.out.println(jsonContent);
        JSONArray cropsJson = new JSONArray(jsonContent);
    }

    public static WorldCrops getInstance() {
        if(cropsInstance == null) {
            try {
                cropsInstance = new WorldCrops();
            } catch (IOException exception) {
                logger.error(exception.getMessage());
                throw new RuntimeException(exception);
            }
        }
        return cropsInstance;
    }

}
