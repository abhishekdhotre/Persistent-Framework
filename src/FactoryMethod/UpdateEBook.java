package FactoryMethod;

import java.sql.SQLException;

import BusinessObjects.Book;
import BusinessObjects.Ebook;
import Commands.UpdateBook;

public class UpdateEBook extends UpdateBook {

	public UpdateEBook(Book book) {
		super(book);
	}

	@Override
	public void queryDB() {
		Ebook eb = (Ebook) book;
        try
		{
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT * FROM ebook_master WHERE idEBook_Master = " + eb.getBookId() + "");
			stmt = con.createStatement();
			stmt.executeUpdate("UPDATE ebook_master SET EBook_Name = '" + eb.getBookName() + "',"
					+ " EBook_Author='" + eb.getBookAuthor() + "'," 
					+ " EBook_Publisher='" + eb.getBookPublisher() + "',"
					+ " EBook_ISBN='" + eb.getBookISBN() + "'," 
					+ " EBook_AvailableYN='" + eb.getBookAvailableYN().charAt(0) + "',"
					+ " EBook_Edition='" + eb.getBookEdition() + "'," 
					+ " EBook_FileSize='" + eb.getFileSize() + "',"
					+ " EBook_PrintLength='" + eb.getPrintLength() + "'," 
					+ " EBook_MultipleDeviceUsage='" + eb.getMultipleDeviceUsage() + "'"
					+ " WHERE idEBook_Master = " + eb.getBookId() + ";");
		} 
        catch(SQLException e)
        {
        	e.printStackTrace();
		}	
	}

	@Override
	public Book processResult() 
	{
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
		return book;
	}
	
	@Override
	public void undoQueryDB() {
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT * FROM ebook_master WHERE idEBook_Master = " + book.getBookId() + "");
			stmt = con.createStatement();
			stmt.executeUpdate("UPDATE ebook_master SET EBook_Name = '" +book.getBookName() + "',"
					+ " EBook_Author='" + book.getBookAuthor() + "'," 
					+ " EBook_Publisher='" + book.getBookPublisher() + "',"
					+ " EBook_ISBN='" + book.getBookISBN() + "'," 
					+ " EBook_AvailableYN='" + book.getBookAvailableYN().charAt(0) + "',"
					+ " EBook_Edition='" + book.getBookEdition() + "'," 
					+ " EBook_FileSize='" + ((Ebook)book).getFileSize() + "',"
					+ " EBook_PrintLength='" + ((Ebook)book).getPrintLength() + "'," 
					+ " EBook_MultipleDeviceUsage='" + ((Ebook)book).getMultipleDeviceUsage() + "'"
					+ " WHERE idEBook_Master = " + book.getBookId() + ";");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
