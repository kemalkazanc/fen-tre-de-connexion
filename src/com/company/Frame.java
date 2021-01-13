package com.company;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;


public class Frame extends JFrame {
    private JTextField textField;
    private JPasswordField passwordField;
    private JLabel label;
    private JLabel label1;


    public Frame () {
        super("Fenêtre de connexion");
        setVisible(true);
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);

        JPanel contentPane = (JPanel) this.getContentPane();
        contentPane.setLayout(null);

        //Création du boutton + la connexion
        JButton button = new JButton("connexion");
        contentPane.add(button);
        button.setBounds(125,150,200,30);
        button.addActionListener(this::OnSeConnect);

        textField = new JTextField();
        textField.setBounds(200,35,200,30);
        contentPane.add (textField);

        passwordField = new JPasswordField();
        passwordField.setBounds(200,80,200,30);
        contentPane.add(passwordField);


        label = new JLabel("pseudo");
        label.setBounds(100,35,200,30);
        contentPane.add(label);


        label1 = new JLabel("Password");
        label1.setBounds(100,80,200,30);
        contentPane.add(label1);

        // POPUPfenetre pour fermer la fenetre de connexion
            this.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    int clickedButton = JOptionPane.showConfirmDialog(Frame.this, "Etes-vous sur de vouloir quitter ?", "Title", JOptionPane.YES_NO_OPTION);
                    if(clickedButton == JOptionPane.YES_OPTION){
                        Frame.this.dispose();
                    }
                }
            });
        }
        public void OnSeConnect(ActionEvent e) {

            //Connexion à la base de donnée
            Properties props = new Properties();
            try (FileInputStream fis = new
                    FileInputStream("conf.properties")) {
                props.load(fis);
            } catch (FileNotFoundException fe) {
                fe.printStackTrace();
            } catch (IOException IOe) {
                IOe.printStackTrace();
            }
            String jdbcUrl = props.getProperty("jdbc.url");
            String jdbcLogIn = props.getProperty("jdbc.login");
            String jdbcPassword = props.getProperty("jdbc.password");

            String recuplogin = textField.getText();
            char[] BAP = passwordField.getPassword();
            String recuppassword = new String(BAP);
            try {
                Connection connection = DriverManager.getConnection(jdbcUrl, jdbcLogIn, jdbcPassword);
                PreparedStatement sql = connection.prepareStatement("SELECT user_login, user_password FROM user where user_login=? and user_password=?");
                sql.setString(1, recuplogin);
                sql.setString(2, recuppassword);
                ResultSet resultSql = sql.executeQuery();
                if(resultSql.next()){
                    JOptionPane.showMessageDialog(null,"Authentification réussi");
                } else{
                    JOptionPane.showMessageDialog(null,"Authentification échouer ");
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    public static void  main (String[] args) throws Exception {
        UIManager.setLookAndFeel(new NimbusLookAndFeel());
        Frame authentification = new Frame();
        authentification.setVisible(true);
    }
    }