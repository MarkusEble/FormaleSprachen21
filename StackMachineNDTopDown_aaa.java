
public class StackMachineNDTopDown_aaa extends compiler.StackMachineND implements Cloneable {
	
	public StackMachineNDTopDown_aaa(compiler.MultiMachine multiMachine) {
		super(multiMachine);
	}

	@Override
	public String getStartState() {
		// push start symbol
		return "z_0";
	}

	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

    @Override
    public void step() throws Exception {
        // start state: push start symbol and go to processing state
        if (m_state.equals("z_0")) {
            m_state = "z";
            m_stack.push('S');
            return;
        }
        
        // processing state
        assert m_state.equals("z");
        
        char currentChar = m_input.currentChar();
        char topChar = pop();

        if (topChar == 0) {
            if (currentChar == 0) {
                m_state = "z+";
            } else {
                m_state = "z-";
            }
            // non terminal on top of stack; consume from input or fail
        } else if (topChar == '+' || topChar == 'a') {
            if (currentChar == topChar) {
                m_input.advance();
            } else {
                m_state = "z-";
            }
        // terminal on top of input; create one clone for each possible rule
        } else if (topChar == 'S') {
            StackMachineNDTopDown_aaa theClone_S_a = this;
            StackMachineNDTopDown_aaa theClone_S_aPlusS = (StackMachineNDTopDown_aaa)this.clone();
            theClone_S_a.m_stack.push('a');
            theClone_S_aPlusS.m_stack.push('S');
            theClone_S_aPlusS.m_stack.push('+');
            theClone_S_aPlusS.m_stack.push('a');
            m_multiMachine.addMachine(theClone_S_aPlusS);
        } else {
           assert false;
        }
    }	

	@Override
	public boolean isFinalState() {
		return (m_state.equals("z+"));
	}

}
