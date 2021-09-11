
import java.io.OutputStreamWriter;

public class StateMachineNumberMain {

	public static void main(String[] args) throws Exception {
		compiler.StateMachineBase numberMachine = new StateMachineTableNumber();
		compiler.StateMachineBase hexNumberMachine = new StateMachineTableHexNumber();
		compiler.MultiMachine multiMachine = new compiler.MultiMachine();
		multiMachine.addMachine(numberMachine);
		multiMachine.addMachine(hexNumberMachine);
		OutputStreamWriter outStream = new OutputStreamWriter(System.out, "UTF-8");
		multiMachine.process("0x12A", outStream);
	}

}
