package FactoryMethod;

import java.sql.SQLException;

import BusinessObjects.Book;
import BusinessObjects.HardcoverBook;
import Commands.UpdateBook;

public class UpdateHardCoverBook extends UpdateBook  {
	
	public UpdateHardCoverBook(Book book) {
		super(book);
	}

	@Override
	public void queryDB() {
		HardcoverBook hb = (HardcoverBook) book;
        try
		{
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT * FROM hardcover_book_master WHERE idhardcover_book_Master = " + hb.getBookId() + "");
			stmt = con.createStatement();
			stmt.executeUpdate("UPDATE hardcover_book_master SET hardcover_book_name = '" + hb.getBookName() + "',"
					+ " hardcover_book_Author='" + hb.getBookAuthor() + "'," 
					+ " hardcover_book_Publisher='" + hb.getBookPublisher() + "',"
					+ " hardcover_book_ISBN='" + hb.getBookISBN() + "'," 
					+ " hardcover_book_AvailableYN='" + hb.getBookAvailableYN().charAt(0) + "',"
					+ " hardcover_book_Edition='" + hb.getBookEdition() + "'," 
					+ " hardcover_book_NoOfPages='" + hb.getBookNoOfPages() + "'," 
					+ " hardcover_book_Weight='" + hb.getBookWeight() + "',"
					+ " hardcover_book_Dimensions='" + hb.getBookDimensions() + "'"
					+ " WHERE idhardcover_book_Master = " + hb.getBookId() + ";");
		} 
        catch(SQLException e)
        {
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
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT * FROM hardcover_book_master WHERE idhardcover_book_Master = " + book.getBookId() + "");
			stmt = con.createStatement();
			stmt.executeUpdate("UPDATE hardcover_book_master SET hardcover_book_name = '" + book.getBookName() + "',"
					+ " hardcover_book_Author='" + book.getBookAuthor() + "'," 
					+ " hardcover_book_Publisher='" + book.getBookPublisher() + "',"
					+ " hardcover_book_ISBN='" + book.getBookISBN() + "'," 
					+ " hardcover_book_AvailableYN='" + book.getBookAvailableYN().charAt(0) + "',"
					+ " hardcover_book_Edition='" + book.getBookEdition() + "'," 
					+ " hardcover_book_NoOfPages='" + ((HardcoverBook) book).getBookNoOfPages() + "'," 
					+ " hardcover_book_Weight='" + ((HardcoverBook) book).getBookWeight() + "',"
					+ " hardcover_book_Dimensions='" + ((HardcoverBook) book).getBookDimensions() + "'"
					+ " WHERE idhardcover_book_Master = " + book.getBookId() + ";");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
