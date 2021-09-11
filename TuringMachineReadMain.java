import java.io.OutputStreamWriter;

public class TuringMachineReadMain {

	public static void main(String[] args) throws Exception {
		TuringMachineRead readMachine = new TuringMachineRead();
		OutputStreamWriter outStream = new OutputStreamWriter(System.out, "UTF-8");
		readMachine.process("someInput", outStream);
	}

}
