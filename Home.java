
package hospitalmangementsystem;

import static java.awt.Color.*;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Home{
    JFrame frame;
    JLabel heading;
    JButton Doctor;
    JButton Patient;
    JButton Admin;
    JLabel address;
    JLabel addrText;
    JPanel panelAdP;
    JPanel Contact;
    JLabel contact;
    JLabel ContactMail;
    JLabel ContactPhone;
    
    public void format(JButton button){
        button.setBackground(pink);
        button.setForeground(black);
        button.setBorderPainted(false);
        button.setFont(new Font("Times New Roman", Font.BOLD, 20));
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
            button.setBackground(DARK_GRAY);
            button.setForeground(white);
         }

      });
    }
    
    public Home()
    {
        frame = new JFrame("BMS HOSPITAL");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize.width, screenSize.height);
        frame.setLocationRelativeTo(null);
        ImageIcon bg = new ImageIcon("C:\\Users\\user\\Desktop\\4thSemDocs\\JAVA\\HospitalMangementSystem\\src\\hospitalmangementsystem\\Hospital.jpg");
        Image img = bg.getImage();
        Image temp_img = img.getScaledInstance(screenSize.width, screenSize.height, Image.SCALE_SMOOTH);
        bg = new ImageIcon(temp_img);
        JLabel background = new JLabel("",bg,JLabel.CENTER);
        background.setBounds(0,0,screenSize.width,screenSize.height);
        frame.add(background);
        
        heading = new JLabel("Welcome to BMS Hospital");
        heading.setBounds(screenSize.width/3, 20, 500, 50);
        heading.setFont(new Font("Arial", Font.BOLD, 40));
        heading.setForeground(blue);
        background.add(heading);
        
        Doctor = new JButton("DOCTOR");
        Doctor.setBounds(screenSize.width-250, 90, 170, 60);
        format(Doctor);
        Doctor.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                new Login("DOCTOR");
            }
        });
        background.add(Doctor);
        
        Patient = new JButton("PATIENT");
        Patient.setBounds(screenSize.width-250, 170, 170, 60);
        format(Patient);
        Patient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Login("PATIENT");
            }
        });
        background.add(Patient);
        
        Admin = new JButton("ADMIN");
        Admin.setBounds(screenSize.width-250, 250, 170, 60);
        format(Admin);
        Admin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Login("ADMIN");
            }
        });
        background.add(Admin);
        
        panelAdP = new JPanel();
        panelAdP.setBounds(20,screenSize.height-200,300, 90);
        panelAdP.setBackground(black);
        background.add(panelAdP);
        
        address = new JLabel("ADDRESS");
        address.setBounds(20,screenSize.height-300,300, 100);
        address.setFont(new Font("Times New Roman", Font.BOLD, 22));
        address.setForeground(yellow);
        panelAdP.add(address);
        
        addrText = new JLabel("Basavanagudi ,Bengaluru-560019");
        addrText.setFont(new Font("Times New Roman", Font.BOLD, 18));
        addrText.setForeground(white);
        panelAdP.add(addrText);
        
        Contact = new JPanel();
        Contact.setBounds(350,screenSize.height-200,300, 90);
        Contact.setBackground(black);
        background.add(Contact);
        
        contact = new JLabel("CONTACT");
        contact.setBounds(350,700,300, 100);
        contact.setFont(new Font("Times New Roman", Font.BOLD, 22));
        contact.setForeground(yellow);
        Contact.add(contact);
        
        ContactMail = new JLabel("EMAIL: bmsh@gmail.com");
        ContactMail.setFont(new Font("Times New Roman", Font.BOLD, 18));
        ContactMail.setForeground(white);
        Contact.add(ContactMail);
        
        ContactPhone = new JLabel("PHONE: 080 2660 1212");
        ContactPhone.setFont(new Font("Times New Roman", Font.BOLD, 18));
        ContactPhone.setForeground(white);
        Contact.add(ContactPhone);
        
        frame.setVisible(true);
    }
    public static void main(String[] args) {
        new Home();
    }
}
    

