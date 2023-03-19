import Database.DatabaseHandling;
import Book.*;
import MainGUI.*;

import java.awt.*;
import java.util.*;
import javax.swing.*;

import User.View;

public class Main
{
    public static void main (String[] argh)
    {

        JFrame mainFramee = new JFrame("Library Management System");
        mainFramee.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFramee.setSize(1000, 800);
        mainFramee.setVisible(true);


        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");

        JMenuItem exitMenuItem = new JMenuItem("users list");
        JMenuItem aboutMenuItem = new JMenuItem("About");

        fileMenu.add(exitMenuItem);
        fileMenu.add(aboutMenuItem);

        menuBar.add(fileMenu);

        mainFramee.setJMenuBar(menuBar);

        View view = new View();

        exitMenuItem.addActionListener(e ->
        {
            view.getUserList();
            // when the user click X button, the program should go back to main frame
            mainFramee.setVisible(true);
        });


    }
}