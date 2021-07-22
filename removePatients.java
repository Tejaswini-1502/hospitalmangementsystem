
package hospitalmangementsystem;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.sql.*;
public class removePatients {
    JFrame frame;
    JPanel panel;
    JLabel heading;
    JLabel head1;
    JLabel patID;
    JTextField patIDText;
    JButton remove;
    
    removePatients()
    {
        frame = new JFrame();
        panel = new JPanel();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(panel);
        frame.setTitle("Remove Patient");
        frame.setResizable(false);
        panel.setLayout(null);
        panel.setBackground(Color.white);
        
        heading = new JLabel("REMOVE PATIENT");
        heading.setFont(new Font("Times New Roman", Font.BOLD, 20));
        heading.setBounds(170, 18, 200, 30);
        panel.add(heading);
        
        head1 = new JLabel("To remove a Patient, enter ID below.");
        head1.setFont(new Font("Times New Roman", Font.BOLD, 18));
        head1.setBounds(15, 70, 400, 30);
        panel.add(head1);
        
        patID = new JLabel("PATIENT ID");
        patID.setFont(new Font("Verdana",Font.PLAIN,15));
        patID.setBounds(80, 150, 100, 30);
        panel.add(patID);
        patIDText = new JTextField(30);
        patIDText.setBounds(210, 150, 180, 25);
        panel.add(patIDText);
        
        remove = new JButton("REMOVE");
        remove.setFont(new Font("Arial", Font.PLAIN, 18));
        remove.setBounds(170, 230, 140, 40);
        remove.setFocusable(false);
        remove.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                //remove patient
                try {
                    String patient_id = patIDText.getText();
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "root");
                    PreparedStatement pst;
                    pst = con.prepareStatement("delete from patient where patient_id = ?");
                    pst.setString(1, patient_id);
                    pst.executeUpdate(); 
                    con.close();
                    JOptionPane.showMessageDialog(new JPanel(),"Record removed Successfully.");

                } catch (SQLException ex) {
                    
                    JOptionPane.showMessageDialog(new JPanel(),ex);
                }
            }
        });
        panel.add(remove);
        
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
         
}
