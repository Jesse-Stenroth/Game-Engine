package fi.jessestenroth.rectumcere.engine.save;

/**
 * This interface contains methods what need to Savesystem
 * data save to in device
 * @author Jesse Stenroth
 * @version 0.1
 */
public interface SaveSystem {
    /**
     * save String information
     * @param dataName name of data example: save("m","hello") and when call getString("m") get back "hello"
     * @param s what you want to save
     */
    public void save(String dataName, String s);

    /**
     * save Integer information
     * @param dataName name of data example: save("m",1) and when call getString("m") get back 1
     * @param in what you want to save
     */
    public void save(String dataName, int in);

    /**
     * save Double information
     * @param dataName name of data example: save("m",3.5) and when call getString("m") get back 3.5
     * @param doub what you want to save
     */
    public void save(String dataName, double doub);

    /**
     * get String information
     * @param dataName name of data example: save("m","hello") and when call getString("m") get back "hello"
     * @return String information
     */
    public String getString(String dataName);

    /**
     * get Integer information
     * @param dataName name of data example: save("m",1) and when call getString("m") get back 1
     * @return Integer information
     */
    public int getInteger(String dataName);

    /**
     * get Double information
     * @param dataName name of data example: save("m",3.5) and when call getString("m") get back 3.5
     * @return Double information
     */
    public double getDouble(String dataName);

    /**
     * This must call when you want save all data to file.
     * also when you delete data from file
     * @return true if data is save false if not
     */
    public boolean close();

    /**
     * This method remove String data from file
     * @param dataName name of data
     */
    public void removeString(String dataName);

    /**
     * This method remove Integer data from file
     * @param dataName name of data
     */
    public void removeInteger(String dataName);

    /**
     * This method remove Double data from file
     * @param dataName name of data
     */
    public void removeDouble(String dataName);
}
