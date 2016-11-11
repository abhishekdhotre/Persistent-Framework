/**
 * 
 */
package Database;
import java.sql.*;
import java.util.*;
import BridgeInterface.DBImplInterface;
import BridgeInterface.RDBImpl;
import BusinessObjects.*;


public class DBMgr 
{
	DBImplInterface imp = new RDBImpl();
	
	public List<Book> FetchEBooks(String bookName) throws SQLException
	{
		return imp.FetchEBooks(bookName);
	}
	
	public Book GetEBook(int BookId) throws SQLException
	{
		return imp.GetEBook(BookId);
	}
	
	public void SaveEBook(Book book) throws SQLException
	{
		imp.SaveEBook(book);
	}
	
	public void DeleteEBook(int BookId) throws SQLException
	{
		imp.DeleteEBook(BookId);
	}
	
	public void UpdateEBook(Book book) throws SQLException
	{
		imp.UpdateEBook(book);
    }
	
	public List<Book> FetchHardCoverBooks(String bookName) throws SQLException
	{
		return imp.FetchHardCoverBooks(bookName);
	}
	
	public Book GetHardCoverBook(int BookId) throws SQLException
	{
		return imp.GetHardCoverBook(BookId);
	}
	
	public void SaveHardCoverBook(Book book) throws SQLException
	{
		imp.SaveHardCoverBook(book);
	}
	
	public void DeleteHardCoverBook(int BookId) throws SQLException
	{
		imp.DeleteHardCoverBook(BookId);
	}
	
	public void UpdateHardCoverBook(Book book) throws SQLException
	{
		imp.UpdateHardCoverBook(book);
    }
}
	
	

