package hospitalmangementsystem;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.sql.*;

public class addPatients {
    JFrame frame;
    JPanel panel;
    JLabel heading;
    JLabel patID,patFN,patLN,diag,dob,doa,dod,age,phone,floorNo,roomNo,pwd,paid;
    JTextField patIDText,patFNText,patLNText,diagText,dobText,doaText,dodText,ageText,phoneText,floorNoText,roomNoText,paidText;
    JPasswordField pwdText;
    JButton add;
    void insertRecord()
    {
        try 
        {
            String patient_id = patIDText.getText();
            String first_name = patFNText.getText();
            String last_name = patLNText.getText();
            String pwd = pwdText.getText();
            String diag = diagText.getText();
            String dob = dobText.getText();
            String doa = doaText.getText();
            String dod = dodText.getText();
            String age = ageText.getText();
            String phone = phoneText.getText();
            String floor_no = floorNoText.getText();
            String room_no = roomNoText.getText();
            String paid = paidText.getText();
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "root");
            PreparedStatement pst;
            pst = con.prepareStatement("insert into patient values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
            pst.setString(1, patient_id);
            pst.setString(2, first_name);
            pst.setString(3, last_name);
            pst.setString(4, diag);
            pst.setString(5, dob);
            pst.setString(6, doa);
            pst.setString(7, dod);
            pst.setString(8, age);
            pst.setString(9, phone);
            pst.setString(10, floor_no);
            pst.setString(11, room_no);
            pst.setString(12, pwd);
            pst.setString(13, paid);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(new JPanel(), "Record Inserted Successfully!");
            patIDText.setText("");
            patFNText.setText("");
            patLNText.setText("");
            diagText.setText("");
            dobText.setText("");
            doaText.setText("");
            dodText.setText("");
            ageText.setText("");
            phoneText.setText("");
            floorNoText.setText("");
            roomNoText.setText("");
            pwdText.setText("");
            paidText.setText(""); 
            con.close();

        } catch (SQLException ex) {
            System.out.println(ex);

        }

    }
    void addField(JLabel label,JTextField field,int y)
    {
        label.setFont(new Font("Verdana",Font.PLAIN,15));
        label.setBounds(15, y, 170, 30);
        panel.add(label);
        field.setBounds(180, y, 180, 25);
        panel.add(field);
    }
    
    addPatients()
    {
        frame = new JFrame();
        panel = new JPanel();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(panel);
        frame.setTitle("Add Patient");
        frame.setResizable(false);
        panel.setLayout(null);
        panel.setBackground(Color.white);
        
        heading = new JLabel("ADD PATIENT");
        heading.setFont(new Font("Times New Roman", Font.BOLD, 20));
        heading.setBounds(170, 18, 200, 30);
        panel.add(heading);
        
        patID = new JLabel("PATIENT ID");
        patIDText = new JTextField(30);
        addField(patID,patIDText,70);
        
        patFN = new JLabel("FIRST NAME");
        patFNText = new JTextField(30);
        addField(patFN,patFNText,110);
        
        patLN = new JLabel("LAST NAME");
        patLNText = new JTextField(30);
        addField(patLN,patLNText,150);
        
        pwd = new JLabel("PASSWORD");
        pwd.setFont(new Font("Verdana",Font.PLAIN,15));
        pwd.setBounds(15, 190, 170, 30);
        panel.add(pwd);
        pwdText = new JPasswordField(30);
        pwdText.setBounds(180, 190, 180, 25);
        panel.add(pwdText);
        
        dob = new JLabel("DATE OF BIRTH");
        dobText = new JTextField(30);
        addField(dob,dobText,230);
        
        age = new JLabel("AGE");
        ageText = new JTextField(30);
        addField(age,ageText,270);
        
        phone = new JLabel("PHONE");
        phoneText = new JTextField(30);
        addField(phone,phoneText,310);
        
        diag = new JLabel("DIAGNOSIS");
        diagText = new JTextField(30);
        addField(diag,diagText,350);
        
        doa = new JLabel("ADMIT DATE");
        doaText = new JTextField(30);
        addField(doa,doaText,390);
        
        dod = new JLabel("DISCHARGE DATE");
        dodText = new JTextField(30);
        addField(dod,dodText,430);
        
        floorNo = new JLabel("FLOOR NO.");
        floorNoText = new JTextField(30);
        addField(floorNo,floorNoText,470);
        
        roomNo = new JLabel("ROOM NO.");
        roomNoText = new JTextField(30);
        addField(roomNo,roomNoText,510);
        
        paid = new JLabel("PAID");
        paidText = new JTextField(30);
        addField(paid,paidText,550);
        
        add = new JButton("ADD");
        add.setFont(new Font("Arial", Font.PLAIN, 18));
        add.setBounds(170, 600, 140, 40);
        add.setFocusable(false);
        add.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                //add patient
                insertRecord();
            }
        });
        panel.add(add);
        
        
        frame.setSize(500, 710);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}