
package hospitalmangementsystem;
import java.awt.*;
import javax.swing.*;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
public class ContactDoctor {
    Integer patID;
    JFrame frame;
    DefaultTableModel model;
    JTable table1;
    JScrollPane sp;
    ContactDoctor(int patient_id)
    {
        patID = patient_id;
        frame= new JFrame("CONTACT MY DOC");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        String[] columns = {"First Name","Last Name","Phone"};
        model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);
        table1 = new JTable();
        table1.setModel(model);
        table1.setBounds(30, 40, 200, 300);
        sp = new JScrollPane(table1);
        getContact();
        frame.add(sp);
        frame.setSize(500, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    void getContact() 
    {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "root");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select first_name,last_name,phone_no from doctor as d1,doctor_phone as d2 where d1.doctor_id=d2.doctor_id and  d2.doctor_id = (select doctor_id from treatment where patient_id =" + patID + ") ");
            while (rs.next()) {
                //model.addRow(new Object[]{rs.getLong(1)});
                model.addRow(new Object[]{rs.getString(1),rs.getString(2),rs.getLong(3)});
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }

    }
   
}
