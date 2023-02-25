import java.util.ArrayList;

public class MyStack<T> implements StackInterface<T>
{
	private ArrayList<T> stack;
	private final int MAXSIZE = 50;
	MyStack(int size)
	{
		stack = new ArrayList<T> (size);
	}
	@Override
	public boolean isEmpty() {
		if(stack.size() == 0)
		{
			return true;
		}
		return false;
	}

	@Override
	public boolean isFull() {
		if(stack.size() == MAXSIZE)
		{
			return true;
		}
		return false;
	}

	@Override
	public T pop() throws StackUnderflowException {
		T topEntry;
		if(isEmpty() == true)
		{
			throw new StackUnderflowException();
		}
		topEntry = stack.get(0);
		stack.remove(0);
		return topEntry;
	}

	@Override
	public T top() throws StackUnderflowException {
		T topEntry;
		if(isEmpty() == true)
		{
			throw new StackUnderflowException();
		}
		topEntry = stack.get(0);
		return topEntry;
	}

	@Override
	public int size() {
		return(stack.size());
	}

	@Override
	public boolean push(T e) throws StackOverflowException {
		if(isFull() == true)
		{
			throw new StackOverflowException();
		}
		stack.add(0, e);
		return true;
	}

	public String toString()
	{
		String stackString ="";
		for(int i = 0; i < stack.size(); i++)
		{
			stackString += stack.get(stack.size() - (i + 1));
		}
		return stackString; 
	}
	@Override
	public String toString(String delimiter) {
		String stackString = "";
		for(int i = 0; i < stack.size(); i++)
		{
			stackString += stack.get(stack.size() - (i + 1));
			if(i != stack.size() - 1)
			{
				stackString += delimiter;
			}
		}
		return stackString;
	}

	@Override
	public void fill(ArrayList list) 
	{
		ArrayList<T> copy;
		copy = new ArrayList<T>(list);
		for (T e : copy) 
		{
			try 
			{
				this.push(e);
			} 
			catch (StackOverflowException b) 
			{
				b.printStackTrace();
			}
		}
	}
	
	
}