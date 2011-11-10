/*********************************************************************
 * First Class In Project 4.  This adds all the baisc methods and 
 * all the getter and setter methods, plus I have added the equals 
 * method that donesn't get used but need to be there.  
 * 
 * @author Taylor Countryman
 * @version Project 4 11-05-2011
 ********************************************************************/

public class ZipCode
{
    //these are my variables
    int zip;
    String city;
    String state;
    double latitude;
    double longitude;

    /*****************************************************************
     * Inital constructor.  This sets up a basic zip code with a 
     * zip code, city, state, latitude, longitude.
     *
     * @param pZip
     * @return none
     ****************************************************************/

    public ZipCode (int pZip){
        zip = pZip; 
        city = "UNKNOWN";
        state = "ST";
        latitude = 0.0;
        longitude = 0.0;
    }

    /*****************************************************************
     * This sets up a working zipCode with all the info needed.
     *
     * @param pZip, pCity, pState, pLAt, pLon
     ****************************************************************/

    public ZipCode (int pZip, String pCity, String pState, 
        double pLat, double pLon){
        zip = pZip;
        city = pCity;
        state = pState;
        latitude = pLat;
        longitude = pLon;
    }

    /*****************************************************************
     * Returns the zip code(the number zip).
     *
     * @return zip
     ****************************************************************/

    public int getZip(){
        return zip;
    }

    /****************************************************************
     * Sets pZip equal to zip.
     *
     * @param pZip
     ***************************************************************/

    public void setZip( int pZip){
        zip = pZip;
    }

    /***************************************************************
     * Returns the name of the city.
     *
     * @return city
     **************************************************************/

    public String getCity(){
        return city;
    }

    /**************************************************************
     * Set city equal to pCity.
     *
     * @param pCity
     *************************************************************/

    public void setCity(String pCity){
        city = pCity;
    }

    /*************************************************************
     * Returns the state.
     *
     * @return state
     ************************************************************/

    public String getState(){
        return state;
    }

    /************************************************************
     * Sets state equal pState.
     *
     * @param pState
     ***********************************************************/

    public void setState(String pState){
        state = pState;
    }

    /************************************************************
     * Returns latitude.
     *
     * @return latitude
     ***********************************************************/

    public double getLat(){
        return latitude;
    }

    /***********************************************************
     * Sets pLat equal to latitude.
     *
     * @param pLat
     **********************************************************/

    public void setLat(double pLat){
        latitude = pLat;
    }

    /**********************************************************
     * Returns longitude.
     *
     * @return longitude
     *********************************************************/

    public double getLon(){
        return longitude;
    }

    /*********************************************************
     * Sets pLon equal to longitude.
     *
     * @param pLon
     ********************************************************/
    public void setLon(double pLon){
        longitude = pLon;
    }

    /*********************************************************
     * Returns a string with info.
     *
     * @return String
     *********************************************************/

    public String toString(){
        return ( city + (", ") + state + (" ") + zip); 
    }

    /**********************************************************
     * Returns equals.
     *
     *@return equals
     **********************************************************/

    public boolean equals(){
        return false;
    }
}
