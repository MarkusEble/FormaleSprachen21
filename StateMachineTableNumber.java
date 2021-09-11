/**
 * sample state machine
 * accept natural numbers 0|[1-9][0-9]*
 */

public class StateMachineTableNumber extends compiler.StateMachine {
	
	@Override
	public String getStartState() {
		return "firstDigit";
	}

	@Override
	public boolean isFinalState() {
		return (m_state.equals("number0") || m_state.equals("nextDigit"));
	}

	@Override
	public void initStateTable() {
		compiler.State firstDigit = new compiler.State("firstDigit");
		firstDigit.addTransition('0', "number0");
		firstDigit.addTransition('1', "nextDigit");
		firstDigit.addTransition('2', "nextDigit");
		firstDigit.addTransition('3', "nextDigit");
		firstDigit.addTransition('4', "nextDigit");
		firstDigit.addTransition('5', "nextDigit");
		firstDigit.addTransition('6', "nextDigit");
		firstDigit.addTransition('7', "nextDigit");
		firstDigit.addTransition('8', "nextDigit");
		firstDigit.addTransition('9', "nextDigit");
		m_stateMap.put("firstDigit", firstDigit);

		compiler.State number0 = new compiler.State("number0");
		m_stateMap.put("number0", number0);

		compiler.State nextDigit = new compiler.State("nextDigit");		
		nextDigit.addTransition('0', "nextDigit");
		nextDigit.addTransition('1', "nextDigit");
		nextDigit.addTransition('2', "nextDigit");
		nextDigit.addTransition('3', "nextDigit");
		nextDigit.addTransition('4', "nextDigit");
		nextDigit.addTransition('5', "nextDigit");
		nextDigit.addTransition('6', "nextDigit");
		nextDigit.addTransition('7', "nextDigit");
		nextDigit.addTransition('8', "nextDigit");
		nextDigit.addTransition('9', "nextDigit");
		m_stateMap.put("nextDigit", nextDigit);
	}

}
