/**
 * sample state machine with hand written implementation of transition function step
 * accept natural numbers 0|[1-9][0-9]*
 */
public class StateMachineNumber extends compiler.StateMachineBase {
	
	@Override
	public String getStartState() {
		return "firstDigit";
	}

	@Override
	public void step() {
		char curChar = m_input.currentChar();

		// firstDigit
		if (m_state.equals("firstDigit")) {
		   // 0 => whole number 0
		   if (curChar == '0') {
			   m_state = "number0";
		   } else if ('1' <= curChar && curChar <= '9') {
			   m_state = "nextDigit";
		   } else {
			   m_state = "error"; 
		   }
		// number0
		} else if (m_state.equals("number0")) {
			m_state = "error";
		// nextDigit
		} else if (m_state.equals("nextDigit")) {
		   if ('0' <= curChar && curChar <= '9') {
			   m_state = "nextDigit";
		   } else {
			   m_state = "error"; 
		   }			
		} else {
			m_state = "error";			
		}
		 
		m_input.advance();
	}

	@Override
	public boolean isFinalState() {
		return (m_state.equals("number0") || m_state.equals("nextDigit"));
	}

}
