package FactoryMethod;

import java.sql.SQLException;

import BusinessObjects.Book;
import BusinessObjects.Ebook;
import Commands.GetBook;

public class GetEBook extends GetBook {

	public GetEBook(int BookId) {
		super(BookId);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void queryDB() 
	{
		try
		{
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT * FROM ebook_master WHERE idEBook_Master = '" + BookId + "'");
			while(rs.next()){
			book = new Ebook(rs.getInt("idEbook_Master"), 
					rs.getString("EBook_Name"), 
					rs.getString("EBook_Author"), 
					rs.getString("EBook_Publisher"),
					rs.getString("EBook_ISBN"),
					rs.getString("EBook_AvailableYN"), 
					rs.getString("EBook_Edition"), 
					rs.getString("EBook_FileSize"),
					rs.getString("EBook_PrintLength"),
					rs.getString("EBook_MultipleDeviceUsage"));
			}
		} 
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
	}

	@Override
	public Book processResult() 
	{
		return book;
	}

}
