package BridgeInterface;

import java.sql.SQLException;

import java.util.List;
import BusinessObjects.Book;
import FactoryMethod.*;
import UndoRedo.RedoStack;

public class RDBImpl implements DBImplInterface {

	@Override
	public Book GetEBook(int BookId) throws SQLException {
		GetEBook gb = new GetEBook(BookId);
		gb.execute();
		return (Book) gb.getBook();
	}

	@Override
	public void SaveEBook(Book book) throws SQLException {
		SaveEBook sb = new SaveEBook(book);
		sb.execute();
		RedoStack.push(sb);
	}

	@Override
	public void DeleteEBook(int BookId) throws SQLException {
		DeleteEBook db = new DeleteEBook(BookId);
		db.execute();
		RedoStack.push(db);
	}

	@Override
	public void UpdateEBook(Book book) throws SQLException {
		UpdateEBook ub = new UpdateEBook(book);
		ub.execute();
		RedoStack.push(ub);
	}
	
	@Override
	public List<Book> FetchEBooks(String bookName) throws SQLException {
		FetchEBook fb = new FetchEBook(bookName);
		fb.execute();
		return (List<Book>) fb.getBooks();
	}

	@Override
	public Book GetHardCoverBook(int BookId) throws SQLException {
		GetHardCoverBook gb = new GetHardCoverBook(BookId);
		gb.execute();
		return (Book) gb.processResult();
	}

	@Override
	public void SaveHardCoverBook(Book book) throws SQLException {
		SaveHardCoverBook sb = new SaveHardCoverBook(book);
		sb.execute();
		RedoStack.push(sb);
	}

	@Override
	public void DeleteHardCoverBook(int BookId) throws SQLException {
		DeleteHardCoverBook db = new DeleteHardCoverBook(BookId);
		db.execute();
		RedoStack.push(db);
	}

	@Override
	public void UpdateHardCoverBook(Book book) throws SQLException {
		UpdateHardCoverBook ub = new UpdateHardCoverBook(book);
		ub.execute();
		RedoStack.push(ub);
	}
	
	@Override
	public List<Book> FetchHardCoverBooks(String bookName) throws SQLException {
		FetchHardCoverBook fb = new FetchHardCoverBook(bookName);
		fb.execute();
		return (List<Book>) fb.getBooks();
	}
	
	

}
