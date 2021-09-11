import java.io.OutputStreamWriter;

public class TuringMachineMirrorMain {

	public static void main(String[] args) throws Exception {
		TuringMachineMirror mirrorMachine = new TuringMachineMirror();
		OutputStreamWriter outStream = new OutputStreamWriter(System.out, "UTF-8");
		mirrorMachine.process("someInput", outStream);
	}

}
