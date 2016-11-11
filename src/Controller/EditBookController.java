package Controller;

import java.sql.SQLException;

import BusinessObjects.Ebook;
import BusinessObjects.HardcoverBook;
import Database.DBMgr;

public class EditBookController {
	
	private DBMgr oDBMgr = new DBMgr(); 
	private RetrieveBookController rbc = new RetrieveBookController();
	
	public void UpdateEBook(int BookId, String BookName, String BookAuthor,String BookPublisher,
			String BookISBN, String BookAvailableYN, String BookEdition, String FileSize,
			String MultipleDeviceUsage, String PrintLength) throws SQLException {
		Ebook ebook = (Ebook) rbc.GetEBook(BookId);
		ebook.setBookName(BookName);
		ebook.setBookAuthor(BookAuthor);
		ebook.setBookPublisher(BookPublisher);
		ebook.setBookISBN(BookISBN);
		ebook.setBookEdition(BookEdition);
		ebook.setBookAvailableYN(BookAvailableYN);
		ebook.setFileSize(FileSize);
		ebook.setMultipleDeviceUsage(MultipleDeviceUsage);
		ebook.setPrintLength(PrintLength);
		oDBMgr.UpdateEBook(ebook);
	}
	
	public void UpdateHardCoverBook(int BookId, String BookName, String BookAuthor,String BookPublisher,
			String BookISBN, String BookAvailableYN, String BookEdition, 
			String BookNoOfPages, String BookWeight, String BookDimensions) throws SQLException {
		HardcoverBook hbook = (HardcoverBook) rbc.GetHardcoverBook(BookId);
		hbook.setBookName(BookName);
		hbook.setBookAuthor(BookAuthor);
		hbook.setBookPublisher(BookPublisher);
		hbook.setBookISBN(BookISBN);
		hbook.setBookEdition(BookEdition);
		hbook.setBookAvailableYN(BookAvailableYN);
		hbook.setBookWeight(BookWeight);
		hbook.setBookNoOfPages(BookNoOfPages);
		hbook.setBookDimensions(BookDimensions);
		oDBMgr.UpdateHardCoverBook(hbook);
	}
	
	public void DeleteEBook(int BookId) throws SQLException {
		oDBMgr.DeleteEBook(BookId);
	}

	public void DeleteHardcoverBook(int BookId) throws SQLException {
		oDBMgr.DeleteHardCoverBook(BookId);
	}
}
