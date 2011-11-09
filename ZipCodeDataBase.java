import java.util.ArrayList;
import java.util.*;
import java.io.*;
/**************************************************************************
 * Second Class In Project 4
 * 
 * @author Taylor Countryman
 * @version Project 4
 **************************************************************************/
public class ZipCodeDataBase{
    // instance variables - replace the example below with your own
    private ArrayList <ZipCode> List;
    private ArrayList <String> Search;

    /**********************************************************************
     * Constructor for objects of class ZipCodeDataBase
     *********************************************************************/
    public ZipCodeDataBase()
    {
        List = new ArrayList <ZipCode>();
    }

    /**********************************************************************
     * Constructor for organizing all of the information
     *********************************************************************/
    public void readZipCodeData(String filename){
        String info;
        try{
            // open the data file
            Scanner fileReader = new Scanner(new File("ZipCode.txt")); 
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
                List.add(zip);
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
        ZipCode toreturn = null;
        for (int i=0; i< List.size(); i++){
            if (List.get(i).getZip() == Zip){
                toreturn = List.get(i);
            }
        }
        return toreturn;

    }

    /**********************************************************************
     * Constructor for distance between two places
     *********************************************************************/
    public int distance (int zip1, int zip2){
        ZipCode z1 = findZip (zip1);
        ZipCode z2 = findZip (zip2);
        final int EARTH_RADIUS = 3959;

        int Distance = 0;
        if(z1 != null && z2 !=null){
            double Lati1=Math.toRadians(z1.getLati());
            double Long1=Math.toRadians(z1.getLong());
            double Lati2=Math.toRadians(z1.getLati());
            double Long2=Math.toRadians(z1.getLong());            

            double p1= Math.cos(Lati1)*Math.cos(Long1)*Math.cos(Lati2)*Math.cos(Long2);
            double p2= Math.cos(Lati1)*Math.sin(Long1)*Math.cos(Lati2)*Math.sin(Long2);
            double p3= Math.sin(Lati1)*Math.sin(Lati2);
            Distance = (int) (Math.acos(p1+p2+p3) * EARTH_RADIUS);
        }

        return Distance;
    }

    /**********************************************************************
     * Constructor for finding by state
     *********************************************************************/
    public ArrayList Search(String str){
        Search = new ArrayList <String>();
        str.toUpperCase();

        for (int i=0;i<List.size();i++){
            if(str.equals (List.get(i).getCity())){
            }
        }

        return Search;
    }    

    /**********************************************************************
     * Constructor for finding Cities within a Radius
     *********************************************************************/
    public ArrayList withinRadius(int pZip, int pRadius){

    }

    /**********************************************************************
     * Constructor for finding the furthest Zip
     *********************************************************************/
    public ZipCode furthest (int pZip){

        //         int max = furthest[0];
        // 
        //         for ( int t = 1; i<furthest.size(); t++){
        //             if(max > furthest[t])
        //                 return max;
        //             else
        //                 return null;
        //         }
        return null;
    }

    /**********************************************************************
     * Testing all methods
     *********************************************************************/
    public static void main (String args[]){
                 ZipCode.Search(al);
        //         System.out.println (Search);
    }

}  
