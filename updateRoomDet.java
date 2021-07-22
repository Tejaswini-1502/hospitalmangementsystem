package hospitalmangementsystem;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.sql.*;

public class updateRoomDet {

    Integer admid;

    updateRoomDet(int admID) {
        admid = admID;
        JFrame frame1 = new JFrame("Patient Rooom Details");
        JPanel panel1 = new JPanel();
        frame1.add(panel1);
        frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        panel1.setLayout(null);
        frame1.setResizable(false);

        JLabel head = new JLabel("Patient Room Details");
        head.setFont(new Font("Times New Roman", Font.BOLD, 18));
        head.setBounds(150, 18, 200, 30);
        panel1.add(head);

        JLabel label2 = new JLabel("FLOOR NO.");
        label2.setFont(new Font("Verdana", Font.PLAIN, 15));
        label2.setBounds(15, 100, 140, 30);
        panel1.add(label2);
        JTextField floorNo = new JTextField(12);
        floorNo.setBounds(160, 100, 150, 25);
        panel1.add(floorNo);

        JLabel label3 = new JLabel("ROOM NO.");
        label3.setFont(new Font("Verdana", Font.PLAIN, 15));
        label3.setBounds(15, 140, 140, 30);
        panel1.add(label3);
        JTextField roomNo = new JTextField(12);
        roomNo.setBounds(160, 140, 150, 25);
        panel1.add(roomNo);

        JLabel head1 = new JLabel("To find a Patient, enter ID below.");
        head1.setFont(new Font("Times New Roman", Font.BOLD, 18));
        head1.setBounds(15, 180, 400, 30);
        panel1.add(head1);

        JLabel label4 = new JLabel("PATIENT ID");
        label4.setFont(new Font("Verdana", Font.PLAIN, 15));
        label4.setBounds(15, 220, 140, 30);
        panel1.add(label4);
        JTextField patID = new JTextField(12);
        patID.setBounds(160, 220, 150, 25);
        panel1.add(patID);

        JButton find = new JButton("FIND");
        find.setFont(new Font("Arial", Font.PLAIN, 18));
        find.setBounds(30, 280, 120, 30);
        find.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String ptID = patID.getText();
                if (ptID.equals("")) {
                    JOptionPane.showMessageDialog(new JPanel(), "Enter the Patient ID");
                } else {
                    //FindPatient();
                    //get the values from the database and have it in the textbox
                    try {
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "root");
                        PreparedStatement pst;
                        pst = con.prepareStatement("select * from patient where patient_id = ?");
                        pst.setString(1, ptID);
                        ResultSet rs = pst.executeQuery();

                        if (rs.next() == true) {

                            int floor_no = rs.getInt(10);
                            int room_no = rs.getInt(11);

                            floorNo.setText(Integer.toString(floor_no));
                            roomNo.setText(Integer.toString(room_no));

                        } else {
                            JOptionPane.showMessageDialog(new JPanel(), "This Patient ID does not exist");
                        }
                        con.close();
                    } catch (SQLException ex) {
                        System.out.println(ex);

                    }

                }
            }
        });
        panel1.add(find);

        JButton update = new JButton("UPDATE");
        update.setFont(new Font("Arial", Font.PLAIN, 18));
        update.setBounds(180, 280, 120, 30);
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ptID = patID.getText();
                if (ptID.equals("")) {
                    JOptionPane.showMessageDialog(new JPanel(), "Enter the Patient ID");
                } else {
                    //updation
                    try {
                        String floor_no = floorNo.getText();
                        String room_no = roomNo.getText();
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "root");
                        PreparedStatement pst;
                        pst = con.prepareStatement("update patient set floor_no = ?, room_no = ? where patient_id = ?");
                        pst.setString(1, floor_no);
                        pst.setString(2, room_no);
                        pst.setString(3, ptID);
                        pst.executeUpdate();
                        JOptionPane.showMessageDialog(new JPanel(), "Record Updated Successfully!");
                        floorNo.setText("");
                        roomNo.setText("");
                        patID.setText("");
                        con.close();
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(new JPanel(), ex);

                    }
                }
            }
        });
        panel1.add(update);

        frame1.setSize(500, 400);
        frame1.setLocationRelativeTo(null);
        frame1.setVisible(true);
    }

}
