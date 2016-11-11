package FactoryMethod;

import java.sql.SQLException;

import BusinessObjects.HardcoverBook;
import Commands.FetchBooks;

public class FetchHardCoverBook extends FetchBooks {
	
	public FetchHardCoverBook(String bookName) {
		super(bookName);
	}

	@Override
	public void queryDB() {
		String strQuery;
		try
		{
			stmt = con.createStatement();
			strQuery = "SELECT * FROM hardcover_book_master ";
			if(bookName != ""){
				strQuery = strQuery + "WHERE hardcover_book_Name like '%" + bookName + "%' ";
			}
			rs = stmt.executeQuery(strQuery);
			while (rs.next()) 
			{  			
				bookList.add(
				new HardcoverBook(rs.getInt("idhardcover_book_Master"), 
						rs.getString("hardcover_book_Name"), 
						rs.getString("hardcover_book_Author"), 
						rs.getString("hardcover_book_Publisher"),
						rs.getString("hardcover_book_ISBN"),
						rs.getString("hardcover_book_AvailableYN"), 
						rs.getString("hardcover_book_Edition"), 
						rs.getString("hardcover_book_NoOfPages"),
						rs.getString("hardcover_book_Weight"),
						rs.getString("hardcover_book_Dimensions"))
				);
			} 
		} 
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void undoQueryDB() {
		// TODO Auto-generated method stub
		
	}
}
