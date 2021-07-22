package hospitalmangementsystem;

import java.awt.*;
import static java.awt.Color.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.sql.*;

public class payTreatmentCharges {

    Integer patID;
    JFrame frame;
    JPanel panel;
    JLabel inv, bms, patid, patFN, patLN, trC, rc, gst, tamt, cardNo, cvv;
    JLabel patidVal, patFNVal, patLNVal, trCVal, rcVal, gstVal, tamtVal;
    JTextField cardNoVal, cvvVal;
    JButton pay;
    JButton cpay;

    void retrieveValues() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "root");
            PreparedStatement pst;
            pst = con.prepareStatement("select p.patient_id, first_name, last_name, bill_charges, charges, paid from patient as p, treatment as t, room as r where p.patient_id = t.patient_id and p.room_no = r.room_no and p.floor_no = r.floor_no and p.patient_id = ?");
            pst.setString(1, Integer.toString(patID));
            ResultSet rs = pst.executeQuery();

            if (rs.next() == true) {
                String first_name = rs.getString(2);
                String last_name = rs.getString(3);
                Boolean paid = rs.getBoolean(6);
                Double tc, rc, ta;
                if (paid == false) {
                    tc = rs.getDouble(4);
                    rc = rs.getDouble(5);
                    ta = 1.2 * (tc + rc);
                } else {
                    tc = 0.0;
                    rc = 0.0;
                    ta = 0.0;
                }

                patidVal.setText(Integer.toString(patID));
                patFNVal.setText(first_name);
                patLNVal.setText(last_name);
                trCVal.setText(Double.toString(tc));
                rcVal.setText(Double.toString(rc));
                tamtVal.setText(Double.toString(ta));
                con.close();

            } else {
                JOptionPane.showMessageDialog(new JPanel(), "This Patient ID does not exist");
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    void updateValues() {
        try {

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "root");
            PreparedStatement pst;
            pst = con.prepareStatement("update patient set paid = 1 where patient_id = ?");
            pst.setString(1, Integer.toString(patID));
            pst.executeUpdate();
            pst = con.prepareStatement("update treatment set bill_charges = 0 where patient_id = ?");
            pst.setString(1, Integer.toString(patID));
            pst.executeUpdate();
            JOptionPane.showMessageDialog(new JPanel(), "Payment Successful!");
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex);

        }
    }

    payTreatmentCharges(int pat_id) {
        patID = pat_id;
        frame = new JFrame("INVOICE");
        panel = new JPanel();
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        panel.setLayout(null);
        panel.setBackground(Color.white);

        inv = new JLabel("INVOICE");
        inv.setFont(new Font("Courier New", Font.BOLD, 20));
        inv.setBounds(190, 18, 200, 30);
        panel.add(inv);

        bms = new JLabel("BMS HOSPITAL");
        bms.setFont(new Font("Arial", Font.BOLD, 20));
        bms.setBounds(170, 70, 200, 30);
        panel.add(bms);

        patid = new JLabel("Patient ID");
        patid.setFont(new Font("Verdana", Font.PLAIN, 15));
        patid.setBounds(20, 120, 200, 30);
        panel.add(patid);

        patidVal = new JLabel();
        patidVal.setFont(new Font("Verdana", Font.PLAIN, 15));
        patidVal.setBounds(250, 115, 200, 30);
        patidVal.setForeground(blue);
        panel.add(patidVal);

        patFN = new JLabel("First Name");
        patFN.setFont(new Font("Verdana", Font.PLAIN, 15));
        patFN.setBounds(20, 150, 200, 30);
        panel.add(patFN);

        patFNVal = new JLabel();
        patFNVal.setFont(new Font("Verdana", Font.PLAIN, 15));
        patFNVal.setBounds(250, 145, 200, 30);
        patFNVal.setForeground(blue);
        panel.add(patFNVal);

        patLN = new JLabel("Last Name");
        patLN.setFont(new Font("Verdana", Font.PLAIN, 15));
        patLN.setBounds(20, 180, 200, 30);
        panel.add(patLN);

        patLNVal = new JLabel();
        patLNVal.setFont(new Font("Verdana", Font.PLAIN, 15));
        patLNVal.setBounds(250, 175, 200, 30);
        patLNVal.setForeground(blue);
        panel.add(patLNVal);

        trC = new JLabel("Treatment Charges");
        trC.setFont(new Font("Verdana", Font.PLAIN, 15));
        trC.setBounds(20, 210, 200, 30);
        panel.add(trC);

        trCVal = new JLabel();
        trCVal.setFont(new Font("Verdana", Font.PLAIN, 15));
        trCVal.setBounds(250, 205, 200, 30);
        trCVal.setForeground(blue);
        panel.add(trCVal);

        rc = new JLabel("Room Charges");
        rc.setFont(new Font("Verdana", Font.PLAIN, 15));
        rc.setBounds(20, 240, 200, 30);
        panel.add(rc);

        rcVal = new JLabel();
        rcVal.setFont(new Font("Verdana", Font.PLAIN, 15));
        rcVal.setBounds(250, 235, 200, 30);
        rcVal.setForeground(blue);
        panel.add(rcVal);

        gst = new JLabel("GST (%)");
        gst.setFont(new Font("Verdana", Font.PLAIN, 15));
        gst.setBounds(20, 270, 200, 30);
        panel.add(gst);

        gstVal = new JLabel("2");
        gstVal.setFont(new Font("Verdana", Font.PLAIN, 15));
        gstVal.setBounds(250, 265, 200, 30);
        gstVal.setForeground(blue);
        panel.add(gstVal);

        tamt = new JLabel("Total Amount");
        tamt.setFont(new Font("Verdana", Font.PLAIN, 15));
        tamt.setBounds(20, 300, 200, 30);
        panel.add(tamt);

        tamtVal = new JLabel();
        tamtVal.setFont(new Font("Verdana", Font.PLAIN, 15));
        tamtVal.setBounds(250, 295, 200, 30);
        tamtVal.setForeground(blue);
        panel.add(tamtVal);

        retrieveValues();

        pay = new JButton("PAY NOW");
        pay.setFont(new Font("Verdana", Font.PLAIN, 15));
        pay.setBounds(160, 360, 170, 40);
        pay.setForeground(black);
        pay.setFocusable(false);
        pay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setSize(500, 700);
                cardNo = new JLabel("Debit Card Number");
                cardNo.setFont(new Font("Verdana", Font.BOLD, 17));
                cardNo.setBounds(20, 450, 200, 30);
                panel.add(cardNo);
                cardNoVal = new JTextField(15);
                cardNoVal.setFont(new Font("Verdana", Font.PLAIN, 17));
                cardNoVal.setBounds(250, 450, 200, 30);
                panel.add(cardNoVal);

                cvv = new JLabel("CVV");
                cvv.setFont(new Font("Verdana", Font.BOLD, 17));
                cvv.setBounds(20, 500, 200, 30);
                panel.add(cvv);
                cvvVal = new JTextField(5);
                cvvVal.setFont(new Font("Verdana", Font.PLAIN, 17));
                cvvVal.setBounds(250, 500, 200, 30);
                panel.add(cvvVal);

                cpay = new JButton("PAY");
                cpay.setFont(new Font("Verdana", Font.PLAIN, 15));
                cpay.setBounds(160, 570, 170, 40);
                cpay.setForeground(black);
                cpay.setFocusable(false);
                cpay.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //remove the patient from the database and display message - payment successful
                        updateValues();
                    }
                });
                panel.add(cpay);

            }
        });
        panel.add(pay);

        frame.setSize(500, 530);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
