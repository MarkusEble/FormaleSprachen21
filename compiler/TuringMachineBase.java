package compiler;

import java.io.OutputStreamWriter;
import java.util.Vector;

/**
 * deterministic turing machine
 */
public abstract class TuringMachineBase extends MachineBase {
	// (endless) in/out tape
	protected Vector<Character> m_tape;
	// position of read/write head on tape
	protected int m_pos;
	// current state
	protected String m_state;

	public abstract String getStartState();

	@Override
	public boolean isFinished() {
		return m_state.equals("finished") || m_state.equals("error");
	}

	@Override
	public boolean isAccepted() {
		return m_state.equals("finished");
	}
	
	public void init(String input) {
		// create tape filled with $ (and size 100)
		m_tape = new Vector<Character>(100);
		m_tape.setSize(100);
		for (int i = 0; i != m_tape.capacity(); i++) {
			m_tape.setElementAt('$', i);
		}		
		m_pos = 50;
		// write input ending at pos
		for (int i = 0; i != input.length(); i++) {
			m_tape.setElementAt(input.charAt(input.length() - i - 1), m_pos - i);
		}		
		
		m_state = getStartState();
	}
	
	public void left() {
		m_pos--;
	}

	public void right() {
		m_pos++;
	}
	
	public char readChar() {
		return m_tape.elementAt(m_pos);
	}

	public void writeChar(char c) {
		m_tape.setElementAt(c, m_pos);
	}
	
	/**
	 * output tape content at given position
	 */
	public void tracePos(int pos, OutputStreamWriter outStream) throws Exception {
		// output char at pos
		outStream.write(m_tape.elementAt(pos));
		if (pos == m_pos) {
			// read/write head is at this pos
			outStream.write(']');
		} else if (pos == m_pos - 1) {
			// read/write head is at the next pos
			outStream.write('[');
		} else {
			outStream.write(' ');
		}
	}

	public void trace(OutputStreamWriter outStream) throws Exception {
		// dump state with padding
		outStream.write(m_state);
		for (int i = m_state.length(); i < 10; i++) {
			outStream.write (' ');
		}
		// dump tape
		int i = 0;
		// skip prefix $
		for (; i != m_tape.size(); i++) {
			if (m_tape.elementAt(i) != '$') {
				break;
			}
		}
		outStream.write(" | ");
		tracePos(i-1, outStream);
		for (; i != m_tape.size(); i++) {
			tracePos(i, outStream);
			// skip postfix $
			if (m_tape.elementAt(i) == '$') {
				break;
			}
		}		
	}
	
	public void traceHead(OutputStreamWriter outStream) throws Exception {
		// dump padding for state
		outStream.write("STATE");
		for (int i = 5; i < 10; i++) {
			outStream.write (' ');
		}		
		outStream.write(" | ");
		outStream.write("TAPE");
	}
	
}
