package hospitalmangementsystem;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.sql.*;

public class UpdMyProfileAd {

    Integer admID;
    JFrame frame;
    JPanel panel;
    JLabel heading;
    JLabel adName, yoe, phone, pwd, sal;
    JTextField adNameText, yoeText, phoneText, salText;
    JPasswordField pwdText;
    JButton update;
    
    UpdMyProfileAd(int adm_id) {
        admID = adm_id;
        frame = new JFrame();
        panel = new JPanel();

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(panel);
        frame.setTitle("Update My Profile");
        frame.setResizable(false);
        panel.setLayout(null);
        panel.setBackground(Color.white);

        heading = new JLabel("MY PROFILE");
        heading.setFont(new Font("Times New Roman", Font.BOLD, 20));
        heading.setBounds(170, 18, 200, 30);
        panel.add(heading);

        adName = new JLabel("ADMIN NAME");
        adName.setFont(new Font("Verdana", Font.PLAIN, 15));
        adName.setBounds(15, 70, 150, 30);
        panel.add(adName);
        adNameText = new JTextField(30);
        adNameText.setBounds(160, 70, 180, 25);
        panel.add(adNameText);

        pwd = new JLabel("PASSWORD");
        pwd.setFont(new Font("Verdana", Font.PLAIN, 15));
        pwd.setBounds(15, 120, 150, 30);
        panel.add(pwd);
        pwdText = new JPasswordField(10);
        pwdText.setBounds(160, 120, 180, 25);
        panel.add(pwdText);

        yoe = new JLabel("EXPERIENCE");
        yoe.setFont(new Font("Verdana", Font.PLAIN, 15));
        yoe.setBounds(15, 170, 150, 30);
        panel.add(yoe);
        yoeText = new JTextField(10);
        yoeText.setBounds(160, 170, 180, 25);
        panel.add(yoeText);

        phone = new JLabel("PHONE");
        phone.setFont(new Font("Verdana", Font.PLAIN, 15));
        phone.setBounds(15, 220, 150, 30);
        panel.add(phone);
        phoneText = new JTextField(10);
        phoneText.setBounds(160, 220, 180, 25);
        panel.add(phoneText);

        sal = new JLabel("SALARY");
        sal.setFont(new Font("Verdana", Font.PLAIN, 15));
        sal.setBounds(15, 270, 150, 30);
        panel.add(sal);
        salText = new JTextField(10);
        salText.setBounds(160, 270, 180, 25);
        panel.add(salText);

        update = new JButton("UPDATE");
        update.setFont(new Font("Arial", Font.PLAIN, 18));
        update.setBounds(180, 340, 140, 40);
        update.setFocusable(false);
        retrieveValues();
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //updating values of name,yoe, experience,pwd and salary
                updateValues();
            }
        });
        panel.add(update);

        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    void updateValues() {
        try {
            String name = adNameText.getText();
            String yoe = yoeText.getText();
            String sal = salText.getText();
            String phone = phoneText.getText();
            String pwd = pwdText.getText();
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "root");
            PreparedStatement pst;
            pst = con.prepareStatement("update admin set name = ?, yoe = ?, salary = ?, phone_no = ?, pwd = ? where adm_id = ?");
            pst.setString(1, name);
            pst.setString(2, yoe);
            pst.setString(3, sal);
            pst.setString(4, phone);
            pst.setString(5, pwd);
            pst.setString(6, Integer.toString(admID));
            pst.executeUpdate();
            JOptionPane.showMessageDialog(new JPanel(), "Record Updated Successfully!");
        } catch (SQLException ex) {
            System.out.println(ex);

        }
    }

    void retrieveValues() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "root");
            PreparedStatement pst;
            pst = con.prepareStatement("select * from admin where adm_id = ?");
            pst.setString(1, Integer.toString(admID));
            ResultSet rs = pst.executeQuery();

            if (rs.next() == true) {
                String name = rs.getString(4);
                String yoe = rs.getString(2);
                String sal = rs.getString(3);
                String pwd = rs.getString(5);
                long phone = rs.getLong(6);

                adNameText.setText(name);
                yoeText.setText(yoe);
                salText.setText(sal);
                pwdText.setText(pwd);
                phoneText.setText(Long.toString(phone));

            } else {
                JOptionPane.showMessageDialog(new JPanel(), "This Admin ID does not exist");
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }


}
