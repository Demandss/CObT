package su.demands.cobt;

import java.util.*;

public class Controller {

    private String[] readLine;
    private List<String> result = new ArrayList<>(Collections.emptyList());
    private Stack<String> st = new Stack<>();
    private String firstOperand, assignSymbol;
    private int operationNumber = 0;
    private int currentLine = 0;

    public Controller() {
    }

    public List<String> getResult() {
        return result;
    }

    public void processTriads(String var) {
        this.readLine = addSpaces(var).split("\\s");

        firstOperand = readLine[0];
        assignSymbol = readLine[1];

        readLine = Arrays.copyOfRange(readLine, 2, readLine.length);
        for (String input : readLine) {
            st.push(input);
            if (st.size() == 3)
            {
                String a = st.peek();
                st.pop();
                String operation = st.peek();
                st.pop();
                String b = st.peek();
                st.pop();
                if (operation.equals("^"))
                {
                    operationNumber++;
                    result.add(String.format("%d ) * ( %s, %s )\n",operationNumber,b,b));
                    for (int i = 2; i < Integer.parseInt(a); i++)
                    {
                        operationNumber++;
                        result.add(String.format("%d ) * ( ^%d, %s )\n",operationNumber,operationNumber - 1,b));
                    }
                    st.push("^" + operationNumber);
                }
                else
                {
                    operationNumber++;
                    result.add(String.format("%d ) %s ( %s, %s )\n",operationNumber,operation,b,a));
                    st.push("^" + operationNumber);
                }
            }
        }
        result.add(String.format("%d ) %s ( %s, %s )\n",++operationNumber,assignSymbol,firstOperand,st.peek()));
    }

    boolean hasMoreLines() {
        return result.size() > currentLine;
    }

    String nextLine() {
        return result.get(currentLine++);
    }

    private String addSpaces(String s) {
        char[] signs = {'+', '-', '*', ':', '^'};
        StringBuilder newString = new StringBuilder();
        for (char c : s.toCharArray()) {
            for (char sign : signs) {
                if (c == sign) newString.append(' ');
            }
            newString.append(c);
        }
        return newString.toString().replaceAll("[\\s]+"," ");
    }
}
