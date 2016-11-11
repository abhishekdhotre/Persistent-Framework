package FactoryMethod;

import java.sql.SQLException;

import BusinessObjects.Book;
import BusinessObjects.HardcoverBook;
import Commands.GetBook;

public class GetHardCoverBook extends GetBook {
	
	public GetHardCoverBook(int BookId) {
		super(BookId);
	}
	
	@Override
	public void queryDB() 
	{
		try
		{
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT * FROM hardcover_book_master WHERE idhardcover_book_Master = '" + BookId + "'");
			while(rs.next()){
			book = new HardcoverBook(rs.getInt("idhardcover_book_Master"), 
					rs.getString("hardcover_book_Name"), 
					rs.getString("hardcover_book_Author"), 
					rs.getString("hardcover_book_Publisher"),
					rs.getString("hardcover_book_ISBN"),
					rs.getString("hardcover_book_AvailableYN"), 
					rs.getString("hardcover_book_Edition"), 
					rs.getString("hardcover_book_NoOfPages"),
					rs.getString("hardcover_book_Weight"),
					rs.getString("hardcover_book_Dimensions"));
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
