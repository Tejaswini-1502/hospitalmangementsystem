package hospitalmangementsystem;


import java.awt.BorderLayout;
import java.awt.Color;
import java.sql.*;
import static java.awt.Color.DARK_GRAY;
import static java.awt.Color.black;
import static java.awt.Color.pink;
import static java.awt.Color.white;
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

final public class Doctor implements ActionListener {

    JFrame frame;
    JPanel panel;
    JLabel heading;
    JButton uptDoc;
    JButton viewPat;
    JButton uptPat;
    Integer docID;
    

    public Doctor(int doc_id) {
        docID = doc_id;
        frame = new JFrame();
        panel = new JPanel();

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.add(panel);
        frame.setTitle("DOCTOR PORTAL");
        panel.setLayout(null);
        panel.setBackground(Color.darkGray);

        heading = new JLabel("WELCOME BACK !");
        heading.setBounds(40, 20, 200, 40);
        heading.setFont(new Font("Verdana", Font.BOLD, 18));
        heading.setForeground(white);
        panel.add(heading);

        uptDoc = new JButton("Update My Profile");
        uptDoc.setBounds(40, 80, 200, 40);
        formatChildren(uptDoc);
        uptDoc.addActionListener(this);
        uptDoc.setActionCommand("UpdateMyProfile");
        panel.add(uptDoc);

        viewPat = new JButton("View My Patients");
        viewPat.setBounds(40, 140, 200, 40);
        formatChildren(viewPat);
        viewPat.addActionListener(this);
        viewPat.setActionCommand("ViewMyPatients");
        panel.add(viewPat);

        uptPat = new JButton("Update Patients");
        uptPat.setBounds(40, 200, 200, 40);
        formatChildren(uptPat);
        uptPat.addActionListener(this);
        uptPat.setActionCommand("UpdatePatientsProfile");
        panel.add(uptPat);
        
        frame.setSize(310, 350);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
    
    void viewMyPatients() {
        
        JFrame frame1 = new JFrame("View My Patients");
        frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame1.setLayout(new BorderLayout());
        String columns[] = {"Patient ID", "First Name", "Last Name", "Diagnosis", "Date of Birth", "Date of Admission","Date of Discharge", "Age", "Phone Number", "Floor No", "Room No"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);
        JTable table1 = new JTable();
        table1.setModel(model);
        table1.setBounds(30, 40, 200, 300);
        JScrollPane sp = new JScrollPane(table1);
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "root");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from patient where patient_id in (select patient_id from treatment where doctor_id = " + docID + ")");
            while (rs.next()) {
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
    
  
    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = ((JButton) e.getSource()).getActionCommand();

        switch (actionCommand) {
            case "UpdateMyProfile":
                new UpdMyProfileDoc(docID);
                break;
            case "ViewMyPatients":
                viewMyPatients();
                break;
            case "UpdatePatientsProfile":
                new UpdPatProfileInDoc(docID);
                break;
        }
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

    

}
