package chrysalis;

//Imports
import java.awt.event.ActionEvent;
import javax.swing.*;

public class ScreenTemplate extends screen
{
    //Stores the handler for changing screens.
    handler h;
    
    
    //Constructor for Home Screen. Passes handler to screen constructor and sets h variable to handler.
    //These are used for changing screens and registering this screen.
    public ScreenTemplate(handler _h)
    {
        super(_h, "PUT_NAME_HERE");        
        h = _h;
    }
    
    //A required initialization method that creates all of the components.
    @Override
    public void initialize()
    {
        
    }
    
    //Implementation of required actionPerformed method. This handles button events.
    @Override
    public void actionPerformed(ActionEvent e)
    {
        
    }
}