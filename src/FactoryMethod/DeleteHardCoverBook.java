package FactoryMethod;

import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.PreparedStatement;

import BusinessObjects.Book;
import BusinessObjects.HardcoverBook;
import Commands.DeleteBook;

public class DeleteHardCoverBook extends DeleteBook {

	public DeleteHardCoverBook(int BookId) {
		super(BookId);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void queryDB() {
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT * FROM hardcover_book_master WHERE idhardcover_book_Master = " + BookId + "");
			stmt = con.createStatement();
			stmt.executeUpdate("DELETE FROM hardcover_book_master WHERE idhardcover_book_Master = " + BookId + "");
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Book processResult() {
		try {
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
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return book;
	}

	@Override
	public void undoQueryDB() {
		int newId = 0;
		 try {
				PreparedStatement stm = (PreparedStatement) con.prepareStatement("INSERT INTO `hardcover_book_master` (`hardcover_book_Name`, `hardcover_book_Author`, "
						+ "`hardcover_book_Publisher`, `hardcover_book_ISBN`, `hardcover_book_AvailableYN`, "
						+ "`hardcover_book_Edition`, `hardcover_book_Weight`, `hardcover_book_NoOfPages`, "
						+ "`hardcover_book_Dimensions`) VALUES  ("
						+ "'" + book.getBookName() + "'," 
						+ "'" + book.getBookAuthor() + "',"
						+ "'" + book.getBookPublisher() + "'," 
						+ "'" + book.getBookISBN() + "',"
						+ "'" + book.getBookAvailableYN() + "'," 
						+ "'" + book.getBookEdition() + "',"
						+ "'" + ((HardcoverBook) book).getBookWeight() + "'," 
						+ "'" + ((HardcoverBook) book).getBookNoOfPages() + "',"
						+ "'" + ((HardcoverBook) book).getBookDimensions() +  "');", Statement.RETURN_GENERATED_KEYS);
				stm.executeUpdate();
				rs = stm.getGeneratedKeys();
				if (rs.next()) {
					  newId = rs.getInt(1);
					}
				BookId = newId;
			} 
		    catch(SQLException e){			
		    	e.printStackTrace();
		    }
		
	}

}
