
public class StackMachineNDTopDownParanthese extends compiler.StackMachineND implements Cloneable {
	
	public StackMachineNDTopDownParanthese(compiler.MultiMachine multiMachine) {
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
		} else if (topChar == '(' || topChar == ')') {
		    if (currentChar == topChar) {
		        m_input.advance();
		    } else {
		        m_state = "z-";
		    }
		// terminal on top of input; create one clone for each possible rule
		} else if (topChar == 'S') {
            StackMachineNDTopDownParanthese theClone_S_Epsilon = this;
            //StackMachineNDTopDownParanthese theClone_S_pSp = (StackMachineNDTopDownParanthese)this.clone();
            //theClone_S_pSp.m_stack.push(')');
            //theClone_S_pSp.m_stack.push('S');
            //theClone_S_pSp.m_stack.push('(');
            //m_multiMachine.addMachine(theClone_S_pSp);
            // this rule causes the machine to overflow
            //StackMachineNDTopDownParanthese theClone_S_SS = (StackMachineNDTopDownParanthese)this.clone();
            //theClone_S_SS.m_stack.push('S');
            //theClone_S_SS.m_stack.push('S');
            //m_multiMachine.addMachine(theClone_S_SS);
            StackMachineNDTopDownParanthese theClone_S_RS = (StackMachineNDTopDownParanthese)this.clone();
            theClone_S_RS.m_stack.push('R');
            theClone_S_RS.m_stack.push('S');
            m_multiMachine.addMachine(theClone_S_RS);
        } else if (topChar == 'R') {
            StackMachineNDTopDownParanthese theClone_R_Epsilon = this;
            StackMachineNDTopDownParanthese theClone_R_pRp = (StackMachineNDTopDownParanthese)this.clone();
            theClone_R_pRp.m_stack.push(')');
            theClone_R_pRp.m_stack.push('R');
            theClone_R_pRp.m_stack.push('(');
            m_multiMachine.addMachine(theClone_R_pRp);
        } else {
	       assert false;
		}
	}

	@Override
	public boolean isFinalState() {
		return (m_state.equals("z+"));
	}

}
