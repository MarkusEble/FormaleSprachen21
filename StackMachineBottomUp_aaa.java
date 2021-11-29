
public class StackMachineBottomUp_aaa extends compiler.StackMachine {
	
	public StackMachineBottomUp_aaa() {
	}

	@Override
	public String getStartState() {
		return "z";
	}

	@Override
	public void step() throws Exception {
        // processing state
		if (m_state.equals("z")) {
		    // rule S => a+S
		    if (matchStackTop("a+S")) {
		        m_stack.pop();
		        m_stack.pop();
                m_stack.pop();
		        m_stack.push('S');
		    // rule S => a
		    } else if (matchStackTop("a")) {
	            m_stack.pop();
	            m_stack.push('S');
            // rule S => (S)
		    } else if (m_input.currentChar() != 0) {
		        m_stack.push(m_input.currentChar());
		        m_input.advance();
		    // no fitting rule, no input left, accept if stack contains exactly S
		    } else if (m_stack.size() == 1 && getStackTop() == 'S') {
		        m_stack.pop();
		        m_state = "z+";
		    // fail
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
