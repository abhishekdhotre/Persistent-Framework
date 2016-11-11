package Commands;

import java.util.List;

import BusinessObjects.Book;
import TemplateMethod.RDBImplCmd;

public abstract class FetchBooks extends RDBImplCmd {
	
	public FetchBooks(String bookName){
		super();
		this.bookName=bookName;
	}
	
	@Override
	public Book processResult() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<Book> getBooks(){
		return bookList;
	}
	
	
	
}
