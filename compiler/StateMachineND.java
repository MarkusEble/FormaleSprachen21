package compiler;

import java.util.HashMap;
import java.util.Vector;

/**
 * non-deterministic finish state machine with transition table
 */
public abstract class StateMachineND extends StateMachineBase {
	// state transition table
	protected HashMap<String, StateND> m_stateMap;
	// container managing all clones
	protected MultiMachine m_multiMachine;
	
	public StateMachineND(MultiMachine multiMachine) {
		super();
		m_stateMap = new HashMap<String, StateND>();
		initStateTable();
		m_multiMachine = multiMachine;
	}
	
	/**
	 * initialize state transition table
	 */
	public abstract void initStateTable();

	public boolean isFinished() {
		if (m_state.equals("error")) {
			// finished in case of error
			return true;
		} else {		
			// finished if we have no input and no epsilon transition
		    StateND curState = m_stateMap.get(m_state);
		    boolean hasEpsilonTransition = (curState.getTransitionND('\0') != null);
		    return m_input.currentChar() == 0 && !hasEpsilonTransition;
		}
	}
	
	@Override
	public void step() throws Exception {
		char curChar = m_input.currentChar();
		StateND curState = m_stateMap.get(m_state);

		// look for epsilon transitions on current state
		Vector<String> epsilonStateStringList = curState.getTransitionND('\0'); 
        if (epsilonStateStringList != null) {
        	// create a clone for each epsilon transition
        	for (int i = 0; i != epsilonStateStringList.size(); i++) {
        		String nextState = epsilonStateStringList.elementAt(i);
        		cloneWithTransition(nextState);
        	}
        }
		
        // look for transitions on (current state, current input)
		Vector<String> nextStateStringList = curState.getTransitionND(curChar); 
        if (nextStateStringList != null && curChar != '\0') {
        	// consume input (for this instance and all clones we create now)
            m_input.advance();
        	// create a clone for each transition besides the first one
        	for (int i = 1; i != nextStateStringList.size(); i++) {
        		String nextState = nextStateStringList.elementAt(i);
        		cloneWithTransition(nextState);
        	}
        	// do the first transition on this instance
        	m_state = nextStateStringList.elementAt(0);
        } else {
        	m_state = "error";
        }
        
        // if we have neither a transition on epsilon nor on current input => error
        if (epsilonStateStringList == null && nextStateStringList == null) {
        	//m_state = "error";
        }
	}

	// clone and transit clone to next state
	public void cloneWithTransition(String nextState) throws Exception {
		StateMachineND theClone = (StateMachineND)this.clone();
		theClone.m_state = nextState;
		m_multiMachine.addMachine(theClone);		
	}

	public Object clone() throws CloneNotSupportedException {
		StateMachineND theClone = (StateMachineND)super.clone();
		theClone.m_input = (InputReader)m_input.clone();
		theClone.m_state = new String(m_state);
		return theClone;
	}	
}
