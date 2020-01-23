package fi.jessestenroth.rectumcere.engine.save;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * This class save data to txt file and get data from txt file
 * @author Jesse Stenroth
 * @version 0.1
 */
public class txtSave implements SaveSystem{
    private String name;
    private Map<String, String> stringMap;
    private Map<String,Integer> integerMap;
    private Map<String, Double> doubleMap;

    /**
     * This constructor set information which file data must to save
     * @param n file name (without file type) example: highscore not highscore.txt
     */
    public txtSave(String n){
        this.stringMap = new HashMap<>();
        this.integerMap = new HashMap<>();
        this.doubleMap = new HashMap<>();
        this.name = n;
        updateMaps();
    }

    /**
     * This method get all Maps information from file
     */
    private void updateMaps() {
        try {
            File file = new File(this.name + ".txt");
            if (file.exists()) {
                //read data line by line
                BufferedReader br = new BufferedReader(new FileReader(this.name + ".txt"));
                for(String line; (line = br.readLine()) != null; ) {
                    //if line is not empthy
                    if(!(line.trim().isEmpty())) {
                        String[] parts = line.split(":");
                        //check which one map must add data
                        if (parts[0].contains("STRING")) {
                            this.stringMap.put(parts[1],parts[2]);
                        } else if (parts[0].contains("INTEGER")) {
                            this.integerMap.put(parts[1], Integer.parseInt(parts[2]));
                        } else if (parts[0].contains("DOUBLE")) {
                            this.doubleMap.put(parts[1], Double.parseDouble(parts[2]));
                        }
                    }
                }
                br.close();
            } else {
                //if file not exists then create file
                file.createNewFile();
            }
        } catch (IOException e){
            System.out.println("error in file handling");
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void save(String dataName, String s) {
        this.stringMap.put(dataName,s);
    }

    @Override
    public void save(String dataName, int in) {
        this.integerMap.put(dataName, in);
    }

    @Override
    public void save(String dataName, double doub) {
        this.doubleMap.put(dataName,doub);
    }

    @Override
    public String getString(String dataName) {
        return this.stringMap.get(dataName);
    }

    @Override
    public int getInteger(String dataName) {
        return this.integerMap.get(dataName);
    }

    @Override
    public double getDouble(String dataName) {
        return this.doubleMap.get(dataName);
    }

    @Override
    public boolean close() {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(this.name + ".txt"));
            for(String e : this.stringMap.keySet()){
                String o = this.stringMap.get(e);
                out.write("STRING:" + e + ":" + o);
                out.newLine();
            }
            for(String e : this.integerMap.keySet()){
                int o = this.integerMap.get(e);
                out.write("INTEGER:" + e + ":" + o);
                out.newLine();
            }
            for(String e : this.doubleMap.keySet()){
                double o = this.doubleMap.get(e);
                out.write("DOUBLE:" + e + ":" + o);
                out.newLine();
            }
            out.close();
            return true;
        } catch (IOException e){
            System.out.println("error to save data in file");
            return false;
        }
    }

    @Override
    public void removeString(String dataName) {
        this.stringMap.remove(dataName);
    }

    @Override
    public void removeInteger(String dataName) {
        this.integerMap.remove(dataName);
    }

    @Override
    public void removeDouble(String dataName) {
        this.doubleMap.remove(dataName);
    }
}
