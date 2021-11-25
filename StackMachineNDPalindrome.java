
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
	    char currentChar = m_input.currentChar();
	    char topChar = pop();
        // if input empty, check if stack is empty
        if (currentChar == 0) {
           if (m_stack.isEmpty()) {
              m_state = "z+";
              return;
           } else {
              m_state = "z-";
              return;
           }
        }

        // when we are reading the right side
        if (m_state == "z_r") {
            // check if the current char matches top of stack
            if (topChar == currentChar) {
                // continue with right side
                m_input.advance();
                m_state = "z_r";
                return;
            } else {
                // mismatch
                m_state = "z-";
                return;
             }

         // when we are reading the left side
         } else if (m_state == "z_l") {
	         // check if input matches top of stack
	         if (topChar == currentChar) {
	            // non-deterministic:
	            // switch to ride side
	            StackMachineNDPalindrome switchSide = (StackMachineNDPalindrome)this.clone();
	            m_multiMachine.addMachine(switchSide);
	            switchSide.m_input.advance();
	            switchSide.m_state = "z_r";
	            // or continue reading left side
	            m_stack.push(topChar);
	            m_stack.push(currentChar);
	            m_input.advance();
	            m_state = "z_l";
	            return;
	         // continue reading left side
	         } else {
	            m_stack.push(topChar);
	            m_stack.push(currentChar);
	            m_input.advance();
	            m_state = "z_l";
	            return;
	         }
	      }
	   }
	 

	@Override
	public boolean isFinalState() {
		return (m_state.equals("z+"));
	}

}
