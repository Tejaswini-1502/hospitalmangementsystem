package hospitalmangementsystem;

import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.Color;
import static java.awt.Color.black;
import static java.awt.Color.pink;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

final public class Admin implements ActionListener {

    JFrame frame;
    JPanel panel;
    JLabel heading;
    JButton viewAd;
    JButton uptAd;
    JButton viewPat;
    JButton viewDoc;
    JButton viewTreatment;
    JButton uptRoom;
    JButton viewMyDoc;
    JButton addPat;
    JButton delPat;
    Integer adm_id;
   
    public Admin(int admID) {
        
        adm_id = admID;
        frame = new JFrame();
        panel = new JPanel();

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(panel);
        frame.setResizable(false);
        frame.setTitle("ADMIN PORTAL");
        panel.setLayout(null);
        panel.setBackground(Color.darkGray);

        heading = new JLabel("WELCOME BACK !");
        heading.setBounds(170, 20, 400, 40);
        heading.setFont(new Font("Verdana", Font.BOLD, 18));
        heading.setForeground(Color.white);
        panel.add(heading);


        uptAd = new JButton("Update My Profile");
        uptAd.setBounds(20, 80, 200, 40);
        formatChildren(uptAd);
        uptAd.addActionListener(this);
        uptAd.setActionCommand("UpdateMyProfile");
        panel.add(uptAd);

        viewPat = new JButton("View Patients");
        viewPat.setBounds(20, 140, 200, 40);
        formatChildren(viewPat);
        viewPat.addActionListener(this);
        viewPat.setActionCommand("ViewPatients");
        panel.add(viewPat);
        
        viewDoc = new JButton("View Doctors");
        viewDoc.setBounds(20, 200, 200, 40);
        formatChildren(viewDoc);
        viewDoc.addActionListener(this);
        viewDoc.setActionCommand("ViewDoctors");
        panel.add(viewDoc);

        viewMyDoc = new JButton("View My Doctors");
        viewMyDoc.setBounds(20, 260, 200, 40);
        formatChildren(viewMyDoc);
        viewMyDoc.addActionListener(this);
        viewMyDoc.setActionCommand("ViewMyDoctors");
        panel.add(viewMyDoc);

        viewTreatment = new JButton("View Treatments");
        viewTreatment.setBounds(280, 80, 200, 40);
        formatChildren(viewTreatment);
        viewTreatment.addActionListener(this);
        viewTreatment.setActionCommand("ViewTreatments");
        panel.add(viewTreatment);

        uptRoom = new JButton("Update Room Details");
        uptRoom.setBounds(280, 140, 200, 40);
        formatChildren(uptRoom);
        uptRoom.addActionListener(this);
        uptRoom.setActionCommand("RoomDetails");
        panel.add(uptRoom);
        
        addPat = new JButton("Add Patients");
        addPat.setBounds(280, 200, 200, 40);
        formatChildren(addPat);
        addPat.addActionListener(this);
        addPat.setActionCommand("AddPatient");
        panel.add(addPat);
        
        delPat = new JButton("Remove Patients");
        delPat.setBounds(280, 260, 200, 40);
        formatChildren(delPat);
        delPat.addActionListener(this);
        delPat.setActionCommand("RemovePatient");
        panel.add(delPat);
        
        frame.setSize(520, 370);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

   
    void display(char c) {
        JFrame frame1 = new JFrame("BMS Doctors");
        frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame1.setLayout(new BorderLayout());
        String columns[] = {"Doctor ID", "First Name", "Last Name", "Qualification", "Specialization", "Salary", "YOE", "Admin ID"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);
        JTable table1 = new JTable();
        table1.setModel(model);
        table1.setBounds(30, 40, 200, 300);
        JScrollPane sp = new JScrollPane(table1);

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "root");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from doctor");
            while (rs.next()) {
                model.addRow(new Object[]{rs.getInt(1), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getDouble(7), rs.getInt(8), rs.getInt(9)});
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        frame1.add(sp);
        frame1.setSize(1000, 400);
        frame1.setLocationRelativeTo(null);
        frame1.setVisible(true);

    }

    void display(int b) {
        JFrame frame1 = new JFrame("Treatment Charges");
        frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame1.setLayout(new BorderLayout());
        String columns[] = {"Doctor ID", "Patient ID", "Bill Charges"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);
        JTable table1 = new JTable();
        table1.setModel(model);
        table1.setBounds(30, 40, 200, 300);
        JScrollPane sp = new JScrollPane(table1);
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "root");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from treatment");
            //System.out.println("Doctor_id  Patient_id  Bill_Charges");
            while (rs.next()) //System.out.println(rs.getInt(1)+"       "+rs.getInt(2)+"   "+rs.getDouble(3));  
            {
                model.addRow(new Object[]{rs.getInt(1), rs.getInt(2), rs.getDouble(3)});
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        frame1.add(sp);
        frame1.setSize(1000, 400);
        frame1.setLocationRelativeTo(null);
        frame1.setVisible(true);

    }

    void display(boolean c) {
        JFrame frame1 = new JFrame("Doctors Under Supervision");
        frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame1.setLayout(new BorderLayout());
        String columns[] = {"Doctor ID", "First Name", "Last Name", "Qualification", "Specialization", "Salary", "YOE", "Admin ID"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);
        JTable table1 = new JTable();
        table1.setModel(model);
        table1.setBounds(30, 40, 200, 300);
        JScrollPane sp = new JScrollPane(table1);
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "root");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from doctor where adm_id = " + adm_id);
            while (rs.next()) {
                model.addRow(new Object[]{rs.getInt(1), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getDouble(7), rs.getInt(8), rs.getInt(9)});
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        frame1.add(sp);
        frame1.setSize(1000, 400);
        frame1.setLocationRelativeTo(null);
        frame1.setVisible(true);
    }

    void display(double d) {
        JFrame frame1 = new JFrame("BMS Patients");
        frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame1.setLayout(new BorderLayout());
        String columns[] = {"Patient ID", "First Name", "Last Name", "Diagnosis", "DOB", "Admitted Date", "Discharge Date", "Age", "Phone", "Floor No.", "Room No."};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);
        JTable table1 = new JTable();
        table1.setModel(model);
        table1.setBounds(30, 40, 200, 300);
        JScrollPane sp = new JScrollPane(table1);
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "root");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from patient");
            //System.out.println("patient_id | first_name | last_name | diagnosis | dob        | doa        | dod  | age  | phone_no   | floor_no | room_no");
            while (rs.next()) //System.out.println(rs.getInt(1)+"       "+rs.getString(2)+"   "+rs.getString(3) + "   " + rs.getString(4) + " " + rs.getDate(5) + " " + rs.getDate(6) + " " + rs.getDate(7) + " " + rs.getInt(8) + " " + rs.getLong(9) + " " + rs.getInt(10) + " " + rs.getInt(11));  
            {
                model.addRow(new Object[]{rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5), rs.getDate(6), rs.getDate(7), rs.getInt(8), rs.getLong(9), rs.getInt(10), rs.getInt(11)});
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        frame1.add(sp);
        frame1.setSize(1000, 400);
        frame1.setLocationRelativeTo(null);
        frame1.setVisible(true);
    }
    
    public void formatChildren(JButton button)
    {
        button.setBackground(Color.pink);
        button.setForeground(black);
        button.setBorderPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 15));
        button.setFocusable(false);
        button.addMouseListener(new MouseAdapter() {
         public void mouseEntered(MouseEvent evt) {
            button.setBackground(black);
            button.setForeground(pink);
         }
         public void mouseExited(MouseEvent evt) {
            button.setBackground(pink);
            button.setForeground(black);
         }
         public void mousePressed(MouseEvent evt){
            button.setForeground(black);
            button.setBackground(Color.darkGray);
         }

      });
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = ((JButton) e.getSource()).getActionCommand();

        switch (actionCommand) {
            case "UpdateMyProfile":
                new UpdMyProfileAd(adm_id);
                break;
            case "ViewPatients":
                display(10.4);
                break;
            case "ViewDoctors":
                display('a');
                break;
            case "ViewMyDoctors":
                display(true);
                break;
            case "ViewTreatments":
                display(10) ;
                break;
            case "RoomDetails":
                new updateRoomDet(adm_id);
                break;
            case "AddPatient":
                new addPatients();
                break;
            case "RemovePatient":
                new removePatients();
                break;
        }
    }
    
    
    
}
