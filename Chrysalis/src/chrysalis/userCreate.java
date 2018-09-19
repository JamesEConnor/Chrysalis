package chrysalis;

//Imports
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
    JTextField pass;
    
    //LABELS
    JLabel u;
    JLabel p;
    
    //RADIO BUTTON
    JRadioButton admin;
    JRadioButton standard;
    ButtonGroup adminSelect;
    
    
    String username;
    String password;
    int uid;
    String userNum; 
    boolean administrator;
    
    MessageDigest digest;
    PrintWriter output;
   
    
    
    
    
    
    
 //Constructor for Home Screen. Passes handler to screen constructor and sets h variable to handler.
    //These are used for changing screens and registering this screen.
    public userCreate(handler _h) {
        super(_h, "NewUser");
        h = _h;
        
    }

   
    
    
   
  
    
    //A required initialization method that creates all of the components.
    @Override
    public void initialize()
    {
        
        //Set up the labels with their text
        u = new JLabel("Enter username:");
        p = new JLabel("Enter password");
        
        
        //set up the text boxes
        uname = new JTextField(8);
        pass = new JTextField(8);
        
        //set up the selection for whether or not a user is an administrator
        adminSelect = new ButtonGroup();
        admin = new JRadioButton("Administrator");
        standard = new JRadioButton("Standard User");
        adminSelect.add(admin);
        adminSelect.add(standard);
        
        //set up the user creation button
        create = new JButton("create user");
        
        //add components to the window
        this.add(u);
        this.add(uname);
        this.add(p);
        this.add(pass);
        this.add(standard);
        this.add(admin);
        create.addActionListener(this);
        this.add(create);
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
            try {
                //create the new user
                username = "\"username\":" +  uname.getText() + ",";
                password = pass.getText();
                
                //generate the UID
                uid = (int)(Math.random() * 10000);
                
                //create hash of the password (we don't want to store it in plain text, that'd be bad)
                try {
                    digest = MessageDigest.getInstance("SHA-256");
                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(userCreate.class.getName()).log(Level.SEVERE, null, ex);
                }
                byte[] encodedhash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
                password = "\"password\":" + bytesToHex(encodedhash) + ",";
                
                userNum = "\"uid\":" + uid + ",";
                if(administrator)
                    output = new PrintWriter(new FileWriter("./rsc/admins.json", true));
                else
                    output = new PrintWriter(new FileWriter("./rsc/stdUsers.json", true));
                output.println(username +"\n" + password + "\n" + userNum);
                output.close();
                
            } catch (FileNotFoundException ex) {
                Logger.getLogger(userCreate.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(userCreate.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    
    private static String bytesToHex(byte[] hash) {
    StringBuilder hexString = new StringBuilder();
    for (int i = 0; i < hash.length; i++) {
    String hex = Integer.toHexString(0xff & hash[i]);
    if(hex.length() == 1) hexString.append('0');
        hexString.append(hex);
    }
    return hexString.toString();
}
    
   
}