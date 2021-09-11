package compiler;

import java.util.HashMap;
import java.util.Vector;

/**
 * single state of a deterministic finite state machine with transitions
 */
public class State {
	private String m_name;
	private HashMap<String, String> m_transitionMap;

	public State(String name) {
		m_name = name;
		m_transitionMap = new HashMap<String, String>();
	}
	
	public void addTransition(char terminal, String targetState) {
		String s = new String();
		s += terminal;
		m_transitionMap.put(s, targetState);
	}

	public String getTransition(char terminal) {
		String s = new String();
		s += terminal;
		String state = m_transitionMap.get(s);
		return state;
	}

	public String getName() {
		return m_name;
	}
}