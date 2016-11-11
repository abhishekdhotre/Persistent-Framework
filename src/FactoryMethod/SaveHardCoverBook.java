package FactoryMethod;

import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.PreparedStatement;

import BusinessObjects.Book;
import BusinessObjects.HardcoverBook;
import Commands.SaveBook;

public class SaveHardCoverBook extends SaveBook{

	public SaveHardCoverBook(Book book) {
		super(book);
	}

	@Override
	public void queryDB() {
		HardcoverBook hb = (HardcoverBook) book;
	    try
		{
			stmt = con.createStatement();
			PreparedStatement stm = (PreparedStatement) con.prepareStatement("INSERT INTO `hardcover_book_master` (`hardcover_book_Name`, `hardcover_book_Author`, "
					+ "`hardcover_book_Publisher`, `hardcover_book_ISBN`, `hardcover_book_AvailableYN`, "
					+ "`hardcover_book_Edition`, `hardcover_book_Weight`, `hardcover_book_NoOfPages`, "
					+ "`hardcover_book_Dimensions`) VALUES  ("
					+ "'" + hb.getBookName() + "'," 
					+ "'" + hb.getBookAuthor() + "',"
					+ "'" + hb.getBookPublisher() + "'," 
					+ "'" + hb.getBookISBN() + "',"
					+ "'" + hb.getBookAvailableYN() + "'," 
					+ "'" + hb.getBookEdition() + "',"
					+ "'" + hb.getBookWeight() + "'," 
					+ "'" + hb.getBookNoOfPages() + "',"
					+ "'" + hb.getBookDimensions() +  "');", Statement.RETURN_GENERATED_KEYS);
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
			stmt.executeUpdate("DELETE FROM hardcover_book_master WHERE idhardcover_book_Master = " + book.getBookId() + "");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
