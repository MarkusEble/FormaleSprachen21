
public class StackMachineBottomUpParanthese extends compiler.StackMachine {
	
	public StackMachineBottomUpParanthese() {
	}

	@Override
	public String getStartState() {
		return "z";
	}

	@Override
	public void step() throws Exception {
        // processing state
		if (m_state.equals("z")) {
		    // rule S => ()
		    if (matchStackTop("()")) {
		        m_stack.pop();
		        m_stack.pop();
		        m_stack.push('S');
		    // rule S => (S)
		    } else if (matchStackTop("(S)")) {
	            m_stack.pop();
	            m_stack.pop();
                m_stack.pop();
	            m_stack.push('S');
            // rule S => (S)
	        } else if (matchStackTop("SS")) {
	            m_stack.pop();
	            m_stack.pop();
	            m_stack.push('S');
	        // no fitting rule => push next character
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
