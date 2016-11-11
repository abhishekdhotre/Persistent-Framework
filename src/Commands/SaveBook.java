package Commands;

import BusinessObjects.Book;
import TemplateMethod.RDBImplCmd;

public abstract class SaveBook extends RDBImplCmd 
{
	
	public SaveBook(Book book)
	{
		super();
		this.book = book;
	}
	
	@Override
	public Book processResult() 
	{
		return null;
	}
}
