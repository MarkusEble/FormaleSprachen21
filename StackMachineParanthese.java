

public class StackMachineParanthese extends compiler.StackMachine {
	


    @Override
    public String getStartState() {
       // expect opening parantheses
       return "z";
    }

    @Override
    public void step() {
       if ((m_state == "z") && (m_stack.isEmpty()) && (m_input.currentChar() == 0)) {
         m_state = "z+";
         return;
       }

       // if current input is "("
       if (m_input.currentChar() == '(') {
         m_stack.push('(');
         m_input.advance();
       }  else if (m_input.currentChar() == ')' ) {
         // if top of stack is "("
         if (m_stack.lastElement() == '(') {
           m_stack.pop();
           m_input.advance();
         } else {
          m_state = "z-";
         }
       } else {
         m_state = "z-";
       }    
    }

	@Override
	public boolean isFinalState() {
		return (m_state.equals("z+"));
	}

}
