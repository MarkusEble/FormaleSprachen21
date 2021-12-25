import java.io.OutputStreamWriter;

public class LexerMain {

    public static void main(String[] args) throws Exception {
        compiler.Lexer lexer = new compiler.Lexer();
        OutputStreamWriter outStream = new OutputStreamWriter(System.out, "UTF-8");
        compiler.StateMachineBase numberMachine = new StateMachineTableNumber();
        lexer.addMachine(numberMachine);
        compiler.StateMachineBase hexNumberMachine = new StateMachineTableHexNumber();
        lexer.addMachine(hexNumberMachine);
        lexer.processInput("0xAAC 452 0x217  46", outStream);
    }

}
