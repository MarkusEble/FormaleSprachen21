package compiler;

import java.io.OutputStreamWriter;

/**
 * base class for finish state machine
 */
public abstract class StateMachineBase extends MachineBase implements Cloneable {
	protected InputReader m_input;
	protected String m_state;
	protected boolean m_traceFinished = false;

	/**
	 * get start state
	 */
	public abstract String getStartState();

	/**
	 * is the machine in a final state
	 */
	public abstract boolean isFinalState();
	
	public boolean isFinished() {
		return m_input.currentChar() == 0 || m_state.equals("error");
	}

	public StateMachineBase() {
		m_input = new InputReader("");
		m_state = getStartState();
	}
	
	public void init(String input) {
		m_input = new InputReader(input);
		m_state = getStartState();
	}

	public boolean isAccepted() {
		return isFinished() && isFinalState();
	}
	
	/**
	 * dump the current machine state
	 */
	public void trace(OutputStreamWriter outStream) throws Exception {
		// trace finished state only once
		if (m_traceFinished) {
			traceBlank(outStream);
		} else {
			traceState(outStream);
		}
		if (isFinished()) {
			m_traceFinished = true;
		}
	}
	
	/**
	 * trace the actual state
	 */
	public void traceState(OutputStreamWriter outStream) throws Exception {
		// dump input
		m_input.traceState(outStream);
		outStream.write(" | ");
		// dump state with padding
		outStream.write(m_state);
		for (int i = m_state.length(); i < 10; i++) {
			outStream.write (' ');
		}
		
	}

	/**
	 * trace blanks instead of actual state (since machine is finished)
	 */
	public void traceBlank(OutputStreamWriter outStream) throws Exception {
		// dump input
		m_input.traceBlank(outStream);
		outStream.write("   ");
		// dump padding for state
		for (int i = 0; i < 10; i++) {
			outStream.write (' ');
		}		
	}
	
	public void traceHead(OutputStreamWriter outStream) throws Exception {
		// dump input
		m_input.traceHead(outStream);
		outStream.write(" | ");
		// dump padding for state
		outStream.write("STATE");
		for (int i = 5; i < 10; i++) {
			outStream.write (' ');
		}		
	}

	/**
	 * clone in case of non-deterministic decision
	 */
	public Object clone() throws CloneNotSupportedException {
		StateMachineBase theClone = (StateMachineBase)super.clone();
		theClone.m_input = (InputReader)m_input.clone();
		theClone.m_state = new String(m_state);
		theClone.m_traceFinished = false;
		return theClone;
	}
	
	public String getName() {
	    return "<unnamed>";
	}

}
