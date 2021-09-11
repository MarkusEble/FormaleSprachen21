package compiler;

import java.util.HashMap;
import java.util.Vector;

/**
 * single state of a non-deterministic finite state machine with transitions
 */
public class StateND {
	private String m_name;
	private HashMap<String, Vector<String>> m_transitionMap;

	public StateND(String name) {
		m_name = name;
		m_transitionMap = new HashMap<String, Vector<String>>();
	}
	
	public void addTransition(char terminal, String targetState) {
		String s = new String();
		s += terminal;
		Vector<String> stateList = new Vector<String>();
		stateList.add(targetState);
		m_transitionMap.put(s, stateList);
	}

	public void addTransitionND(char terminal, Vector<String> targetStateList) {
		String s = new String();
		s += terminal;	
		m_transitionMap.put(s, targetStateList);
	}

	public Vector<String> getTransitionND(char terminal) {
		String s = new String();
		s += terminal;
		Vector<String> stateList = m_transitionMap.get(s);
		return stateList;
	}
	
	public String getName() {
		return m_name;
	}
}