package FactoryMethod;

import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.PreparedStatement;

import BusinessObjects.Book;
import BusinessObjects.Ebook;
import Commands.DeleteBook;

public class DeleteEBook extends DeleteBook {

	public DeleteEBook(int BookId) {
		super(BookId);
	}
	
	@Override
	public void queryDB() 
	{		
		try 
		{
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT * FROM ebook_master WHERE idEBook_Master = " + BookId + "");
			stmt = con.createStatement();
			stmt.executeUpdate("DELETE FROM ebook_master WHERE idEBook_Master = " + BookId + "");
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

	@Override
	public void undoQueryDB() {
		int newId = 0;
		try
		{
			PreparedStatement stm = (PreparedStatement) con.prepareStatement("INSERT INTO `ebook_master` (`EBook_Name`, "
					+ "`EBook_Author`, `EBook_Publisher`, "
					+ "`EBook_ISBN`, `EBook_AvailableYN`, `EBook_Edition`, `EBook_FileSize`, `EBook_PrintLength`, "
					+ "`EBook_MultipleDeviceUsage`) VALUES  ("
					+ "'" + book.getBookName() + "'," 
					+ "'" + book.getBookAuthor() + "',"
					+ "'" + book.getBookPublisher() + "'," 
					+ "'" + book.getBookISBN() + "',"
					+ "'" + book.getBookAvailableYN() + "'," 
					+ "'" + book.getBookEdition() + "',"
					+ "'" + ((Ebook) book).getFileSize() + "'," 
					+ "'" + ((Ebook)book).getPrintLength() + "',"
					+ "'" + ((Ebook)book).getMultipleDeviceUsage() +  "');", Statement.RETURN_GENERATED_KEYS);
			stm.executeUpdate();
			rs = stm.getGeneratedKeys();
			if (rs.next()) {
				  newId = rs.getInt(1);
				}
			BookId = newId;
		} 
	    catch(SQLException e)
	    {			
	    	e.printStackTrace();
	    }
	}
	
	@Override
	public Book processResult() 
	{
		if(rs!=null){
			try {
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
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return book;
	}

}
