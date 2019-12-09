package parser;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class DbConn {
	public Connection conn=null;
	public PreparedStatement statement1=null;
	public PreparedStatement statement2=null;
	public String Jdbc_driver="com.mysql.jdbc.Driver";
	static String sourceurl="jdbc:mysql://localhost/shuba";
	public void connection(){
		try{
			Class.forName(Jdbc_driver);
			conn=DriverManager.getConnection(sourceurl,"root","shuba");
			System.out.println("connecting to database");
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null,"not connected"+e.getMessage());
		}
	}
	public int authenticate(String uname,String pwd){
		String k=uname;
		String ins1="select pwd from employer where uname='"+uname+"'";
		String p;
		System.out.println(ins1);
		try {
			statement1=conn.prepareStatement(ins1);
			ResultSet result=statement1.executeQuery(ins1);
			while(result.next()){
				p=result.getString(1);
				System.out.println(p);
				if(p.equals(pwd)){
					System.out.println("matching pwd");
					conn.close();
					return 1;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("something went wrong while authenticating");
		}
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	public int authenticateUser(String uname,String pwd){
		String k=uname;
		String ins1="select pwd from user where uname='"+uname+"'";
		String ins2="select firstname from user where uname='"+uname+"'";
		String p;
		System.out.println(ins1);
		try {
			statement1=conn.prepareStatement(ins1);
			ResultSet result=statement1.executeQuery(ins1);
			while(result.next()){
				p=result.getString(1);
				System.out.println(p);
				if(p.equals(pwd)){
					System.out.println("matching pwd");
				statement1=conn.prepareStatement(ins2);
				ResultSet result2=statement1.executeQuery(ins2);
				while(result2.next()){
					MainFrame.fullname=result.getString(1);
				}
					conn.close();
					return 1;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("something went wrong while authenticating");
		}
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	public void storeResume(String fname,String resumepath)
	{
		String ins="insert into resume values(?,?)";
		String ins1="select fullname from resume;";
		int count=0;
		String com;
		try {
			statement1=conn.prepareStatement(ins1);
			ResultSet result=statement1.executeQuery(ins1);
			System.out.println("stmnt executed");
			while(result.next())
			{
				com=result.getString(1);
				if(com.equals(fname)){
					count++;
				}
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(count!=0)
		{
			System.out.println(resumepath);
			ins="delete from resume where fullname='"+fname+"';";
			try {
				statement1=conn.prepareStatement(ins);
				int rowsUpdated=statement1.executeUpdate();
				count=0;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(count==0){
		try {
			ins="insert into resume values(?,?)";
			statement1=conn.prepareStatement(ins);
			statement1.setString(1,fname);
			statement1.setString(2,resumepath);
			int rowsUpdated=statement1.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	
			
		
	}
	public String[] retrieveResumes(){
		int count=0;
		String ins="select resumepath from resume";
		try {
			statement1=conn.prepareStatement(ins);
			ResultSet result=statement1.executeQuery(ins);
			System.out.println("stmnt executed");
			while(result.next())
			{
				count++;
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[] filepath=new String[count];
		count=0;
		try {
			statement1=conn.prepareStatement(ins);
			ResultSet result=statement1.executeQuery(ins);
			System.out.println("stmnt executed");
			while(result.next())
			{
				filepath[count]=result.getString(1);
				count++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return filepath;
	}
	public void insert(String education,String ssc,String inter,String bdp,String techskills,String stren,String exp)
	{
		String ins="insert into constraints values(?,?)";
		String ins2="delete from constraints";
		System.out.println(education);
		System.out.println(ssc);
		System.out.println(inter);
		System.out.println(bdp);
		System.out.println(techskills);
		System.out.println(stren);
		System.out.println(exp);
		try {
			statement1=conn.prepareStatement(ins2);
			int rowsUpdated=statement1.executeUpdate();
			statement1=conn.prepareStatement(ins);
			statement1.setString(1,"education qualification");
			statement1.setString(2,education);
			rowsUpdated=statement1.executeUpdate();
			statement1=conn.prepareStatement(ins);
			statement1.setString(1,"ssc");
			statement1.setString(2,ssc);
			rowsUpdated=statement1.executeUpdate();statement1=conn.prepareStatement(ins);
			statement1.setString(1,"intermediate");
			statement1.setString(2,inter);
			rowsUpdated=statement1.executeUpdate();statement1=conn.prepareStatement(ins);
			statement1.setString(1,"btech/degree");
			statement1.setString(2,bdp);
			rowsUpdated=statement1.executeUpdate();statement1=conn.prepareStatement(ins);
			statement1.setString(1,"technical skills");
			statement1.setString(2,techskills);
			rowsUpdated=statement1.executeUpdate();statement1=conn.prepareStatement(ins);
			statement1.setString(1,"strenghts");
			statement1.setString(2,stren);
			rowsUpdated=statement1.executeUpdate();statement1=conn.prepareStatement(ins);
			statement1.setString(1,"experience");
			statement1.setString(2,exp);
			rowsUpdated=statement1.executeUpdate();
			conn.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	public void insertuser(String fname,String uname,String pwd)
	{
		String ins="insert into user values(?,?,?)";
		try {
			statement1=conn.prepareStatement(ins);
			statement1.setString(1,fname);
			statement1.setString(2,uname);
			statement1.setString(3,pwd);
			int rowsUpdated=statement1.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public int retrieve()
	{
		String ins="select * from constraints;";
		String label,value;
		Read r;
		int count=0;
		try {
			statement1=conn.prepareStatement(ins);
			ResultSet result=statement1.executeQuery(ins);
			System.out.println("stmnt executed");
			while(result.next())
			{
				label=result.getString(1);
				value=result.getString(2);
				System.out.println(label+value);
				if(label.equals("btech/degree"))
				{
					
					try {
						r=new Read();
						count+=r.search("btech",value);
						MainFrame.total_no_constraints++;
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else if(label.equals("technical skills")){
					String[] arr=value.split(",");
					for(int i=0;i<arr.length;i++){
						r=new Read();
						MainFrame.total_no_constraints++;
						try {
							count+=r.search(label,arr[i]);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				else if(label.equals("strenghts")){
					String[] arr=value.split(",");
					for(int i=0;i<arr.length;i++){
						r=new Read();
						MainFrame.total_no_constraints++;
						try {
							count+=r.search(label,arr[i]);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				else{
					try{
					r=new Read();
					MainFrame.total_no_constraints++;
					count+=r.search(label,value);
					}
					catch(IOException e){
						e.printStackTrace();
					}
				}
				
			}
			System.out.println(count);
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
}
