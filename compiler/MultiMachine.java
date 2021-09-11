package compiler;

import java.io.OutputStreamWriter;
import java.util.Vector;

/**
 * container to hold and execute multiple machines in parallel
 * allows non-deterministic machines to clone themselves
 */

public class MultiMachine extends MachineBase {
	protected Vector<MachineBase> m_machineList;
	
	public MultiMachine() {
		m_machineList = new Vector<MachineBase>();
	}
	
	public void addMachine(MachineBase machine) {
		m_machineList.add(machine);
	}

	@Override
	public void init(String input) {
		// initialize each machine
		for (int i = 0; i != m_machineList.size(); i++) {
			m_machineList.elementAt(i).init(input);
		}
	}

	@Override
	public void step() throws Exception {
		// proceed one step on each unfinished machine
		int machineListSizeAtStepBegin = m_machineList.size();
		for (int i = 0; i != machineListSizeAtStepBegin; i++) {
			MachineBase curMachine = m_machineList.elementAt(i);
			if (!curMachine.isFinished()) {
				curMachine.step();
			}
		}
	}

	@Override
	public boolean isFinished() {
		// check if all machines are finished
		boolean isFinished = true;
		for (int i = 0; i != m_machineList.size(); i++) {
			isFinished &= m_machineList.elementAt(i).isFinished();
		}
		return isFinished || isAccepted();
	}

	@Override
	public boolean isAccepted() {
		// check if any machine has accepted
		boolean isAccepted = false;
		for (int i = 0; i != m_machineList.size(); i++) {
			isAccepted |= m_machineList.elementAt(i).isAccepted(); 
		}
		return isAccepted;
	}

	@Override
	public void trace(OutputStreamWriter outStream) throws Exception {
		// trace each machine
		for (int i = 0; i != m_machineList.size(); i++) {
			m_machineList.elementAt(i).trace(outStream);
			outStream.write("   ");
		}
	}

	@Override
	public void traceHead(OutputStreamWriter outStream) throws Exception {
		// trace each machine
		for (int i = 0; i != m_machineList.size(); i++) {
			m_machineList.elementAt(i).traceHead(outStream);
			outStream.write("   ");
		}
	}
}
