import java.io.OutputStreamWriter;

public class StackMachineNDTopDownParantheseMain {

	public static void main(String[] args) throws Exception {
        compiler.MultiMachine multiMachine = new compiler.MultiMachine();
		StackMachineNDTopDownParanthese parantheseMachine = new StackMachineNDTopDownParanthese(multiMachine);
        multiMachine.addMachine(parantheseMachine);
		OutputStreamWriter outStream = new OutputStreamWriter(System.out, "UTF-8");
		multiMachine.process("()()", outStream);
	}

}
