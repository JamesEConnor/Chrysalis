package chrysalis;

//Imports
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class homeScreen extends JPanel implements ActionListener
{   
    handler h;
    
    
    //Buttons ------------------
    JButton createCommit;
    JButton approveCommit;
    JButton createUser;
    //--------------------------
    
    
    public homeScreen(handler _h)
    {
        h = _h;
        
        this.initialize();
    }
    
    public void initialize()
    {
        createCommit = new JButton("Create Commit");
        createCommit.addActionListener(this);
        this.add(createCommit);
        
        approveCommit = new JButton("Approve Commit");
        approveCommit.addActionListener(this);
        this.add(approveCommit);
        
        createUser = new JButton("Create User");
        createUser.addActionListener(this);
        this.add(createUser);
        
        this.setVisible(true);
    }
    
    //Draw the graphics.
    @Override
    public void paintComponent(Graphics g)
    {        
        super.paintComponent(g);
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == createCommit)
            h.changeComponents(this);
        else if(e.getSource() == approveCommit)
            h.changeComponents(this);
        else if(e.getSource() == createUser)
            h.changeComponents(this);
    }
}
