package compiler;

/**
 * single processing step of a turing machine
 */
public class TuringAction {
	// character to write on the tape
	public char m_writeChar;
	// move head position +1/0/-1
	public int m_move;
	// new state
	public String m_targetState;
	
	public TuringAction(char writeChar, int move, String targetState) {
		m_writeChar = writeChar;
		m_move = move;
		m_targetState = targetState;
	}

}
