package org.javeriana.environmentReference;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.Arrays;

public abstract class WeatherObject {

    private static final Logger logger = LogManager.getLogger(WeatherObject.class);

    protected double[] monthlyAverages = new double[12];
    protected double maxFromMonthlyAverages;
    protected double minFromMonthlyAverages;
    /**
     *
     * @param max
     * @param exceedance
     * @return
     */
    protected abstract double calculateProbability(double max, double exceedance);

    /**
     * Loads the parametric file to memory, each value for each month 0-11
     * @param filename
     */
    protected void loadAveragesMonthlyFile(String filename) {
        if(filename != null && !filename.equals("")){
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource(filename).getFile());
            try(InputStream in = new FileInputStream(file)) {
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                String line;
                int counter = 0;
                while((line = br.readLine()) != null){
                    this.monthlyAverages[counter] = Double.parseDouble(line);
                    counter++;
                }
                logger.info("Averages loaded:"+ Arrays.toString(this.monthlyAverages));
                this.maxFromMonthlyAverages = Arrays.stream(this.monthlyAverages).max().getAsDouble();
                this.minFromMonthlyAverages = Arrays.stream(this.monthlyAverages).min().getAsDouble();
            }catch (IOException e){
                logger.error(e.getMessage());
                e.printStackTrace();
            }
        } else {
            logger.warn("No filename provided, no data loaded");
        }
    }

    public double getMaxFromMonthlyAverages() {
        return maxFromMonthlyAverages;
    }

    public double getMinFromMonthlyAverages() {
        return minFromMonthlyAverages;
    }
}
