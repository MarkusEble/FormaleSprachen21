public class TuringMachineRead extends compiler.TuringMachineBase {

	@Override
	public String getStartState() {
		return "read";
	}

	@Override
	public void step() throws Exception {
		char c = readChar();
		if (m_state.equals("read")) {
			if (c == '$') {
				writeChar(c);
				m_state = "finished";
			} else {
				writeChar(c);
				m_state = "read";
				left();
			}
		} else {
			m_state = "error";
		}
	}

}
