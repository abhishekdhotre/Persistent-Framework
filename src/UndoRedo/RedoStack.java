package UndoRedo;
import java.util.Stack;

import TemplateMethod.RDBImplCmd;

public class RedoStack {
private static Stack<RDBImplCmd> rStack;
	
	public static void initialize()
	{
		rStack = new Stack<RDBImplCmd>();		
	}
	
	public static void push(RDBImplCmd cmd)
	{
		rStack.push(cmd);
	}
	
	public static RDBImplCmd pop()
	{
		if(!rStack.isEmpty())
			return rStack.pop();
		
		return null;
	}
}
