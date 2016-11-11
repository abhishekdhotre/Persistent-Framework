package Controller;

import TemplateMethod.RDBImplCmd;
import UndoRedo.RedoStack;
import UndoRedo.UndoStack;

public class UndoRedoController 
{	
	public void undo(){
		RDBImplCmd cmd = RedoStack.pop();
		if(cmd!=null){
			cmd.undo();
			UndoStack.push(cmd);	
		}
	}
	
	public void redo()
	{
		RDBImplCmd cmd = UndoStack.pop();
		if (cmd!=null){
			cmd.execute();
			RedoStack.push(cmd);

		}
	}
}
	

