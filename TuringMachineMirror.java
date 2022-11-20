public class TuringMachineMirror extends compiler.TuringMachineBase {

	@Override
	public String getStartState() {
		return "read";
	}

	@Override
	public void step() throws Exception {
		char c = readChar();
		if (m_state.equals("read")) {
			if (c == ' ') {
				writeChar(c);				
				m_state = "read";
				left();
			} else if (c == '$') {
				writeChar(c);
				m_state = "finished";
			} else {
				writeChar(' ');
				m_state = new String("write_");
				m_state += Character.toString(c);
			}
		} else if (m_state.startsWith("write_")) {
			if (c == '$') {
				writeChar(m_state.charAt(6));
				m_state = "back";
			} else {
				right();
			}
		} else if (m_state.equals("back")) {
			if (c == ' ') {
				writeChar(c);
				m_state = "read";
			} else {
				writeChar(c);
				m_state = "back";
				left();
			}
		} else {
			m_state = "error";
		}
	}

}
