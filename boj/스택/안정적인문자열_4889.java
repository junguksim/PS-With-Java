package 스택;

import java.io.*;
import java.util.Stack;

public class 안정적인문자열_4889 {
    public static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

    public static String getInput(int i) throws IOException {
        String[] strings = bufferedReader.readLine().split("");
        if(strings[0].equals("-")) {
            return "END";
        }
        Stack<String> stringStack = new Stack<>();
        int count = 0;
        for(String string : strings) {
            if(string.equals("{")) {
                stringStack.push(string);
            }
            else if(stringStack.isEmpty()) {
                count++;
                stringStack.push("{");
            }
            else {
                // "{" 가 있고 들어오는 스트링이 "}" 인 경우
                stringStack.pop();
            }
        }
        // if stack.size() > 0 => 전부 { 이고, 한개씩 바꿔서 짝 맞춰주면 되므로 나누기 2
        bufferedWriter.write(i+". "+(count + stringStack.size() / 2)+"\n");
        return null;
    }

    public static void main(String[] args) throws IOException {
        int index = 1;
        while(true) {
            String result = getInput(index);
            if(result != null &&result.equals("END")) {
                break;
            }
            index++;
        }
        bufferedWriter.close();
        bufferedReader.close();
    }
}
