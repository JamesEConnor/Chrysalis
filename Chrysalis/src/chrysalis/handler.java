package chrysalis;

import java.applet.Applet;
import java.awt.Dimension;
import java.util.Dictionary;
import java.util.Hashtable;

public class handler extends Applet
{
    //Dictionary of all screens, linking them with their names.
    public static Dictionary<String, screen> screens = new Hashtable<>();
    
    //The current screen the user is looking at.
    screen currScreen;
    
    //Initialization method called by applet.
    @Override
    public void init()
    {
        //Create a new Home Screen. When it's created, it will automatically register itself.
        new homeScreen(this);
        new TestScreen(this);
    }
    
    //Change the screen by adding the component (screen is a JPanel).
    public void changeScreen(screen s)
    {
        this.removeAll();       
        this.add(s);
        
        this.repaint();
    }
    
    //Change the screen by adding the component, based off of it's name (screen is a JPanel).
    public void changeScreen(String s)
    {
        this.removeAll();  
        //Add it if the screen exists.
        this.add(screens.get(s));
        
        //Basically repaints it so the graphics all show up properly.
        //TODO Make this not so hacky and bad.
        Dimension d = this.getSize();
        this.resize(d.width - 1, d.height - 1);
        d = this.getSize();
        this.resize(d.width + 1, d.height + 1);
    }
    
    //Register a screen. This is called by every new screen, and adds it to screens.
    public void registerScreen(screen s, String name)
    {
        //Add screen to screens.
        screens.put(name, s);
        
        //If it's the first screen, it should be the one that we load first.
        if(screens.size() == 1)
        {
            //Set the current screen.
            currScreen = s;
            
            //Change it to that screen.
            this.changeScreen(currScreen);
        }
    }
}
