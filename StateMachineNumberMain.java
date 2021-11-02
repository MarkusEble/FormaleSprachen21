
import java.io.OutputStreamWriter;

public class StateMachineNumberMain {

	public static void main(String[] args) throws Exception {
		compiler.StateMachine numberMachine = new StateMachineTableNumber();
		compiler.StateMachine hexNumberMachine = new StateMachineTableHexNumber();
		compiler.MultiMachine multiMachine = new compiler.MultiMachine();
		multiMachine.addMachine(numberMachine);
		multiMachine.addMachine(hexNumberMachine);
		OutputStreamWriter outStream = new OutputStreamWriter(System.out, "UTF-8");
		multiMachine.process("0x12A", outStream);

		System.out.println();
		System.out.println(numberMachine.asDot());
		System.out.println(hexNumberMachine.asDot());
	}

}
