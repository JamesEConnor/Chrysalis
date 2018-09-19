package chrysalis;

//Imports
import java.awt.event.ActionEvent;
import javax.swing.*;

public class TestScreen extends screen
{    
    //Stores the handler for changing screens.
    handler h;
    
    //Buttons-----------------
    JButton back;
    //------------------------
    
    //Constructor for Home Screen. Passes handler to screen constructor and sets h variable to handler.
    //These are used for changing screens and registering this screen.
    public TestScreen(handler _h)
    {
        super(_h, "Test");        
        h = _h;
    }
    
    //A required initialization method that creates all of the components.
    @Override
    public void initialize()
    {
        back = new JButton("Back");
        back.addActionListener(this);
        this.add(back);
    }
    
    //Implementation of required actionPerformed method. This handles button events.
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == back)
            h.changeScreen("Home");
    }
}