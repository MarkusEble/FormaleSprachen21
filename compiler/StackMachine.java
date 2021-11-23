package compiler;

import java.io.OutputStreamWriter;
import java.util.Stack;

public abstract class StackMachine extends StateMachineBase {
	protected Stack<Character> m_stack;
	
	public StackMachine() {
		m_stack = new Stack<Character>();
	}

	public boolean isFinished() {
		if (m_state.equals("z-")) {
			return true;
		} else {
			return m_stack.empty() && m_input.currentChar() == 0 && isFinalState();
		}
	}

    protected char pop() {
        char topChar = m_stack.isEmpty() ? 0 : m_stack.pop();
        return topChar;
    }    

    protected void push(char c) {
        if (c != 0) {
            m_stack.push(c);
        }
    }
	
	// dump the current machine state
	public void traceState(OutputStreamWriter outStream) throws Exception {
		super.traceState(outStream);
		// dump stack content in reverse order with padding
		outStream.write(" | ");
		for (int i = m_stack.size(); i != 0; i-- ) {
			outStream.write(m_stack.elementAt(i-1));
		}
		outStream.write('*');
		for (int i = m_stack.size(); i < 10; i++) {
			outStream.write (' ');
		}
	}
	
	public void traceBlank(OutputStreamWriter outStream) throws Exception {
		super.traceBlank(outStream);
		// blank stack content
		for (int i = 0; i != 3 + 11; i++) {
			outStream.write(' ');
		}
	}
	
	public void traceHead(OutputStreamWriter outStream) throws Exception {
		super.traceHead(outStream);
		outStream.write(" | ");
		outStream.write("STACK");
		for (int i = 5; i != 11; i++) {
			outStream.write(' ');
		}
	}

	protected Character getStackTop() {
		return m_stack.elementAt(m_stack.size()-1);
	}

    protected boolean matchStackTop(String strToMatch) {
      int stackSize = m_stack.size();
      int strLen = strToMatch.length();
      int stackStartOffset = stackSize - strLen;
      if (stackStartOffset < 0) {
          return false;
      }
      for (int i = 0; i != strLen; ++i) {
          if (strToMatch.charAt(i) != m_stack.elementAt(stackStartOffset + i)) {
              return false;
          }
      }
      return true;
	}

}
