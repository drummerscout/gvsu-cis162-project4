/**************************************************************************
 * This is were all the really stuff happens.  In this class, we will 
 * create a test class and scan in the text file, and preform all the 
 * actions on the zip codes.  
 * 
 * @author Taylor Countryman
 * @version Project 4 11-05-2011
 **************************************************************************/

import java.util.ArrayList;
import java.util.*;
import java.io.*;

public class ZipCodeDataBase{
    // Here is the one and only varible.
    private Map<Integer,ZipCode> map;

    /**********************************************************************
     * Constructor.  This sets up an array with all the info.  
     *********************************************************************/
    public ZipCodeDataBase()
    {
        map = new HashMap<Integer,ZipCode>();
    }

    /**********************************************************************
     * Constructor for organizing all of the information
     *********************************************************************/
    public void readZipCodeData(String filename){
        String info;
        try{
            // open the data file
            Scanner fileReader = new Scanner(new File("zipcodes.txt")); 
            Scanner lineReader;

            // continue while there is more data to read
            while(fileReader.hasNext()) {

                // read one line of data
                info = fileReader.nextLine();

                lineReader = new Scanner(info);
                lineReader.useDelimiter(",");

                // read the items one at a time
                int Zip = lineReader.nextInt();
                String City = lineReader.next();
                String State = lineReader.next();
                double Long = lineReader.nextDouble();
                double Lati = lineReader.nextDouble();    
                ZipCode zip = new ZipCode (Zip,City,State,Long,Lati);
                map.put(Zip, zip);
            }

            // could not find file
        }catch(FileNotFoundException error) {
            System.out.println("File not found ");

            // problem reading the fil
        }catch(IOException error){
            System.out.println("Oops! Something went wrong.");
        }
    }

    /**********************************************************************
     * Constructor for finding the correct ZipCode
     *********************************************************************/
    public ZipCode findZip (int Zip){
        return map.get(Zip);
    }

    /**********************************************************************
     * Constructor for distance between two places
     *********************************************************************/
    public int distance (int zip1, int zip2){
        ZipCode z1 = findZip (zip1);
        ZipCode z2 = findZip (zip2);

        if(z1 == null || z2 == null)
            return -1;

        final int EARTH_RADIUS = 3959;

        double lat1=Math.toRadians(z1.getLat());
        double lon1=Math.toRadians(z1.getLon());
        double lat2=Math.toRadians(z2.getLat());
        double lon2=Math.toRadians(z2.getLon());            

        double p1= Math.cos(lat1)*Math.cos(lon1)*Math.cos(lat2)*Math.cos(lon2);
        double p2= Math.cos(lat1)*Math.sin(lon1)*Math.cos(lat2)*Math.sin(lon2);
        double p3= Math.sin(lat1)*Math.sin(lat2);
        return (int) (Math.acos(p1+p2+p3) * EARTH_RADIUS);

    }

    /**********************************************************************
     * Constructor for finding by state
     *********************************************************************/
    public ArrayList search(String str){
        ArrayList<ZipCode> zipCodes = new ArrayList<ZipCode>();
        str = str.toUpperCase();

        for (ZipCode myZip : map.values()){
            if(myZip.getCity().startsWith(str)|| myZip.getState().startsWith(str))
                zipCodes.add(myZip);
        }
        return zipCodes;
    }

    /**********************************************************************
     * This returns all zip codes within the distence of the zip code you
     * set it to
     *
     *@param 
     *********************************************************************/
    public ArrayList withinRadius(int pZip, int pRadius){
        ArrayList radius = new ArrayList <Integer>();

        for (ZipCode zipCode : map.values()) {
            int distance = distance (pZip, zipCode.getZip());   
            if (distance <= pRadius)
                radius.add(zipCode.getZip());
        }
        return radius; 
    }

    /**********************************************************************
     * Returns the furthest zip code from the given zip code.
     * @param pZip the given zip code
     * @return the zip code farest from pZip
     *********************************************************************/
    public ZipCode furthest (int pZip){

        int maxDistance = -1;
        ZipCode maxZipCode = null;

        for ( ZipCode zipCode : map.values()){
            int distance = distance(pZip, zipCode.getZip());
            if (distance  > maxDistance){
                maxZipCode = zipCode;
                maxDistance = distance;
            }
        }
        return maxZipCode;
    }
    // Example to help me understand!
    //         int maxNum = -100;
    //         int[] nums = {4, 7, 9, 3, 10};
    //         for(int num : nums)
    //         {
    //             if (num > maxNum)
    //                 maxNum = num;
    //         }
    //         return maxNum;
    /**********************************************************************
     * The main to test some of the methods.
     *********************************************************************/
    public static void main (String [] args){
        ZipCodeDataBase pizza = new ZipCodeDataBase();
        pizza.readZipCodeData("zipcodes.txt");
        System.out.println(pizza.findZip(82930));
        System.out.println(pizza.distance(49401, 90001));
        System.out.println(pizza.search("all"));
        System.out.println(pizza.withinRadius(49401, 10));
        System.out.println(pizza.furthest(49401));
    }
}  

