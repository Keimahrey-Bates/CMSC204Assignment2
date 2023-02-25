import java.util.ArrayList;

public class MyQueue<T> implements QueueInterface<T>
{
	private ArrayList<T> queue;
	private final int MAXSIZE = 50;
	MyQueue(int size)
	{
		queue = new ArrayList<T> (size);
	}
	@Override
	public boolean isEmpty() {
		if(queue.size() == 0)
		{
			return true;
		}
		return false;
	}

	@Override
	public boolean isFull() {
		if(queue.size() == MAXSIZE)
		{
			return true;
		}
		return false;
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		T frontQueue;
		if(isEmpty() == true)
		{
			throw new QueueUnderflowException();
		}
		frontQueue = queue.get(queue.size() - 1);
		queue.remove(queue.size() - 1);
		return frontQueue;
		}
	

	@Override
	public int size() {
		return(queue.size());
	}

	@Override
	public boolean enqueue(T e) throws QueueOverflowException {
		if(isFull() == true)
		{
			throw new QueueOverflowException();
		}
		queue.add(0, e);
		return true;
	}
	@Override
	public String toString()
	{
		String queueString ="";
		for(int i = 0; i < queue.size(); i++)
		{
			queueString += queue.get(queue.size() - (i + 1));
		}
		return queueString;
	}
	@Override
	public String toString(String delimiter) {
		String queueString = "";
		for(int i = 0; i < queue.size(); i++)
		{
			queueString += queue.get(queue.size() - (i + 1));
			if(i != queue.size() - 1)
			{
				queueString += delimiter;
			}
		}
		return queueString;
	}

	@Override
	public void fill(ArrayList list) {
		ArrayList<T> copy;
		copy = new ArrayList<T>(list);
		for (T e : copy) 
		{
			try 
			{
				this.enqueue(e);
			} 
			catch (QueueOverflowException b) 
			{
				b.printStackTrace();
			}
		}
		
	}
	
}