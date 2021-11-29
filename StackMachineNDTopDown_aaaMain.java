import java.io.OutputStreamWriter;

public class StackMachineNDTopDown_aaaMain {

	public static void main(String[] args) throws Exception {
        compiler.MultiMachine multiMachine = new compiler.MultiMachine();
		StackMachineNDTopDown_aaa parantheseMachine = new StackMachineNDTopDown_aaa(multiMachine);
        multiMachine.addMachine(parantheseMachine);
		OutputStreamWriter outStream = new OutputStreamWriter(System.out, "UTF-8");
		multiMachine.process("a+a+a", outStream);
	}

}
