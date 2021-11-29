import java.io.OutputStreamWriter;

public class StackMachineBottomUp_aaaMain {

	public static void main(String[] args) throws Exception {
		StackMachineBottomUp_aaa parantheseMachine = new StackMachineBottomUp_aaa();
		OutputStreamWriter outStream = new OutputStreamWriter(System.out, "UTF-8");
		parantheseMachine.process("a+a+a", outStream);
	}

}
