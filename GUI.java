import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

/*************************************************************
 * GUI for a Zip Code Database
 * 
 * @author Scott Grissom
 * @author "also edited by Taylor Countryman"
 * @version October 7, 2011
 ************************************************************/
public class GUI extends JPanel{

    /** the analyzer that does all the real work */
    ZipCodeDataBase database;

    /** Buttons to initiate each action */
    JButton distance, find, within, search, furthest;

    /** GUI frame */
    JFrame myGUI;
    JTextArea results;

    /** Data entry fields */
    JTextField zip1, zip2, radius, name;

    /** menu items */
    // if attempting the challenge activity
    JMenuBar menus;
    JMenu fileMenu;
    JMenuItem quitItem;
    JMenuItem openItem;  

    /*****************************************************************
     * Main Method
     ****************************************************************/ 
    public static void main(String args[]){
        // the traditional five lines of code from the book are
        // provided in the GUI constructor
        new GUI();
    }

    /*****************************************************************
     * constructor installs all of the GUI components
     ****************************************************************/    
    public GUI(){

        // instantiate the analyzer and read the data file    
        database = new ZipCodeDataBase();
        database.readZipCodeData("zipcodes.txt");

        // establish the frame
        myGUI = new JFrame();
        myGUI.setSize(400,200);
        myGUI.setTitle("Taylor Countryman");    

        // create display area
        results = new JTextArea(20,20);
        JScrollPane scrollPane = new JScrollPane(results);
        myGUI.add(BorderLayout.CENTER, scrollPane);

        // create data entry panel
        zip1 = new JTextField(6);
        zip2 = new JTextField(6);
        radius = new JTextField(6);
        name = new JTextField(10);
        JPanel panel = new JPanel();
        panel.add(new JLabel("Zip 1"));
        panel.add(zip1);
        panel.add(new JLabel("Zip 2"));
        panel.add(zip2);    
        panel.add(new JLabel("radius"));
        panel.add(radius);
        panel.add(new JLabel("name"));
        panel.add(name);
        myGUI.add(BorderLayout.NORTH, panel);

        // create button panel
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        distance = new JButton("distance between Zip 1 and Zip 2");
        find = new JButton("find Zip 1");
        within = new JButton("within radius of Zip 1");
        search = new JButton("search by name");
        furthest = new JButton("furthest from Zip 1");
        panel.add(find);
        panel.add(distance);
        panel.add(within);
        panel.add(search);
        panel.add(furthest);
        myGUI.add(BorderLayout.EAST, panel);

        // register the listeners
        ButtonListener listener = new ButtonListener();

        // add action listeners
        find.addActionListener (listener);
        within.addActionListener (listener);
        search.addActionListener (listener);
        furthest.addActionListener (listener);
        distance.addActionListener (listener);  

        // set up File menu
        fileMenu = new JMenu("File");
        quitItem = new JMenuItem("Quit");
        openItem = new JMenuItem("Open...");
        fileMenu.add(openItem);
        fileMenu.add(quitItem);
        menus = new JMenuBar();
        myGUI.setJMenuBar(menus);
        menus.add(fileMenu);

        // register the menu items with the action listener

        // temporarily disable the menu items
        quitItem.setEnabled(false);
        openItem.setEnabled(false);

        myGUI.setVisible(true);
        myGUI.pack();
    }

    /*****************************************************************
     * Search city and state for any match
     ****************************************************************/ 
    private void searchByName(){

        // 
        ArrayList <ZipCode> list = database.search(name.getText());

        // dislay the results
        results.setText("zip codes that begin with "            
            + name.getText()+"\n\n");
        for (ZipCode z : list){
            results.append(z.toString() + "\n");
        }
    }   

    /*****************************************************************
     * Calculate distances between two zip codes if the textfields
     * constain valid integers
     ****************************************************************/ 
    private void distance (){
        int z1 = Integer.parseInt(zip1.getText());
        int z2 = Integer.parseInt(zip2.getText()); 
        int dist = database.distance(z1,z2);

        String loc1 = database.findZip(z1).toString();
        String loc2 = database.findZip(z2).toString();

        results.setText("The distance betwen \n" 
            + loc1+" and \n"+loc2+" is " + dist+" miles");
    }

    /*****************************************************************
     * finds a zip code
     ****************************************************************/ 
    private void findZip (){

        // search for the zip code
        int z1 = Integer.parseInt(zip1.getText());
        ZipCode z = database.findZip(z1);

        // if no zip code found
        if (z == null)
            results.setText("no city found with zip code " + z1);
        else
            results.setText(z.toString());
    }

    /*****************************************************************
     * finds a zip code that is the furthest away form a given point
     ****************************************************************/ 
    private void findFurthest (){

        // search for the zip code
        int z1 = Integer.parseInt(zip1.getText());
        ZipCode z = database.furthest(z1);

        // if no zip code found
        if (z == null)
            results.setText("no city found with zip code " + z1);
        else
            results.setText(z.toString());

    }

    /*****************************************************************
     * find zips within a specific radius
     ****************************************************************/ 
    private void findZipsWithinRadius (){
        // search for the zip code
        int z1 = Integer.parseInt(zip1.getText());

        // find the specified radius
        int z2 = Integer.parseInt(radius.getText());
        ArrayList<ZipCode> zipRadius = new ArrayList <ZipCode>();
        zipRadius = database.withinRadius(z1, z2);

        results.setText(zipRadius.toString());
    }

    /*****************************************************************
     * open a data file with the name selected by the user
     ****************************************************************/ 
    private void openFile(){

        String userDir = System.getProperty("user.dir");
        JFileChooser fc = new JFileChooser(userDir);

        int returnVal = fc.showOpenDialog(this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            String filename = fc.getSelectedFile().getName();
            database.readZipCodeData(filename);          
        }
    }

    /*****************************************************************
     * Check if the String contains a valid integer.  Display
     * an appropriate warning if it is not valid.
     * 
     * @param numStr - the String to be checked
     * @param label - the textfield name that contains the String
     * @return true if valid
     ****************************************************************/   
    private boolean checkValidInteger(String numStr, String label){
        boolean isValid = true;
        try{
            int val = Integer.parseInt(numStr);

            // display error message if not a valid integer    
        }catch(NumberFormatException e){
            isValid = false;
            JOptionPane.showMessageDialog(null, "Enter an integer in " + label);
        }    
        return isValid;
    }

    /*****************************************************************
     * This method is called when any button is clicked.  The proper
     * internal method is called as needed.
     * 
     * @param e the event that was fired
     ****************************************************************/       
    private class ButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){

            // extract the button that was clicked
            JComponent buttonPressed = (JComponent) e.getSource();

            // find button
            if (e.getSource() == find)   
                findZip(); 
            // search button
            else if (e.getSource() == search)
                searchByName();
            // within button
            else if (e.getSource() == within)
                findZipsWithinRadius();
            // distance button
            else if (e.getSource() == distance)
                distance();
            // furthest
            else if (e.getSource() == furthest)
                findFurthest();

        }
    }
}