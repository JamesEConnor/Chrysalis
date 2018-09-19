package chrysalis;

//Imports
import java.awt.event.ActionEvent;
import javax.swing.*;

//Represents home screen.
public class homeScreen extends screen
{   
    //Stores the handler for changing screens.
    handler h;
    
    
    //Buttons ------------------
    JButton createCommit;
    JButton approveCommit;
    JButton createUser;
    //--------------------------
    
    
    //Constructor for Home Screen. Passes handler to screen constructor and sets h variable to handler.
    //These are used for changing screens and registering this screen.
    public homeScreen(handler _h)
    {
        super(_h, "Home");        
        h = _h;
    }
    
    //A required initialization method that creates all of the components.
    @Override
    public void initialize()
    {
        //Make the createCommit button and store it to a variable.
        createCommit = new JButton("Create Commit");
        createCommit.addActionListener(this);
        this.add(createCommit);
        
        //Make the approveCommit button and store it to a variable.
        approveCommit = new JButton("Approve Commit");
        approveCommit.addActionListener(this);
        this.add(approveCommit);
        
        //Make the createUser button and store it to a variable.
        createUser = new JButton("Create User");
        createUser.addActionListener(this);
        this.add(createUser);
    }
    
    //Implementation of required actionPerformed method. This handles button events.
    @Override
    public void actionPerformed(ActionEvent e)
    {
        //Change screens according to different buttons.
        if(e.getSource() == createCommit)
            h.changeScreen("Test");
        else if(e.getSource() == approveCommit)
            h.changeScreen(this);
        else if(e.getSource() == createUser)
            h.changeScreen(this);
    }
}