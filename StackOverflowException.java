public class StackOverflowException extends RuntimeException
{
	private static final long serialVersionUID = -1833607713372576764L;

	public StackOverflowException()
	{
		super("The stack is already full.");
	}
}