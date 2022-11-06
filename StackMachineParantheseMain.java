import java.io.OutputStreamWriter;

public class StackMachineParantheseMain {

	public static void main(String[] args) throws Exception {
		StackMachineParanthese parantheseMachine = new StackMachineParanthese();
		OutputStreamWriter outStream = new OutputStreamWriter(System.out, "UTF-8");
		parantheseMachine.process("(())", outStream);
	}

}
