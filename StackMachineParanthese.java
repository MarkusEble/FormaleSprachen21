

public class StackMachineParanthese extends compiler.StackMachine {
	
	@Override
	public String getStartState() {
		// expect opening parantheses
		return "z(";
	}

	@Override
	public void step() {
	}

	@Override
	public boolean isFinalState() {
		return (m_state.equals("z+"));
	}

}
