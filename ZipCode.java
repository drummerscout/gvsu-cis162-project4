
/**************************************************************************
 * First Class In Project 4
 * 
 * @author Taylor Countryman
 * @version Project 4
**************************************************************************/
public class ZipCode
{
    private int Zip;
    private String City;
    private String State;
    private double Lati;
    private double Long;
    public ZipCode (int pZip){
        Zip = pZip;   
    }
    public ZipCode (int pZip, String pCity, String pState, double pLati, double pLong){
        Zip = pZip;
        City = pCity;
        State = pState;
        Lati = pLati;
        Long = pLong;
    }
    public String toString(){
        return City + ", "+ State+ " " + Zip;
    }
    public String getCity(){
        return City;
    }
    public String getState(){
        return State;
    }
    public int getZip(){
        return Zip;
    }
    public double getLati(){
        return Lati;
    }
    public double getLong(){
        return Long;
    }
}
