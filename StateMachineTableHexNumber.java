
/**
 * sample deterministic state machine
 * accept hexadecimal numbers 0x([0-9]|[A-F])+
 */
public class StateMachineTableHexNumber extends compiler.StateMachine {
	
	@Override
	public String getStartState() {
		return "leading0";
	}

	@Override
	public boolean isFinalState() {
		return (m_state.equals("nextDigit"));
	}

	@Override
	public void initStateTable() {
		compiler.State leading0 = new compiler.State("leading0");
		leading0.addTransition('0', "leadingX");
		m_stateMap.put("leading0", leading0);

		compiler.State leadingX = new compiler.State("leadingX");
		leadingX.addTransition('x', "firstDigit");
		m_stateMap.put("leadingX", leadingX);

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
		firstDigit.addTransition('A', "nextDigit");
		firstDigit.addTransition('B', "nextDigit");
		firstDigit.addTransition('C', "nextDigit");
		firstDigit.addTransition('D', "nextDigit");
		firstDigit.addTransition('E', "nextDigit");
		firstDigit.addTransition('F', "nextDigit");
		m_stateMap.put("firstDigit", firstDigit);

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
		nextDigit.addTransition('A', "nextDigit");
		nextDigit.addTransition('B', "nextDigit");
		nextDigit.addTransition('C', "nextDigit");
		nextDigit.addTransition('D', "nextDigit");
		nextDigit.addTransition('E', "nextDigit");
		nextDigit.addTransition('F', "nextDigit");
		m_stateMap.put("nextDigit", nextDigit);
	}

}
