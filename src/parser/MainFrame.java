/*import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class MainFrame {
	
	
	public static void main (String[] args){    
	  JFrame frame = new JFrame("Test");
	  frame.setVisible(true);
	  frame.setSize(500,500);
	  frame.setContentPane(new JLabel(new ImageIcon("C:\\Users\\Nagender\\Downloads\\i1.png")));
	  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  frame.setLayout(new FlowLayout());
	  //JPanel panel = new JPanel();
	 // frame.add(panel);
	  JButton button = new JButton("submit selection criteria");
	  frame.add(button);
	  //button.addActionListener (new Action1());

	  JButton button2 = new JButton("submit resume");
	  frame.add(button2);
	 // button.addActionListener (new Action2()); 
	}
}
*/
package parser;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.Writer;

import javax.swing.JOptionPane;

import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
class MainFrame extends JFrame implements ActionListener
{
JButton b1;
JButton b2,login,b3,b4;
static JFrame j;
static int total_no_constraints;
JFileChooser chooser;
String choosertitle="select resume";
JLabel uname,pwd;
JTextField name,path;
JPasswordField passwd;
static JComboBox edq;
static String[] combotypes={"Btech/degree","Degree(BSc)","Degree(BCom)","Btech","PG","SSC","Intermediate","other"};
static JTextField s,i,b,ex;
static JTextArea ipskills,strenghts;
static String document="",fullname;
DbConn db=new DbConn();
	public MainFrame()
	{
	setTitle("Parser For Resumes");
	setSize(600,600);
	setLocationRelativeTo(null);
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	setVisible(true);
	
	setLayout(new BorderLayout());
	setContentPane(new JLabel(new ImageIcon("C:\\Users\\Nagender\\Downloads\\i1.png")));
	setLayout(new FlowLayout());
	b1=new JButton("selection criteria");
	b1.setActionCommand("employer");
	b1.addActionListener(this);
	b2=new JButton("submit resume");
	b2.setActionCommand("candidate");
	b2.addActionListener(this);
	add(b1);
	add(b2);
	// Just for refresh :) Not optional!
	setSize(399,399);
	setSize(400,400);
	}
	public void inputFrame()
	{
			JLabel eq,cutoff,Techskills,experience,ssc,inter,strenghtslabel,bd;
			
		     j= new JFrame("Input selection criteria");
			j.setSize(390,390);
			j.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			j.setLocationRelativeTo(null);
			j.setVisible(true);
			eq=new JLabel("Educational Qualification required");
			cutoff=new JLabel("    cutoff for SSC,INTER and BTECH/DEGREE if required                             \n");
			Techskills=new JLabel("Technical Skills required(seperate different skills using comma)");
			strenghtslabel=new JLabel("Strenghts or strong points");
			experience=new JLabel("Experience required(in years)");
			//branch=new JLabel("If BTECH mention Branch(if any specific)");
			edq=new JComboBox(combotypes);
			ssc=new JLabel("SSC");
			inter=new JLabel("Intermediate");
			bd=new JLabel("btech/degree");
			s=new JTextField(10);
			i=new JTextField(10);
			b=new JTextField(10);
			login=new JButton("submit");
			ipskills=new JTextArea(3,20);
			strenghts=new JTextArea(2,20);
			ex=new JTextField(10);
			j.setLayout(new FlowLayout());
			j.add(eq);
			j.add(edq);
			j.add(cutoff);
			j.add(ssc);
			j.add(s);
			j.add(inter);
			j.add(i);
			j.add(bd);
			j.add(b);
			j.add(Techskills);
			j.add(ipskills);
			j.add(strenghtslabel);
			j.add(strenghts);
			j.add(experience);
			j.add(ex);
			j.add(login);
			login.setActionCommand("submit");
			login.addActionListener(this);
	}
	public void employerFrame(){
		j=new JFrame("Employer Options");
		j.setSize(200,200);
		j.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		j.setLocationRelativeTo(null);
		j.setVisible(true);
		b3=new JButton("Criteria selection Frame");
		b4=new JButton("Get Resumes");
		j.setLayout(new FlowLayout());
		j.add(b3);
		j.add(b4);
		b3.setActionCommand("criteria");
		b3.addActionListener(this);
		b4.setActionCommand("getresumes");
		b4.addActionListener(this);
		
	}
	public void userFrame(){
		j= new JFrame("resume submitter");
		j.setSize(250, 200);
		j.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		j.setLocationRelativeTo(null);
		j.setVisible(true);
		login=new JButton("select");
		b3=new JButton("send");
		path=new JTextField(20);
		path.setEditable(false);
		j.setLayout(new FlowLayout());
		j.add(path);
		j.add(login);
		j.add(b3);
		login.setActionCommand("select");
		login.addActionListener(this);
		b3.setActionCommand("send");
		b3.addActionListener(this);
	}
	public static void main(String args[])
	{
	MainFrame m=new MainFrame();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if("criteria".equals(e.getActionCommand()))
		{
			inputFrame();
		}
		else if("getresumes".equals(e.getActionCommand())){
			System.out.println("asking to send resumes");
			db.connection();
			int c=0;
			String[] filepath=new String[10];
			String[] arr=db.retrieveResumes();
			Word word=new Word();
			System.out.println(arr.length);
			for(int i=0;i<arr.length;i++){
				word.readMyDocument(arr[i]);
				db.connection();
				int match=db.retrieve();
				System.out.println(match);
				arr[i]=arr[i].replace("\\","/");
				//System.out.println(document);
				if(match==total_no_constraints)
				{
					System.out.println("updating c");
					filepath[c]=arr[i];
					c++;
				//MailResume mail=new MailResume();
				//mail.send(document);
				}
				total_no_constraints=0;
			}
			MailResume mail=new MailResume();
			System.out.println(c);
			mail.send(filepath,c);
		}
		else if("employer".equals(e.getActionCommand()))
		{
		    j= new JFrame("Authentication of employer");
			j.setSize(250, 200);
			j.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			j.setLocationRelativeTo(null);
			j.setVisible(true);
			uname=new JLabel("uname");
			pwd= new JLabel("pwd");
			name=new JTextField(15);
			passwd=new JPasswordField(15);
			login=new JButton("login");
			j.setLayout(new FlowLayout());
			j.add(uname);
			j.add(name);
			j.add(pwd);
			j.add(passwd);
			j.add(login);
			login.setActionCommand("login");
			login.addActionListener(this);
			
		}
		else if("candidate".equals(e.getActionCommand())){
			j=new JFrame("candidate section");
			JLabel info=new JLabel("New users to submit your resume get urself registered");
			j.setSize(400, 400);
			j.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			j.setLocationRelativeTo(null);
			j.setVisible(true);
			b3=new JButton("register");
			b4=new JButton("login");
			j.setLayout(new FlowLayout());
			j.add(info);
			j.add(b3);
			j.add(b4);
			b3.setActionCommand("register");
			b3.addActionListener(this);
			b4.setActionCommand("ulogin");
			b4.addActionListener(this);
						
			/*j= new JFrame("resume submitter");
			j.setSize(250, 200);
			j.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			j.setLocationRelativeTo(null);
			j.setVisible(true);
			login=new JButton("select");
			b3=new JButton("send");
			path=new JTextField(20);
			path.setEditable(false);
			j.setLayout(new FlowLayout());
			j.add(path);
			j.add(login);
			j.add(b3);
			login.setActionCommand("select");
			login.addActionListener(this);
			b3.setActionCommand("send");
			b3.addActionListener(this);*/
		}
		else if("register".equals(e.getActionCommand())){
			j=new JFrame("candidate registration form");
			j.setSize(400, 400);
			j.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			j.setLocationRelativeTo(null);
			j.setVisible(true);
			JLabel firstname=new JLabel("Enter Full Name");
			path=new JTextField(30);
			uname=new JLabel("username");
			pwd=new JLabel("password");
			name=new JTextField(10);
			passwd=new JPasswordField(10);
			JButton submit=new JButton("usubmit");
			j.setLayout(new FlowLayout());
			j.add(firstname);
			j.add(path);
			j.add(uname);
			j.add(name);
			j.add(pwd);
			j.add(passwd);
			j.add(submit);
			submit.setActionCommand("createacc");
			submit.addActionListener(this);
		}
		else if("createacc".equals(e.getActionCommand())){
			db.connection();
			db.insertuser(path.getText(),name.getText(),new String(passwd.getPassword()));
			JOptionPane.showMessageDialog(null,"account created successfully","Acknowledgement",JOptionPane.INFORMATION_MESSAGE);
			j.dispose();
		}
		else if("ulogin".equals(e.getActionCommand())){
			j= new JFrame("Authentication of user");
			j.setSize(250, 250);
			j.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			j.setLocationRelativeTo(null);
			j.setVisible(true);
			uname=new JLabel("uname");
			pwd= new JLabel("pwd");
			name=new JTextField(10);
			passwd=new JPasswordField(10);
			login=new JButton("login");
			j.setLayout(new FlowLayout());
			j.add(uname);
			j.add(name);
			j.add(pwd);
			j.add(passwd);
			j.add(login);
			login.setActionCommand("uauthen");
			login.addActionListener(this);
		}
		else if("uauthen".equals(e.getActionCommand())){
			db.connection();
			int k;
			k=db.authenticateUser(name.getText(),new String(passwd.getPassword()));
			if(k==1){
				j.dispose();
				fullname=name.getText();
				userFrame();
			}
		}
		else if("login".equals(e.getActionCommand())){
			db.connection();
			int k;
			k=db.authenticate(name.getText(),new String(passwd.getPassword()));
			if(k==1){
				j.dispose();
				employerFrame();
				//inputFrame();
			}
			
		}
		else if("submit".equals(e.getActionCommand())){
			db.connection();
			db.insert(edq.getSelectedItem().toString(),s.getText(),i.getText(),b.getText(),ipskills.getText(),strenghts.getText(),ex.getText());
			j.dispose();
		}
		else if("send".equals(e.getActionCommand())){
			if(document=="")
			{
				JOptionPane.showMessageDialog(null,"Select a file","NO file error",JOptionPane.WARNING_MESSAGE);
			}
			else{
				JOptionPane.showMessageDialog(null,"File submitted successfully","Acknowledgement",JOptionPane.INFORMATION_MESSAGE);
				db.connection();
				System.out.println(document);
				db.storeResume(fullname,document);
				j.dispose();
			}
			/*Word word=new Word();
			word.readMyDocument(document);
			db.connection();
			int match=db.retrieve();
			document=document.replace("\\","/");
			System.out.println(document);
			if(match==total_no_constraints)
			{
				MailResume mail=new MailResume();
				mail.send(document);
			}
			*/
		}
		else if("select".equals(e.getActionCommand())){
			chooser = new JFileChooser();
			chooser.setCurrentDirectory(new java.io.File("."));
			chooser.setDialogTitle(choosertitle);

			chooser.setAcceptAllFileFilterUsed(true);

			if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			path.setText(chooser.getSelectedFile().toString());
			document=path.getText();
			}
			else {
			path.setText("No Selection");
			}
		}
	}
}