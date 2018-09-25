package chrysalis;

//Imports
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class login extends screen
{
    //Stores the handler for changing screens.
    handler h;
    
     //BUTTONS
    JButton login;
    
    //TEXTBOX
    JTextField uname;
    JPasswordField pass;
    
    //LABELS
    JLabel u, p;
    
    //RADIO BUTTON
    JRadioButton admin, standard;
    ButtonGroup adminSelect;
    
    //PANELS
    JPanel userPanel, passPanel, buttonPanel, adminPanel;
    
    String username, password, fileUname, filePass;
    
    boolean administrator;
    JsonObject name, pswd, filePswd, fileName;
    JsonArray user, fileUser, fileFull;
    
     //FILES
    File admins = new File("./rsc/admins.json");
    File std = new File("./rsc/stdUsers.json");
    
    
    //Constructor for Home Screen. Passes handler to screen constructor and sets h variable to handler.
    //These are used for changing screens and registering this screen.
    public login(handler _h)
    {
        super(_h, "login");        
        h = _h;
    }
    
    //A required initialization method that creates all of the components.
    @Override
    public void initialize()
    {
       login = new JButton("Log in");
       
       uname = new JTextField(8);
       pass = new JPasswordField(8);
       pass.setEchoChar('•');
       
       u = new JLabel("Username:");
       p = new JLabel("Password");
       
       admin = new JRadioButton("Administrator");
       standard = new JRadioButton("Standard User");
       adminSelect = new ButtonGroup();
       
       //set up the panels to get everything nice and organized
       userPanel = new JPanel();
       passPanel = new JPanel();
       buttonPanel = new JPanel();
       adminPanel = new JPanel();
       
        //add everyhting to the appropriate panels, and add panels to the window
        
        //USERNAME
        userPanel.add(u);
        userPanel.add(uname);
        this.add(userPanel);
        
        //PASSWORD
        passPanel.add(p);
        passPanel.add(pass);
        this.add(passPanel);
        
        
        //USER TYPE
        adminPanel.add(admin);
        adminPanel.add(standard);
        this.add(adminPanel);
        
        //CREATE USER
        buttonPanel.add(login);
        this.add(buttonPanel);
        
        //add components to the window
        login.addActionListener(this);
        standard.addActionListener(this);
        admin.addActionListener(this);
        
        
    }
    
    //Implementation of required actionPerformed method. This handles button events.
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Administrator")) //The user is an admin
        {
            administrator = true;
        } 
        else if (e.getActionCommand().equals("Standard User")) //user is a standard user
        {
            administrator = false;
        }
        else 
        {
            //hash the password
            password = pass.getText();
            password = hasher.hash(password);

            username = uname.getText();

            //get the array that contains the user's info
            try 
            {
                if (administrator) 
                {
                    fileFull = (JsonArray) JSONParseWrite.JSONParse(admins);
                }
                else 
                {
                    fileFull = (JsonArray) JSONParseWrite.JSONParse(std);
                }

            } 
            catch (IOException ex)
            {
                Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
            }
            //TODO Pull the specific user from the full array, seacrchable by username

        }
    }
}
