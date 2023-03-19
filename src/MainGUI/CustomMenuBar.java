package MainGUI;

import javax.swing.*;
import java.awt.event.*;

import Database.DatabaseHandling;
import Database.SecureData;

public class CustomMenuBar extends JMenuBar
{
    public CustomMenuBar ()
    {
        // create the menu bar and return it
        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");
        JMenu helpMenu = new JMenu("Help");

        JMenuItem exitMenuItem = new JMenuItem("Exit");
        JMenuItem aboutMenuItem = new JMenuItem("About");

        fileMenu.add(exitMenuItem);
        helpMenu.add(aboutMenuItem);
        editMenu.add(new JMenuItem("Edit"));

        JMenuBar menuBar = new JMenuBar();

        // add the menu bar to the frame
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(helpMenu);




    }
}
