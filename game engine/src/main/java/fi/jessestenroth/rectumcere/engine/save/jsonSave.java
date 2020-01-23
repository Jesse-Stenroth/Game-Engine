package fi.jessestenroth.rectumcere.engine.save;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * This class save data to json file
 * @author Jesse Stenroth
 * @version 0.1
 */
public class jsonSave implements SaveSystem {

    private String name;
    private Map<String, String> stringMap;
    private Map<String,Integer> integerMap;
    private Map<String, Double> doubleMap;

    /**
     * This constructor set information which file data must to save
     * @param fileName file name (without file type) example: highscore not highscore.json
     */
    public jsonSave(String fileName){
        this.name = fileName;
        this.stringMap = new HashMap<>();
        this.integerMap = new HashMap<>();
        this.doubleMap = new HashMap<>();
        updateMaps();
    }
    /**
     * This method get all Maps information from file
     */
    private void updateMaps() {
        try {
            File file = new File(this.name + ".json");
            if (file.exists()) {
                //read data from json file
                JSONParser jsonParser = new JSONParser();
                FileReader reader = new FileReader(this.name + ".json");
                Object object = jsonParser.parse(reader);
                JSONArray pointList = (JSONArray) object;
                pointList.forEach(point -> parsePointObject((JSONObject) point));
            } else {
                //if file not exists then create file
                file.createNewFile();
            }
        } catch (IOException e){
            System.out.println("error in file handling");
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * This method add jsonobject to maps of class
     * @param point json object
     */
    private void parsePointObject(JSONObject point) {
        String type = (String) point.get("type");
        String key = (String) point.get("key");
        if(type.contains("INTEGER")){
            int i = Integer.parseInt((String) point.get(key));
            this.integerMap.put(key,i);
        } else if(type.contains("STRING")){
            String s = (String) point.get(key);
            this.stringMap.put(key,s);
        } else if(type.contains("DOUBLE")){
            double d = Double.parseDouble((String) point.get(key));
            this.doubleMap.put(key,d);
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
            BufferedWriter out = new BufferedWriter(new FileWriter(this.name + ".json"));
            JSONArray array = new JSONArray();
            for(String e : this.stringMap.keySet()){
                String o = this.stringMap.get(e);
                JSONObject object = new JSONObject();
                object.put("type","STRING");
                object.put("key",e);
                object.put(e,o);
                array.add(object);
            }
            for(String e : this.integerMap.keySet()){
                int o = this.integerMap.get(e);
                JSONObject object = new JSONObject();
                object.put("type","INTEGER");
                object.put("key",""+e);
                object.put(e,o);
                array.add(object);
            }
            for(String e : this.doubleMap.keySet()){
                double o = this.doubleMap.get(e);
                JSONObject object = new JSONObject();
                object.put("type","DOUBLE");
                object.put("key",e);
                object.put(e,"" +o);
                array.add(object);
            }
            out.write(array.toJSONString());
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
