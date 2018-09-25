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


public class userCreate extends screen
{
    
    //Stores the handler for changing screens.
    handler h;
    
    //BUTTONS
    JButton create;
    
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
    
    String username, password, userNum;
    int uid;
    boolean administrator;
    JsonObject name, pswd, uNum;
    JsonArray user;
    
    //FILES
    File admins = new File("./rsc/admins.json");
    File std = new File("./rsc/stdUsers.json");
   
   
    
    
    
    
    
 //Constructor for Screen. Passes handler to screen constructor and sets h variable to handler.
    //These are used for changing screens and registering this screen.
    public userCreate(handler _h) {
        super(_h, "NewUser");
        h = _h;
        
    }

   
    
    
   
  
    
    //A required initialization method that creates all of the components.
    @Override
    public void initialize()
    {
         //instansiate the json objects that will hold the account
         user = new JsonArray();
         name = new JsonObject();
         pswd = new JsonObject();
         uNum = new JsonObject();
       
        
        //set up the panels to get everything nice and organized
        userPanel = new JPanel();
        passPanel = new JPanel();
        buttonPanel = new JPanel();
        adminPanel = new JPanel();
        
        //Set up the labels with their text
        u = new JLabel("Enter username:");
        p = new JLabel("Enter password");
        
        
        //set up the text boxes
        uname = new JTextField(8);
        pass = new JPasswordField(8);
        pass.setEchoChar('•');
        
        //set up the selection for whether or not a user is an administrator
        adminSelect = new ButtonGroup();
        admin = new JRadioButton("Administrator");
        standard = new JRadioButton("Standard User");
        adminSelect.add(admin);
        adminSelect.add(standard);
       
        
        //set up the user creation button
        create = new JButton("create user");
        
        //add everyhting to the appropriate panels, ane add panels to the window
        
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
        buttonPanel.add(create);
        this.add(buttonPanel);
        
        
        
        //add components to the window
        create.addActionListener(this);
        standard.addActionListener(this);
        admin.addActionListener(this);
        
    }
    
    //When the create user button is pushed, take the information eneterd to create the user account in the appropriate file
    @Override
    public void actionPerformed(ActionEvent e)
    {
        
        
       
        if(e.getActionCommand().equals("Administrator"))
            //The new user will be an admin
            administrator = true;
        
        else if (e.getActionCommand().equals("Standard User"))
            //new user is a standard user
            administrator = false;
        else
        {
         
         //hash the password
         password = pass.getText();
         password = hasher.hash(password);
         
         username = uname.getText();
         
         genUID();
         
         //start adding the elements to a JsonObject that we'll add to what's in the file
         name.addProperty("username", username);
         pswd.addProperty("password", password);
         uNum.addProperty("UID", uid);
         user.add(name);
         user.add(pswd);
         user.add(uNum);
         
         try 
         {
             if(administrator == true)

                 JSONParseWrite.JSONWrite(user, admins, username);


             else
                 JSONParseWrite.JSONWrite(user, std, username);
         } 
         catch (IOException ex) 
         {
             Logger.getLogger(userCreate.class.getName()).log(Level.SEVERE, null, ex);
             //let the user know there was an error
               JOptionPane.showMessageDialog(null,"User creation failed","ERROR",JOptionPane.ERROR_MESSAGE);
         }
         
         //let the user know the operation completed sucessfully, and clear the objects for the next user
         JOptionPane.showMessageDialog(null,"User creation complete!","Success",JOptionPane.INFORMATION_MESSAGE);
         user.remove(name);
         user.remove(pswd);
         user.remove(uNum);
         name.remove("username");
         pswd.remove("password");
         uNum.remove("UID");
         
         //clear the textboxes
         pass.setText("");
         uname.setText("");
         
         
        }   
        }
    
    public void genUID()
    {
        uid = (int) (Math.random() * 10000);
    }
    }   
