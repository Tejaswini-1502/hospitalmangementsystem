package hospitalmangementsystem;

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

final public class Patient implements ActionListener {

    JFrame frame;
    JPanel panel;
    JLabel heading;
    
    JButton uptPat;
    JButton paycharges;
    JButton ContactDoc;
    Integer patID;
    public Patient(int pat_id) {
        
        patID = pat_id;
        frame = new JFrame();
        panel = new JPanel();
        
        
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(panel);
        frame.setResizable(false);
        frame.setTitle("PATIENT PORTAL");
        panel.setLayout(null);
        panel.setBackground(Color.darkGray);

        heading = new JLabel("We hope you're doing great !");
        heading.setBounds(10, 20, 400, 40);
        heading.setFont(new Font("Arial", Font.BOLD, 17));
        heading.setForeground(Color.white);
        panel.add(heading);

        uptPat = new JButton("Update My Profile");
        uptPat.setBounds(20, 80, 250, 40);
        formatChildren(uptPat);
        uptPat.addActionListener(this);
        uptPat.setActionCommand("UpdateMyProfile");
        panel.add(uptPat);

        paycharges = new JButton("Pay Treatment Charges");
        paycharges.setBounds(20, 140, 250, 40);
        formatChildren(paycharges);       
        paycharges.addActionListener(this);
        paycharges.setActionCommand("payCharges");
        panel.add(paycharges);

        ContactDoc = new JButton("Contact Doctor");
        ContactDoc.setBounds(20, 200, 250, 40);
        formatChildren(ContactDoc);
        ContactDoc.addActionListener(this);
        ContactDoc.setActionCommand("ContactDoc");
        panel.add(ContactDoc);
        
        frame.setSize(310, 350);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = ((JButton) e.getSource()).getActionCommand();

        switch (actionCommand) {
            case "UpdateMyProfile":
                new UpdMyProfilePat(patID);
                break;
            case "payCharges":
                new payTreatmentCharges(patID);
                break;
            case "ContactDoc":
                new ContactDoctor(patID);
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
    
    

