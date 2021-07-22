package hospitalmangementsystem;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.sql.*;

public class UpdMyProfileDoc {

    Integer docID;
    JFrame frame;
    JPanel panel;
    JLabel heading;
    JLabel fn, ln, pwd, qual, spec, yoe;
    JTextField fnText, lnText, qualText, specText, yoeText;
    JPasswordField pwdText;
    JButton update;

    void updateValues() {
        try {
            String first_name = fnText.getText();
            String last_name = lnText.getText();
            String pwd = pwdText.getText();
            String qual = qualText.getText();
            String spec = specText.getText();
            String yoe = yoeText.getText();
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "root");
            PreparedStatement pst;
            pst = con.prepareStatement("update doctor set first_name = ?, last_name = ?, pwd = ?, qualification = ?, specialization = ?, yoe = ?  where doctor_id = ?");
            pst.setString(1, first_name);
            pst.setString(2, last_name);
            pst.setString(3, pwd);
            pst.setString(4, qual);
            pst.setString(5, spec);
            pst.setString(6, yoe);
            pst.setString(7, Integer.toString(docID));
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
            pst = con.prepareStatement("select * from doctor where doctor_id = ?");
            pst.setString(1, Integer.toString(docID));
            ResultSet rs = pst.executeQuery();

            if (rs.next() == true) {
                String first_name = rs.getString(3);
                String last_name = rs.getString(4);
                String pwd = rs.getString(2);
                String qual = rs.getString(5);
                String spec = rs.getString(6);
                String yoe = rs.getString(8);

                fnText.setText(first_name);
                lnText.setText(last_name);
                pwdText.setText(pwd);
                qualText.setText(qual);
                specText.setText(spec);
                yoeText.setText(yoe);
                con.close();

            } else {
                JOptionPane.showMessageDialog(new JPanel(), "This Doctor ID does not exist");
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    UpdMyProfileDoc(int doc_id) {
        docID = doc_id;
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

        pwd = new JLabel("PASSWORD");
        pwd.setFont(new Font("Verdana", Font.PLAIN, 15));
        pwd.setBounds(15, 170, 170, 30);
        panel.add(pwd);
        pwdText = new JPasswordField(20);
        pwdText.setBounds(180, 170, 180, 25);
        panel.add(pwdText);

        qual = new JLabel("QUALIFICATION");
        qual.setFont(new Font("Verdana", Font.PLAIN, 15));
        qual.setBounds(15, 220, 170, 30);
        panel.add(qual);
        qualText = new JTextField(10);
        qualText.setBounds(180, 220, 180, 25);
        panel.add(qualText);

        spec = new JLabel("SPECIALIZATION");
        spec.setFont(new Font("Verdana", Font.PLAIN, 15));
        spec.setBounds(15, 270, 170, 30);
        panel.add(spec);
        specText = new JTextField(10);
        specText.setBounds(180, 270, 180, 25);
        panel.add(specText);

        yoe = new JLabel("EXPERIENCE");
        yoe.setFont(new Font("Verdana", Font.PLAIN, 15));
        yoe.setBounds(15, 320, 320, 30);
        panel.add(yoe);
        yoeText = new JTextField(10);
        yoeText.setBounds(180, 320, 180, 25);
        panel.add(yoeText);

        update = new JButton("UPDATE");
        update.setFont(new Font("Arial", Font.PLAIN, 18));
        update.setBounds(170, 380, 140, 40);
        update.setFocusable(false);
        retrieveValues();
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateValues();
                //updating values
            }
        });
        panel.add(update);

        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

   
}
