
import java.io.OutputStreamWriter;

public class StateMachineNDABReaderMain {

	public static void main(String[] args) throws Exception {
		compiler.MultiMachine multiMachine = new compiler.MultiMachine();
		compiler.StateMachineND abReaderMachine = new StateMachineNDABReader(multiMachine);
		multiMachine.addMachine(abReaderMachine);
		OutputStreamWriter outStream = new OutputStreamWriter(System.out, "UTF-8");
		multiMachine.process("aab", outStream);
	}

}
