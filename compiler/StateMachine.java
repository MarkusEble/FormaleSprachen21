package compiler;

import java.util.HashMap;

/**
 * deterministic finish state machine with transition table
 */
public abstract class StateMachine extends StateMachineBase {
	// state transition table
	protected HashMap<String, State> m_stateMap;
	
	public StateMachine() {
		super();
		m_stateMap = new HashMap<String, State>();
		initStateTable();
	}
	
	/**
	 * initialize state transition table
	 */
	public abstract void initStateTable();

	@Override
	public void step() {
		// look for transition on (current state, current input)
		char curChar = m_input.currentChar();
		State curState = m_stateMap.get(m_state);
		String nextStateString = curState.getTransition(curChar); 
        if (nextStateString == null) {
        	// no transition => error
        	m_state = "error";
        } else {
        	// execute transition
        	State nextState = m_stateMap.get(nextStateString);
        	m_state = nextState.getName();
        }
        m_input.advance();
	}

}
