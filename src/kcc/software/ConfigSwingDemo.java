/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kcc.software;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;

/**
 *
 * @author pradeep
 */
public class ConfigSwingDemo {
    private File configFile = new File("app.txt");

    public void saveProperties(String path) throws IOException {
        String line;
        if (!this.configFile.exists() || this.configFile.delete()) {
            // empty if block
        }
        FileReader inputFile = new FileReader(path);
        BufferedReader bufferReader = new BufferedReader((Reader)inputFile);
        FileWriter fw = new FileWriter(this.configFile, true);
        while ((line = bufferReader.readLine()) != null) {
            fw.flush();
            fw.append((CharSequence)line);
        }
        fw.close();
        bufferReader.close();
    }
}
