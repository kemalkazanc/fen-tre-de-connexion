package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class Frame extends JFrame {

    public Frame (String title){
    setTitle(title);
    setVisible(true);
    setSize(700,500);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setLocationRelativeTo(null);
    setResizable(true);

    JPanel contentPane = (JPanel) this.getContentPane();
    contentPane.setLayout(new FlowLayout(FlowLayout.CENTER));
    contentPane.add (new JButton("Connexion"));
    contentPane.add (new JTextField(20));
    contentPane.add(new JPasswordField(20));
    contentPane.add(new JLabel("Password"));
    contentPane.add(new JLabel("Pseudo"));


    this.addWindowListener(new WindowAdapter() {
        @Override
        public void windowClosing(WindowEvent e) {
            super.windowClosing(e);
            int clickedButton = JOptionPane.showConfirmDialog(Frame.this,
                    "Êtes vous sur de vouloir quitter ?" , title , JOptionPane.YES_NO_OPTION );
            if ( clickedButton == JOptionPane.YES_OPTION) {
                dispose();
                setVisible(true);
            }


        }
    });

    }
}