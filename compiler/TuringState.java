package compiler;

import java.util.HashMap;

/**
 * single state of a deterministic turing machine
 */
public class TuringState {
	private String m_name;
	private HashMap<String, TuringAction> m_transitionMap;
	
	public TuringState(String name) {
		m_name = name;
		m_transitionMap = new HashMap<String, TuringAction>();
	}
	
	public void addTransition(char readChar, TuringAction action) {
		String s = new String();
		s += readChar;
		m_transitionMap.put(s, action);
	}

	public TuringAction getTransition(char readChar) {
		String s = new String();
		s += readChar;
		TuringAction action = m_transitionMap.get(s);
		return action;
	}

	public String getName() {
		return m_name;
	}
}	

