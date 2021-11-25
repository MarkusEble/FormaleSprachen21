import java.io.OutputStreamWriter;

public class StackMachineNDPalindromeMain {

	public static void main(String[] args) throws Exception {
		compiler.MultiMachine multiMachine = new compiler.MultiMachine();
		StackMachineNDPalindrome palindromeMachine = new StackMachineNDPalindrome(multiMachine);
		multiMachine.addMachine(palindromeMachine);
		OutputStreamWriter outStream = new OutputStreamWriter(System.out, "UTF-8");
		multiMachine.process("abbccbba", outStream);
	}

}
