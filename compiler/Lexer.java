package compiler;

import java.io.OutputStreamWriter;
import java.util.Vector;

public class Lexer  {

    static class MachineInfo {

        public StateMachineBase m_machine;
        public int m_acceptPos;
    
        public MachineInfo(StateMachineBase machine) {
            m_machine = machine;
            m_acceptPos = 0;
        }
        
        public void init(String input) {
            m_acceptPos = 0;
            m_machine.init(input);
        }
    }
    
    static class Token {
        public String m_word;
        public String m_kind;
        
        public Token(String word, String kind) {
            m_word = word;
            m_kind = kind;
        }
    }
    
    protected Vector<MachineInfo> m_machineList;
    protected String m_input;
    
    public Lexer() {
        m_machineList = new Vector<MachineInfo>();
    }
    
    public void addMachine(StateMachineBase machine) {
        m_machineList.add(new MachineInfo(machine));
    }
    
    public void init (String input) {
        m_input = input;
    }
    
    public void initMachines(String input) {
        for (MachineInfo machine : m_machineList) {
            machine.init(input);
        }
    }
    
    public Token nextWord() throws Exception {
        int curPos = 0;
        // initialize machines
        initMachines(m_input);
        // while some machine are in process
        boolean machineActive;
        do {
            machineActive = false;
            // for each machine in process
            for (MachineInfo machine : m_machineList) {
                if (machine.m_machine.isFinished()) {
                    continue;
                }
                machineActive = true;
                // next step
                machine.m_machine.step();
                // if possible final state
                if (machine.m_machine.isFinalState()) {
                    // update last position machine would accept
                    machine.m_acceptPos = curPos + 1;
                }
            } // end for each machine in process
            curPos++;
        } while (machineActive); // end while some machine in process
        // select first machine with largest final pos (greedy)
        MachineInfo bestMatch = new MachineInfo(null);
        for (MachineInfo machine : m_machineList) {
            if (machine.m_acceptPos > bestMatch.m_acceptPos) {
                bestMatch = machine;
            }
        }
        // set next word [start pos, final pos)
        String nextWord = m_input.substring(0, bestMatch.m_acceptPos);
        m_input = m_input.substring(bestMatch.m_acceptPos);
        return new Token(nextWord, bestMatch.m_machine.getName());
    }
    
    boolean isWhitespace(char c) {
        if (c == ' ' || c == '\t' || c == '\n') {
            return true;
        } else {
            return false;
        }
    }

    void skipWhiteSpace() {
        int i = 0;
        while (i < m_input.length() && isWhitespace(m_input.charAt(i))) {
            i++;
        }
        m_input = m_input.substring(i);
    }

    public void processInput(String input, OutputStreamWriter outStream) throws Exception {
        m_input = input;
        // skip white space
        skipWhiteSpace();
        // while input available
        while (!m_input.isEmpty()) {
            // get next word
            Token curWord = nextWord();
            // break on failure
            if (curWord.m_word.isEmpty()) {
                outStream.write("ERROR\n");
                break;
            }
            // print word
            outStream.write(curWord.m_kind);
            outStream.write(" ");
            outStream.write(curWord.m_word);
            outStream.write("\n");
            outStream.flush();
            // skip whitespace
            skipWhiteSpace();
        }
    }
}
