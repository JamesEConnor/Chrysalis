package chrysalis;

import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//The over-arching class used to represent different screens.
public abstract class screen extends JPanel implements ActionListener
{
    //Constructor
    public screen(handler _h, String name)
    {
        //Informs the UI handler that this screen has been created.
        _h.registerScreen(this, name);
        
        //Runs the initialize method.
        initialize();
    }
    
    //Require initialize method to be implemented.
    public abstract void initialize();
    
    //Require Paint Component Method
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
    }
    
    //Require actionPerformed method to be implemented.
    @Override
    public abstract void actionPerformed(ActionEvent e);
    
    
}
