package compiler;

import java.io.OutputStreamWriter;

/**
 * provide input string as stream
 */
public class InputReader implements InputReaderIntf, Cloneable {
	private String m_input;
	private int m_pos;
    
	public InputReader(String input) {
		m_input = input;
		m_pos = 0;
	}
	
	public char currentChar() {
		if (m_pos != m_input.length()) {
			return m_input.charAt(m_pos);
		} else {
			return 0;
		}
	}
	
	public void advance() {
		if (m_pos != m_input.length()) {
			m_pos++;
		}
	}
	
	public void traceState(OutputStreamWriter outStream) throws Exception {
		for (int i = 0; i != m_input.length(); i++) {
			if (i < m_pos) {
				// blank already processed characters
			    outStream.write(' ');
			} else {
				// print remaining characters
				outStream.write(m_input.charAt(i));
			}
		}
	}
	
	public void traceBlank(OutputStreamWriter outStream) throws Exception {
		for (int i = 0; i != m_input.length(); i++) {
 	        outStream.write(' ');
		}
	}

	public void traceHead(OutputStreamWriter outStream) throws Exception {
		outStream.write("IN");
		for (int i = 2; i != m_input.length(); i++) {
 	        outStream.write(' ');
		}		
	}

	public Object clone() throws CloneNotSupportedException {
		InputReader theClone = (InputReader)super.clone();
		theClone.m_input = new String(m_input);
		theClone.m_pos = m_pos;
		return theClone;
	}

}

