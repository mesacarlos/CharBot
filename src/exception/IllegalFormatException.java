package exception;

public class IllegalFormatException extends Exception {
	private static final long serialVersionUID = -7285810810532205707L;

	public IllegalFormatException(String msg) {
		super(msg);
	}
}