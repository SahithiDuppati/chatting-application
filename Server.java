import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;

public class Server extends JFrame implements ActionListener{
    
    JPanel p1;
    JTextField t1;
    JButton b1;
    static JTextArea a1;
    
    static ServerSocket skt;
    static Socket s;
    static DataInputStream din;
    static DataOutputStream dout;
    
    Server(){
        
        p1 = new JPanel();
        p1.setLayout(null);
        p1.setBackground(new Color(7, 94, 84));
        p1.setBounds(0,0,400,60);
        add(p1); 
        //back arrow image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/3.png"));
        Image i2 = i1.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l1 = new JLabel(i3);
        l1.setBounds(5,17,30,30);
        p1.add(l1);
        
        l1.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent ae){
                System.exit(0);
            }
        });
        
        //user image
        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("icons/1.jpg"));
        Image i5 = i4.getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel l2 = new JLabel(i6);
        l2.setBounds(40,5,50,50);
        p1.add(l2);
        //video image
        ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("icons/video.png"));
        Image i8 = i7.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);
        ImageIcon i9 = new ImageIcon(i8);
        JLabel l5 = new JLabel(i9);
        l5.setBounds(250,18,30,30);
        p1.add(l5);
        //phone image
        ImageIcon i11 = new ImageIcon(ClassLoader.getSystemResource("icons/phone.png"));
        Image i12 = i11.getImage().getScaledInstance(35,30,Image.SCALE_DEFAULT);
        ImageIcon i13 = new ImageIcon(i12);
        JLabel l6 = new JLabel(i13);
        l6.setBounds(300,18,35,30);
        p1.add(l6);
        //3 dots image
        ImageIcon i14 = new ImageIcon(ClassLoader.getSystemResource("icons/2.png"));    
        Image i15 = i14.getImage().getScaledInstance(13,25,Image.SCALE_DEFAULT);
        ImageIcon i16 = new ImageIcon(i15);
        JLabel l7 = new JLabel(i16);
        l7.setBounds(350,18,13,25);
        p1.add(l7);
        
        
        JLabel l3 = new JLabel("Sahithi");
        l3.setFont(new Font("SAN_SERIF",Font.BOLD,18));
        l3.setForeground(Color.white);
        l3.setBounds(110,15,100,18);
        p1.add(l3);
        
        JLabel l4 = new JLabel("Active Now");
        l4.setFont(new Font("MONOSPACE",Font.PLAIN,10));
        l4.setForeground(Color.white);
        l4.setBounds(110,35,100,20);
        p1.add(l4);
        
        a1 = new JTextArea();
        a1.setBounds(5,65,390,430);
        a1.setFont(new Font("SAN_SERIF",Font.PLAIN,16));
        a1.setEditable(false);
        a1.setLineWrap(true);
        a1.setWrapStyleWord(true);
        add(a1);
        
        t1 = new JTextField();
        t1.setBounds(5,505,310,40);
        t1.setFont(new Font("SAN_SERIF", Font.PLAIN,16));
        add(t1);
        
        b1 = new JButton("Send");
        b1.setBounds(320,505,75,40);
        b1.setBackground(new Color(7, 94, 84));
        b1.setForeground(Color.white);
        b1.setFont(new Font("SAN_SERIF",Font.PLAIN,16));
        b1.addActionListener(this);
        add(b1);
        
        getContentPane().setBackground(Color.white);
        setLayout(null);
        setSize(400,550);
        setLocation(200,100);
        setUndecorated(true);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        try{
        String out = t1.getText();
        a1.setText(a1.getText() + "\n\t\t\t" + out);
        dout.writeUTF(out);
        t1.setText("");
        }catch(Exception e){
            
        }
        
    }
    
    public static void main(String[] args){
        new Server().setVisible(true);
        
        String msg = "";
        try{
            skt = new ServerSocket(6001);
            s = skt.accept();
            din = new DataInputStream(s.getInputStream());
            dout = new DataOutputStream(s.getOutputStream());
            
            msg = din.readUTF();
            a1.setText(a1.getText()+"\n"+msg);
            
            skt.close();
            s.close();
            
            
        }catch(Exception e){
            
        }
    }
}
