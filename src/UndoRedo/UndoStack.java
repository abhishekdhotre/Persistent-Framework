package UndoRedo;
import java.util.Stack;

import TemplateMethod.RDBImplCmd;

public class UndoStack {
	
private static Stack<RDBImplCmd> uStack;
	
	public static void initialize()
	{
		uStack = new Stack<RDBImplCmd>();		
	}
	
	public static void push(RDBImplCmd cmd)
	{
		uStack.push(cmd);
	}
	
	public static RDBImplCmd pop()
	{
		if(!uStack.isEmpty())
			return uStack.pop();
		return null;
	}
	
}


