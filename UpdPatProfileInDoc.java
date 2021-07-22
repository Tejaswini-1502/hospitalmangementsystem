package hospitalmangementsystem;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.sql.*;

public class UpdPatProfileInDoc {

    JFrame frame;
    JPanel panel;
    JLabel label, label2, label3, label4, head1, label5, label6;
    JTextField Doa, Dod, tc, patID, diag;
    JButton find, update;
    int docID;

    UpdPatProfileInDoc(int doctor_id) {
        docID = doctor_id;
        frame = new JFrame("Update Patient Profile");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        panel = new JPanel();
        frame.add(panel);
        panel.setLayout(null);

        label = new JLabel("Update Patient Profile");
        label.setFont(new Font("Times New Roman", Font.BOLD, 18));
        label.setBounds(150, 22, 200, 30);
        panel.add(label);

        label2 = new JLabel("ADMIT DATE");
        label2.setFont(new Font("Verdana", Font.PLAIN, 15));
        label2.setBounds(15, 100, 140, 30);
        panel.add(label2);
        Doa = new JTextField(12);
        Doa.setBounds(180, 100, 150, 25);
        panel.add(Doa);

        label3 = new JLabel("DISCHARGE DATE");
        label3.setFont(new Font("Verdana", Font.PLAIN, 15));
        label3.setBounds(15, 140, 140, 30);
        panel.add(label3);
        Dod = new JTextField(12);
        Dod.setBounds(180, 140, 150, 25);
        panel.add(Dod);

        label6 = new JLabel("DIAGNOSIS");
        label6.setFont(new Font("Verdana", Font.PLAIN, 15));
        label6.setBounds(15, 180, 140, 30);
        panel.add(label6);
        diag = new JTextField(12);
        diag.setBounds(180, 180, 150, 25);
        panel.add(diag);

        head1 = new JLabel("To find a Patient, enter ID below.");
        head1.setFont(new Font("Times New Roman", Font.BOLD, 17));
        head1.setBounds(15, 260, 400, 30);
        panel.add(head1);

        label5 = new JLabel("PATIENT ID");
        label5.setFont(new Font("Verdana", Font.PLAIN, 15));
        label5.setBounds(15, 300, 140, 30);
        panel.add(label5);
        patID = new JTextField(12);
        patID.setBounds(180, 300, 150, 25);
        panel.add(patID);

        find = new JButton("FIND");
        find.setFont(new Font("Arial", Font.PLAIN, 18));
        find.setBounds(110, 360, 120, 30);
        find.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //find patient
                String ptID = patID.getText();
                if (ptID.equals("")) {
                    JOptionPane.showMessageDialog(new JPanel(), "Enter the Patient ID");
                } else {
                    //FindPatient();
                    //get the values from the database and have it in the textbox
                    try {
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "root");
                        PreparedStatement pst;
                        pst = con.prepareStatement("select * from patient where patient_id = (select patient_id from treatment where patient_id = ? and doctor_id = ?)");
                        pst.setString(1, ptID);
                        pst.setString(2, Integer.toString(docID));
                        ResultSet rs = pst.executeQuery();

                        if (rs.next() == true) {

                            Date doa = rs.getDate(6);
                            Date dod = rs.getDate(7);
                            String diagnosis = rs.getString(4);

                            Doa.setText(doa.toString());
                            if (dod != null) {
                                Dod.setText(dod.toString());
                            }
                            diag.setText(diagnosis);

                        } else {
                            JOptionPane.showMessageDialog(new JPanel(), "This Patient ID does not exist / is not under your supervision");
                        }
                        con.close();
                    } catch (SQLException ex) {
                        System.out.println(ex);

                    }

                }
            }
        });
        find.setFocusable(false);
        panel.add(find);

        update = new JButton("UPDATE");
        update.setFont(new Font("Arial", Font.PLAIN, 18));
        update.setBounds(260, 360, 120, 30);
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //updating
                String ptID = patID.getText();
                if (ptID.equals("")) {
                    JOptionPane.showMessageDialog(new JPanel(), "Enter the Patient ID");
                } else {
                    //updation
                    try {
                        String doa = Doa.getText();
                        String dod = Dod.getText();
                        String diagnosis = diag.getText();
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "root");
                        PreparedStatement pst;
                        pst = con.prepareStatement("update patient set doa = ?, dod = ?, diagnosis = ? where patient_id = ?");
                        pst.setString(1, doa);
                        pst.setString(2, dod);
                        pst.setString(3, diagnosis);
                        pst.setString(4, ptID);
                        pst.executeUpdate();
                        JOptionPane.showMessageDialog(new JPanel(), "Record Updated Successfully!");
                        Doa.setText("");
                        Dod.setText("");
                        diag.setText("");
                        con.close();
                    } catch (SQLException ex) {
                        System.out.println(ex);

                    }
                }

            }
        });
        update.setFocusable(false);
        panel.add(update);

        frame.setSize(500, 480);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
