import java.io.OutputStreamWriter;

public class TuringMachineParantheseMain {

	public static void main(String[] args) throws Exception {
		TuringMachineParanthese parantheseMachine = new TuringMachineParanthese();
		OutputStreamWriter outStream = new OutputStreamWriter(System.out, "UTF-8");
		parantheseMachine.process("(()())", outStream);
	}

}
