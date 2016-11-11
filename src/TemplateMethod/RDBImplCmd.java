package TemplateMethod;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import BusinessObjects.Book;

public abstract class RDBImplCmd {
	protected Connection con = null;
	protected Statement stmt = null;
	protected int BookId;
	protected String bookName;
	protected Book book;
	protected List<Book> bookList = new ArrayList<>();
	protected ResultSet rs;
	//protected Object result;
	
	public void execute()
	{
		connectDB();
		queryDB();
		processResult();
		disconnectDB();
	}
		
	public void connectDB() 
	{
		String driver = "com.mysql.jdbc.Driver";
		try
		{
			Class.forName(driver);
					
		} 
		catch(ClassNotFoundException e)
		{
			System.err.print("Failed to Load driver.");
		}
		try
		{
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys?" +
                    "user=root&password=root123");
		}
		catch(SQLException e)
		{
			System.err.print("Failed to connect.");
		}
	}
	
	public void disconnectDB() 
	{
		try 
		{
			if (rs!=null){
				rs.close();
			}
			stmt.close();
			con.close();
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void undo()
	{
		connectDB();
		undoQueryDB();
		processResult();
		disconnectDB();	
	}
	
	public abstract void queryDB();
	
	public abstract Book processResult();
	
	public abstract void undoQueryDB();

}
