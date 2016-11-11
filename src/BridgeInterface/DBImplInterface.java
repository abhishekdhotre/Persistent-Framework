package BridgeInterface;

import java.sql.SQLException;
import java.util.List;

import BusinessObjects.Book;

public interface DBImplInterface 
{
	public List<Book> FetchEBooks(String bookName) throws SQLException;
	public Book GetEBook(int BookId) throws SQLException;
	public void SaveEBook(Book book) throws SQLException;
	public void DeleteEBook(int BookId) throws SQLException;
	public void UpdateEBook(Book book) throws SQLException;
	
	public List<Book> FetchHardCoverBooks(String bookName) throws SQLException;
	public Book GetHardCoverBook(int BookId) throws SQLException;
	public void SaveHardCoverBook(Book book) throws SQLException;
	public void DeleteHardCoverBook(int BookId) throws SQLException;
	public void UpdateHardCoverBook(Book book) throws SQLException;
}
