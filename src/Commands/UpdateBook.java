package Commands;

import BusinessObjects.Book;
import TemplateMethod.RDBImplCmd;

public abstract class UpdateBook extends RDBImplCmd {
	
	public UpdateBook(Book book)
	{
		this.book = book;
	}

}
