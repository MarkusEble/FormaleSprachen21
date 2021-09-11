package compiler;

import java.util.Stack;

/**
 * non-deterministic stack machine
 *
 */
public abstract class StackMachineND extends StackMachine {
	// container managing all clones
	protected MultiMachine m_multiMachine;

	public StackMachineND(MultiMachine multiMachine) {
		m_multiMachine = multiMachine;
	}

	/**
	 * clone machine in case of non-deterministic decision
	 */
	public Object clone() throws CloneNotSupportedException {
		StackMachineND theClone = (StackMachineND)super.clone();
		theClone.m_multiMachine = m_multiMachine;
		theClone.m_stack = (Stack<Character>)m_stack.clone();
		return theClone;
	}
}
