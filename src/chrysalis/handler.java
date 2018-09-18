package chrysalis;

import java.applet.Applet;
import java.awt.Component;
import java.awt.Graphics;

public class handler extends Applet
{
    //Screens Available
    public static enum screen
    {
        home,
        createCommit,
        approveCommit,
        viewCommits,
        createUser
    }
    
    screen currScreen = screen.home;
    
    homeScreen home;
    
    @Override
    public void init()
    {
        home = new homeScreen(this);
        changeComponents(home);
    }
    
    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
    }
    
    public void changeComponents(Component panel)
    {
        this.removeAll();
        
        this.add(panel);
    }
}
