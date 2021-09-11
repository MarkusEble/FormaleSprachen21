

public class StackMachineParanthese extends compiler.StackMachine {
	
	@Override
	public String getStartState() {
		// expect opening parantheses
		return "z(";
	}

	@Override
	public void step() {
		// while reading opening parantheses
		if (m_state.equals("z(")) {
			// push opening paranthese
			if (m_input.currentChar() == '(') {
				m_stack.push(m_input.currentChar());
				m_input.advance();
			// or end reading opening parantheses
			} else {
				m_state = "z)";
			}
		// read closing paranetheses
		} else if (m_state.equals("z)")) {
			// finish successfully if input and stack are empty
			if (m_input.currentChar() == 0 && m_stack.size() == 0) {
				m_state = "z+";
            // consume closing paranthese				
			} else if (m_input.currentChar() == ')' && m_stack.size() > 0 && getStackTop() == '(') {
				m_stack.pop();
				m_input.advance();
			// otherwise we have an error
			} else {
				m_state = "z-";
			}
		// no other states allowed
		} else {
			assert false;
		}

	}

	@Override
	public boolean isFinalState() {
		return (m_state.equals("z+"));
	}

}
