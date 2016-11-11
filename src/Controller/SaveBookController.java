package Controller;

import java.sql.SQLException;

import BusinessObjects.Book;
import BusinessObjects.Ebook;
import BusinessObjects.HardcoverBook;
import Database.DBMgr;

public class SaveBookController {
	private DBMgr oDBMgr = new DBMgr(); 
	private Book book;
	
	public Book createEBook(String BookName, String BookAuthor, String BookPublisher,
			String BookISBN, String BookAvailableYN, String BookEdition, String FileSize,
			String PrintLength, String MultipleDeviceUsage) {
		book = new Ebook(0,BookName, BookAuthor, BookPublisher, BookISBN, 
				BookAvailableYN, BookEdition, FileSize, PrintLength, MultipleDeviceUsage);
		return book;
	}

	public Book createHardcoverBook(String BookName, String BookAuthor, String BookPublisher,
			String BookISBN, String BookAvailableYN, String BookEdition, String BookNoOfPages,
			String BookWeight, String BookDimensions) {
		book = new HardcoverBook(0, BookName, BookAuthor, BookPublisher, BookISBN, 
				BookAvailableYN, BookEdition, BookNoOfPages, BookWeight, BookDimensions);
		return book;
	}
	
	public void insertHardcoverBook(String BookName, String BookAuthor, String BookPublisher,
			String BookISBN, String BookAvailableYN, String BookEdition, 
			String BookNoOfPages, String BookWeight, String BookDimensions) throws SQLException {
		
		book = createHardcoverBook(BookName, BookAuthor, BookPublisher, BookISBN,
				BookAvailableYN, BookEdition, BookNoOfPages, BookWeight, BookDimensions);
		
		SaveHardCoverBook(book);	
		
	}
	
	public void insertEBook(String BookName, String BookAuthor, String BookPublisher,
			String BookISBN, String BookAvailableYN, String BookEdition, String FileSize,
			String PrintLength, String MultipleDeviceUsage) throws SQLException {
		
		book = createEBook(BookName, BookAuthor, BookPublisher, BookISBN,
				BookAvailableYN, BookEdition, FileSize, PrintLength, MultipleDeviceUsage);
		
		saveEBook(book);	
	}
	
	public void saveEBook(Book book) throws SQLException {
		oDBMgr.SaveEBook(book);
	}
	
	public void SaveHardCoverBook(Book book) throws SQLException {
		oDBMgr.SaveHardCoverBook(book);
	}

}
