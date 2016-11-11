package Commands;

import BusinessObjects.Book;
import TemplateMethod.RDBImplCmd;

public abstract class GetBook extends RDBImplCmd {

	
	public GetBook(int BookId){
		super();
		this.BookId=BookId;
	}
	
	public Book getBook(){
		return book;
	}
	
	@Override
	public void undoQueryDB() {
		// TODO Auto-generated method stub
		
	}
	
}
