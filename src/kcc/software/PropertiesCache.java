/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kcc.software;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 *
 * @author pradeep
 */
public class PropertiesCache {
    private final Properties configProp = new Properties();
    private Map CofigMap;
    UserEncrypt UserEncryptObj = new UserEncrypt();

    private PropertiesCache() {
        this.loadProperties();
    }

    public void loadProperties() {
        File configFile = new File("app.txt");
        try {
            FileInputStream inputStream = new FileInputStream(configFile);
            FileReader inputFile = new FileReader(configFile);
            BufferedReader bufferReader = new BufferedReader((Reader)inputFile);
            String line = "";
            String decrpted = "";
            line = bufferReader.readLine();
            if (line != null) {
                decrpted = this.UserEncryptObj.decrypt(line);
                String[] configDetails = decrpted.split(",");
                this.CofigMap = new HashMap();
                this.CofigMap.put((Object)"dbusr", (Object)/*((ApplicationConstant.UserPrefix.toLowerCase() +)*/ configDetails[0]);
                this.CofigMap.put((Object)"dbpswd", (Object)configDetails[1]);
                this.CofigMap.put((Object)"password", (Object)configDetails[2]);
                this.CofigMap.put((Object)"Tooluser", (Object)configDetails[3]);
            }
            bufferReader.close();
            inputStream.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static PropertiesCache getInstance() {
        return LazyHolder.INSTANCE;
    }

    public String getProperty(String key) {
        return this.configProp.getProperty(key);
    }

    public Map getConfigMap() {
        return this.CofigMap;
    }

    public Set<String> getAllPropertyNames() {
        return this.configProp.stringPropertyNames();
    }

    public boolean containsKey(String key) {
        return this.configProp.containsKey((Object)key);
    }

    private static class LazyHolder {
        private static final PropertiesCache INSTANCE = new PropertiesCache();

        private LazyHolder() {
        }
    }

}
