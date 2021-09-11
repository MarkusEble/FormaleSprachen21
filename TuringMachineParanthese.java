import compiler.TuringMachine;
import compiler.TuringState;
import compiler.TuringAction;

public class TuringMachineParanthese extends TuringMachine {

	@Override
	public void initStateMap() {
		TuringState searchOpen = new TuringState("Search(");
		searchOpen.addTransition('(', new TuringAction(' ', 0, "Search)"));
		searchOpen.addTransition(' ', new TuringAction(' ', -1, "Search("));
		searchOpen.addTransition(')', new TuringAction(')', -1, "Search("));
		searchOpen.addTransition('$', new TuringAction('$', 1, "SearchN)"));
		m_stateMap.put("Search(", searchOpen);

		TuringState searchClose = new TuringState("Search)");
		searchClose.addTransition('(', new TuringAction(' ', 1, "Search)"));
		searchClose.addTransition(' ', new TuringAction(' ', 1, "Search)"));
		searchClose.addTransition(')', new TuringAction(' ', -1, "Search("));
		searchClose.addTransition('$', new TuringAction('$', 0, "error"));
		m_stateMap.put("Search)", searchClose);

		TuringState searchCloseNot = new TuringState("SearchN)");
		searchCloseNot.addTransition('(', new TuringAction('(', 0, "error"));
		searchCloseNot.addTransition(' ', new TuringAction(' ', 1, "SearchN)"));
		searchCloseNot.addTransition(')', new TuringAction(' ', 0, "error"));
		searchCloseNot.addTransition('$', new TuringAction('$', 0, "finished"));
		m_stateMap.put("SearchN)", searchCloseNot);
    }

	@Override
	public String getStartState() {
		return "Search(";
	}

}
