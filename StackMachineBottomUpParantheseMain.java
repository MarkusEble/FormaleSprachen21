import java.io.OutputStreamWriter;

public class StackMachineBottomUpParantheseMain {

	public static void main(String[] args) throws Exception {
		StackMachineBottomUpParanthese parantheseMachine = new StackMachineBottomUpParanthese();
		OutputStreamWriter outStream = new OutputStreamWriter(System.out, "UTF-8");
		parantheseMachine.process("()(())", outStream);
	}

}
