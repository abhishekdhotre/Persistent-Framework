package FactoryMethod;

import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.PreparedStatement;

import BusinessObjects.Book;
import BusinessObjects.Ebook;
import Commands.SaveBook;

public class SaveEBook extends SaveBook {
	public SaveEBook(Book book)
	{
		super(book);
	}
	
	@Override
	public void queryDB() {
		Ebook eb = (Ebook) book;
	    try
		{
	    	stmt = con.createStatement();
	    	PreparedStatement stm = (PreparedStatement) con.prepareStatement("INSERT INTO `ebook_master` (`EBook_Name`, `EBook_Author`, `EBook_Publisher`, "
					+ "`EBook_ISBN`, `EBook_AvailableYN`, `EBook_Edition`, `EBook_FileSize`, `EBook_PrintLength`, "
					+ "`EBook_MultipleDeviceUsage`) VALUES  ("
					+ "'" + eb.getBookName() + "'," 
					+ "'" + eb.getBookAuthor() + "',"
					+ "'" + eb.getBookPublisher() + "'," 
					+ "'" + eb.getBookISBN() + "',"
					+ "'" + eb.getBookAvailableYN() + "'," 
					+ "'" + eb.getBookEdition() + "',"
					+ "'" + eb.getFileSize() + "'," 
					+ "'" + eb.getPrintLength() + "',"
					+ "'" + eb.getMultipleDeviceUsage() +  "');", Statement.RETURN_GENERATED_KEYS);
			stm.executeUpdate();
			rs = stm.getGeneratedKeys();
			if (rs.next()) {
				  book.setBookId(rs.getInt(1));
				}
		} 
	    catch(SQLException e)
	    {			
	    	e.printStackTrace();
	    }
		
	}

	@Override
	public void undoQueryDB() {
		try {
			stmt = con.createStatement();
			stmt.executeUpdate("DELETE FROM ebook_master WHERE idEBook_Master = " + book.getBookId() + "");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
