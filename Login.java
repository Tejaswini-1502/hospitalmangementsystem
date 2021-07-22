package hospitalmangementsystem;

import static java.awt.Color.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.sql.*;
import javax.swing.JOptionPane;

public class Login implements ActionListener {

    JFrame frame;
    JPanel panel;
    JLabel label;
    JLabel pLabel;
    JTextField user;
    JPasswordField passwordText;
    JButton button;
    JButton reset;
    String HRole;
    
    public Login(String role) {
        frame = new JFrame();
        panel = new JPanel();
        HRole = role;

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.add(panel);
        frame.setTitle(role + " LOGIN");
        panel.setBackground(gray);
        panel.setLayout(null);

        label = new JLabel(role + " ID");
        label.setBounds(10, 20, 80, 27);
        label.setForeground(white);
        panel.add(label);

        user = new JTextField(20);
        user.setBounds(100, 20, 165, 25);
        panel.add(user);

        pLabel = new JLabel("PASSWORD");
        pLabel.setBounds(10, 50, 80, 27);
        pLabel.setForeground(white);
        panel.add(pLabel);

        passwordText = new JPasswordField(10);
        passwordText.setBounds(100, 50, 165, 25);
        panel.add(passwordText);

        button = new JButton("Login");
        button.setBounds(10, 100, 80, 25);
        button.setFocusable(false);
        button.addActionListener(this);
        button.setActionCommand("Login");
        panel.add(button);

        reset = new JButton("Reset");
        reset.setBounds(100, 100, 80, 25);
        reset.setFocusable(false);
        reset.addActionListener(this);
        reset.setActionCommand("Reset");
        panel.add(reset);

        frame.setSize(350, 200);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    void unsuccess(String user1, String pwd) {

        if (user1.equals("")) {
            JOptionPane.showMessageDialog(new JPanel(),"Enter your ID");
        } 
        else if (pwd.equals("")) {
            JOptionPane.showMessageDialog(new JPanel(),"Enter your Password");
        }

    }
    public void actionPerformed(ActionEvent e) {
        String actionCommand = ((JButton) e.getSource()).getActionCommand();

        switch (actionCommand) {
            case "Login":
                String user1 = user.getText();
                String pwd = passwordText.getText();

                if (HRole.equals("DOCTOR")) {
                    if (user1.equals("") || pwd.equals("")) {
                        unsuccess(user1, pwd);    
                    } 
                    else {
                        try {
                            Integer user1Int = Integer.parseInt(user1);
                            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "root");
                            Statement stmt = con.createStatement();
                            ResultSet rs = stmt.executeQuery("select * from doctor where doctor_id=" + user1Int + " and  pwd = '"+pwd+"'");
                            if (rs.next()){
                                new Doctor(user1Int);
                                frame.setVisible(false);
                            } 
                            else {
                                JOptionPane.showMessageDialog(new JPanel(),"Patient ID or Password incorrect.");
                                user.setText("");
                                passwordText.setText("");
                            }
                            con.close();
                        } catch (Exception ex) {
                            System.out.println(e);
                        }
                   }
                } 
                else if (HRole.equals("PATIENT")) {
                    if (user1.equals("") || pwd.equals("")) {
                        unsuccess(user1, pwd);    
                    }
                    else{
                        try {
                            Integer user1Int = Integer.parseInt(user1);
                            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "root");
                            Statement stmt = con.createStatement();
                            ResultSet rs = stmt.executeQuery("select * from patient where patient_id=" + user1Int + " and  pwd = '"+pwd+"'");
                            if (rs.next()){
                                new Patient(user1Int);
                                frame.setVisible(false);

                            } 
                            else {
                                JOptionPane.showMessageDialog(new JPanel(),"Patient ID or Password incorrect.");
                                user.setText("");
                                passwordText.setText("");
                            }
                            con.close();
                        } catch (Exception ex) {
                            System.out.println(e);
                        }
                    }
                } 
                else if (HRole.equals("ADMIN")) {
                    if (user1.equals("") || pwd.equals("")) {
                        unsuccess(user1, pwd);    
                    }
                    else{
                        try {
                            Integer user1Int = Integer.parseInt(user1);
                            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "root");
                            Statement stmt = con.createStatement();
                            ResultSet rs = stmt.executeQuery("select * from admin where adm_id=" + user1Int + " and  pwd = '"+pwd+"'");
                            if (rs.next()){
                                new Admin(user1Int);
                                frame.setVisible(false);
                            } 
                            else {
                                JOptionPane.showMessageDialog(new JPanel(),"Patient ID or Password incorrect.");
                                user.setText("");
                                passwordText.setText("");
                            }
                            con.close();
                        } catch (Exception ex) {
                            System.out.println(e);
                        }
                    }
                }
                break;
            case "Reset":
                user.setText("");
                passwordText.setText("");
                break;
        }

    }
   
}
