import java.util.Vector;

import compiler.MultiMachine;

/**
 * sample for non deterministic state machine
 * accept a*ab
 */
public class StateMachineNDABReader extends compiler.StateMachineND {
	
	public StateMachineNDABReader(MultiMachine multiMachine) {
		super(multiMachine);
	}

	@Override
	public String getStartState() {
		return "iterateA";
	}

	@Override
	public boolean isFinalState() {
		return (m_state.equals("afterB"));
	}

	@Override
	public void initStateTable() {
		compiler.StateND iterateA = new compiler.StateND("iterateA");
		Vector<String> aaTransition = new Vector<String>();
		aaTransition.add("iterateA");
		aaTransition.add("expectB");
		iterateA.addTransitionND('a', aaTransition );
		m_stateMap.put("iterateA", iterateA);

		compiler.StateND expectB = new compiler.StateND("expectB");
		expectB.addTransition('b', "afterB");
		m_stateMap.put("expectB", expectB);

		compiler.StateND afterB = new compiler.StateND("afterB");
		m_stateMap.put("afterB", afterB);
   }

}
