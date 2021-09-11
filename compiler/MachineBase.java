package compiler;

import java.io.OutputStreamWriter;

/**
 * base class for accepting input with formal machines
 */
public abstract class MachineBase {
	
	/**
	 * initialize processing of input
	 */
	public abstract void init(String input);
	
	/**
	 * proceed one step
	 * implements the state transition function f of the machine model
	 * may consume input
	 */
	public abstract void step() throws Exception;
	
	/**
	 * is processing finished?
	 */
	public abstract boolean isFinished();
	
	/**
	 * has input been accepted
	 */
	public abstract boolean isAccepted();

	/**
	 * dump the current machine state
	 */
	public abstract void trace(OutputStreamWriter outStream) throws Exception;	

	/**
	 * dump headline for dumping machine state
	 */
	public abstract void traceHead(OutputStreamWriter outStream) throws Exception;	

	/**
	 * process until finished and dump results to outStream
	 */
	public void process(String input, OutputStreamWriter outStream) throws Exception {
		init(input);
		traceHead(outStream);
		outStream.write('\n');

		// iterate until finished
		while (!isFinished()) {
			// dump state
			trace(outStream);
			outStream.write('\n');
			// execute next step
			step();
		}
		// dump final state
		trace(outStream);
		outStream.write('\n');
		if (isAccepted()) {
			outStream.write("ACCEPT\n");
		} else {
			outStream.write("FAIL\n");		
		}
		outStream.flush();
	}
}
