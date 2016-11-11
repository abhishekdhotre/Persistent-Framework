package Controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.StringUtils;

import BusinessObjects.Book;
import BusinessObjects.Ebook;
import BusinessObjects.HardcoverBook;
import Database.DBMgr;

public class RetrieveBookController {
	private DBMgr oDBMgr = new DBMgr(); 
	
	public Book GetEBook(int BookId) throws SQLException {
		return oDBMgr.GetEBook(BookId);	
	}

	public Book GetHardcoverBook(int BookId) throws SQLException {
		return oDBMgr.GetHardCoverBook(BookId);
	}
	
	public DefaultTableModel SearchHardcoverBook(String BookId) throws SQLException {
		List<Book> lstBook = new ArrayList<>();
		if(StringUtils.isStrictlyNumeric(BookId)){
			if(oDBMgr.GetHardCoverBook(Integer.parseInt(BookId))!=null){
			lstBook.add(oDBMgr.GetHardCoverBook(Integer.parseInt(BookId)));
			return CreateResultSetFromHardCoverBookList(lstBook);
		} 
			else
				return null;
		}
		else{
			return null;
		}
	}
	
	public DefaultTableModel SearchEBook(String BookId) throws SQLException {
		List<Book> lstBook = new ArrayList<>();
		if(StringUtils.isStrictlyNumeric(BookId)){
			if(oDBMgr.GetEBook(Integer.parseInt(BookId))!=null){
			lstBook.add(oDBMgr.GetEBook(Integer.parseInt(BookId)));
			return CreateResultSetFromEBookList(lstBook);
			}
			else
				return null;
		} 
		else{
			return null;
		}
	}
	
	public DefaultTableModel FetchEBooks(String bookName) throws SQLException {
		return CreateResultSetFromEBookList(oDBMgr.FetchEBooks(bookName));
	}

	public DefaultTableModel FetchHardCoverBooks(String bookName) throws SQLException {
		return CreateResultSetFromHardCoverBookList(oDBMgr.FetchHardCoverBooks(bookName));
	}

	public DefaultTableModel CreateResultSetFromEBookList(List<Book> lstBook){
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Id");
		model.addColumn("Name");
		model.addColumn("Author");
		model.addColumn("Publisher");
		model.addColumn("ISBN");
		model.addColumn("Avaiable");
		model.addColumn("Edition");
		model.addColumn("File Size");
		model.addColumn("Print Length");
		model.addColumn("Multiple Device Usage");
		for (Book b : lstBook) {
			Ebook e = (Ebook) b;
			  Object[] o = new Object[10];
			  o[0] = e.getBookId();
			  o[1] = e.getBookName();
			  o[2] = e.getBookAuthor();
			  o[3] = e.getBookPublisher();
			  o[4] = e.getBookISBN();
			  if(e.getBookAvailableYN().equals("Y")) {
				  o[5] = "Yes";
			  }
			  else{
				  o[5] = "No";
			  }
			  o[6] = e.getBookEdition();
			  o[7] = e.getFileSize();
			  o[8] = e.getPrintLength();  
			  o[9] = e.getMultipleDeviceUsage();
			  model.addRow(o);	
			}
			return model;
	}

	public DefaultTableModel CreateResultSetFromHardCoverBookList(List<Book> lstBook){
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Id");
		model.addColumn("Name");
		model.addColumn("Author");
		model.addColumn("Publisher");
		model.addColumn("ISBN");
		model.addColumn("Avaiable");
		model.addColumn("Edition");
		model.addColumn("Pages");
		model.addColumn("Weight");
		model.addColumn("Dimensions");
		for (Book b : lstBook) {
		  HardcoverBook h = (HardcoverBook) b;
		  Object[] o = new Object[10];
		  o[0] = h.getBookId();
		  o[1] = h.getBookName();
		  o[2] = h.getBookAuthor();
		  o[3] = h.getBookPublisher();
		  o[4] = h.getBookISBN();
		  if(h.getBookAvailableYN().equals("Y")) {
			  o[5] = "Yes";
		  }
		  else{
			  o[5] = "No";
		  }
		  o[6] = h.getBookEdition();
		  o[7] = h.getBookNoOfPages();
		  o[8] = h.getBookWeight();
		  o[9] = h.getBookDimensions();  
		  model.addRow(o);	
		}
	return model;
}

}
