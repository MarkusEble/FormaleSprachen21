package compiler;

import java.util.HashMap;

/**
 * deterministic turing machine with state transition map 
 *
 */
public abstract class TuringMachine extends TuringMachineBase {
	// state transition map
	protected HashMap<String, TuringState> m_stateMap;

	/**
	 * initialize state transition map
	 */
	public abstract void initStateMap();
	
	public TuringMachine() {
		m_stateMap = new HashMap<String, TuringState>();
		initStateMap();
	}

	@Override
	public void step() throws Exception {
		// look for transition on (current state, current input)
		char curChar = readChar();
		TuringState curState = m_stateMap.get(m_state);
		TuringAction nextAction = curState.getTransition(curChar); 
        if (nextAction == null) {
        	// no transition => error
        	m_state = "error";
        } else {
        	// execute transition
        	writeChar(nextAction.m_writeChar);
        	m_pos += nextAction.m_move;
        	m_state = nextAction.m_targetState;
        }
	}

}
