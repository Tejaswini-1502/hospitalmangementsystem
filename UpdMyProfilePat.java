package hospitalmangementsystem;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.sql.*;

public class UpdMyProfilePat {

    Integer patID;
    JFrame frame;
    JPanel panel;
    JLabel heading;
    JLabel fn, ln, dob, age, phone;
    JTextField fnText, lnText, dobText, ageText, phoneText;
    JButton update;

    UpdMyProfilePat(int pat_id) {
        patID = pat_id;
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

        fn = new JLabel("FIRST NAME");
        fn.setFont(new Font("Verdana", Font.PLAIN, 15));
        fn.setBounds(15, 70, 170, 30);
        panel.add(fn);
        fnText = new JTextField(30);
        fnText.setBounds(180, 70, 180, 25);
        panel.add(fnText);

        ln = new JLabel("LAST NAME");
        ln.setFont(new Font("Verdana", Font.PLAIN, 15));
        ln.setBounds(15, 120, 170, 30);
        panel.add(ln);
        lnText = new JTextField(10);
        lnText.setBounds(180, 120, 180, 25);
        panel.add(lnText);

        dob = new JLabel("DOB(YYYY-MM-DD)");
        dob.setFont(new Font("Verdana", Font.PLAIN, 15));
        dob.setBounds(15, 170, 170, 30);
        panel.add(dob);
        dobText = new JTextField(10);
        dobText.setBounds(180, 170, 180, 25);
        panel.add(dobText);

        age = new JLabel("AGE");
        age.setFont(new Font("Verdana", Font.PLAIN, 15));
        age.setBounds(15, 220, 170, 30);
        panel.add(age);
        ageText = new JTextField(10);
        ageText.setBounds(180, 220, 180, 25);
        panel.add(ageText);

        phone = new JLabel("PHONE");
        phone.setFont(new Font("Verdana", Font.PLAIN, 15));
        phone.setBounds(15, 270, 170, 30);
        panel.add(phone);
        phoneText = new JTextField(10);
        phoneText.setBounds(180, 270, 180, 25);
        panel.add(phoneText);

        update = new JButton("UPDATE");
        update.setFont(new Font("Arial", Font.PLAIN, 18));
        update.setBounds(170, 330, 140, 40);
        update.setFocusable(false);
        retrieveValues();
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //updating values
                updateValues();
            }
        });
        panel.add(update);

        frame.setSize(500, 460);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    
    void updateValues() {
        try {
            String first_name = fnText.getText();
            String last_name = lnText.getText();
            String dob = dobText.getText();
            String age = ageText.getText();
            String phone = phoneText.getText();
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "root");
            PreparedStatement pst;
            pst = con.prepareStatement("update patient set first_name = ?, last_name = ?, dob = ?, age = ?, phone_no = ? where patient_id = ?");
            pst.setString(1, first_name);
            pst.setString(2, last_name);
            pst.setString(3, dob);
            pst.setString(4, age);
            pst.setString(5, phone);
            pst.setString(6, Integer.toString(patID));
            pst.executeUpdate();
            JOptionPane.showMessageDialog(new JPanel(), "Record Updated Successfully!");
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex);

        }
    }

    void retrieveValues() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "root");
            PreparedStatement pst;
            pst = con.prepareStatement("select * from patient where patient_id = ?");
            pst.setString(1, Integer.toString(patID));
            ResultSet rs = pst.executeQuery();

            if (rs.next() == true) {
                String first_name = rs.getString(2);
                String last_name = rs.getString(3);
                Date dob = rs.getDate(5);
                int age = rs.getInt(8);
                long phone = rs.getLong(9);

                fnText.setText(first_name);
                lnText.setText(last_name);
                dobText.setText(dob.toString());
                ageText.setText(Integer.toString(age));
                phoneText.setText(Long.toString(phone));
                con.close();

            } else {
                JOptionPane.showMessageDialog(new JPanel(), "This Patient ID does not exist");
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }


}
