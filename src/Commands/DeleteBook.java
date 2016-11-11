package Commands;

import TemplateMethod.RDBImplCmd;

public abstract class DeleteBook extends RDBImplCmd {
	
	public DeleteBook(int BookId){
		this.BookId = BookId;
	}
}
