package compiler;

import java.io.OutputStreamWriter;

/**
 * interface for stream input
 */
public interface InputReaderIntf {
	/**
	 * constructs InputReader reading from string
	 */
	// public InputReader(String input);

	/**
	 * look at the current character without
	 * consuming it. 0 means end of input.
	 */
	public char currentChar();
	
	/**
	 * consume current char and
	 * advance to next character
	 */
	public void advance();
	
	/**
	 * print current state of input to stream
	 */
	public void traceState(OutputStreamWriter outStream) throws Exception;

	/**
	 * print blanks for current state of input stream
	 */
	public void traceBlank(OutputStreamWriter outStream) throws Exception;

	/**
	 * print headline to stream
	 */
	public void traceHead(OutputStreamWriter outStream) throws Exception;

}
