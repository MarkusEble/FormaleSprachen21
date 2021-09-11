
public class StackMachineNDPalindrome extends compiler.StackMachineND implements Cloneable {
	
	public StackMachineNDPalindrome(compiler.MultiMachine multiMachine) {
		super(multiMachine);
	}

	@Override
	public String getStartState() {
		// expect left part
		return "z_l";
	}

	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	@Override
	public void step() throws Exception {
		// if no input
		if (m_input.currentChar() == 0) {
			// accept if stack empty, else reject
			if (m_stack.size() == 0) {
				m_state = "z+";
			} else {
				m_state = "z-";
			}
		// in state z_l continue reading or switch to state z_r
		} else if (m_state.equals("z_l")) {
			// if current char equals top of stack, create a clone in state z_r
			if (m_stack.size() > 0 && m_input.currentChar() == getStackTop()) {
				StackMachineNDPalindrome theClone = (StackMachineNDPalindrome)this.clone();
				m_multiMachine.addMachine(theClone);
				theClone.m_state = "z_r";
			}
			// push input
			m_stack.push(m_input.currentChar());
			m_input.advance();
		// in state z_r match input and top of stack
    	} else if (m_state.equals("z_r")) {    		
    		if (m_stack.size() > 0 && m_input.currentChar() == getStackTop()) {
    			// consume in case of match
    			m_input.advance();
    			m_stack.pop();
    		} else {
    			// else reject
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
