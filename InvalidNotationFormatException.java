public class InvalidNotationFormatException extends RuntimeException
{

	private static final long serialVersionUID = 7409145447544459252L;

	public InvalidNotationFormatException()
	{
		super("The notation format is invalid.");
	}
}