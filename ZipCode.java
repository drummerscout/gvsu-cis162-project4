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
    int zipCode;
    String city, state;
    double lat;
    double lon;

    /*********************************************************************
     * Inital consturctur.  This sets up a basic zip code with a 
     * zip code, city, state, latitude, longitude.
     *
     *@param zipcode, a city, state, latitude, longitude
     ********************************************************************/

    public ZipCode (int pZip){
        zipCode = this.zipCode; 
        city = "UNKNOWN";
        state = "ST";
        lat = 0.0;
        lon = 0.0;
    }

    public ZipCode (int pZip, String pCity, String pState, 
    double pLat, double pLon){

        zipCode = pZip;
        city = pCity;
        state = pState;
        lat = pLat;
        lon = pLon;
    }

    public int getZip(){
        return zipCode;
    }

    public void setZip( int pZip){
        zipCode = pZip;
    }

    public String getCity(){
        return city;
    }

    public void setCity(String pCity){
        city = pCity;
    }

    public String getState(){
        return state;
    }

    public void setState(String pState){
        state = pState;
    }

    public double getLat(){
        return lat;
    }

    public void setLat(double pLat){
        lat = pLat;
    }

    public double getLon(){
        return lon;
    }

    public void setLon(double pLon){
        lon = pLon;
    }

    public String toString(){
        return ( city + (", ") + state + (" ") + zipCode); 
    }

    public boolean equals(){
        return false;
    }
}
