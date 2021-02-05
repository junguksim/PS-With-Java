package swea;

import java.io.*;
import java.util.Stack;

public class Solution_계산기2_D4 {
    static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static char[] chars;
    static int N;

    static char[] makePostfix(char[] chars) {
        StringBuilder stringBuilder = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < N; i++) {
            if(Character.isDigit(chars[i])) {
                stringBuilder.append(chars[i]);
            } else {
                if(stack.isEmpty()) {
                    stack.push(chars[i]);
                    continue;
                } // 스택이 비었으면 그냥 넣어준다.
                if (chars[i] == '+') {
                    while (!stack.isEmpty()) {
                        stringBuilder.append(stack.pop());
                    }
                    stack.push('+');
                    continue;
                } // '+'라면, stack이 빌 때까지 전부 출력해주고, 자신을 stack에 넣는다.
                while (!stack.isEmpty() && stack.peek() == '*') {
                    stringBuilder.append(stack.pop());
                }
                stack.push('*'); // '*' 라면, 스택에서 '*'를 만날 때 까지만 pop해서 출력해주고, 자신을 stack에 넣는다.
            }
        }
        while(!stack.isEmpty()) {
            stringBuilder.append(stack.pop());
        } // 스택에 남아있는게 있다면 전부 출력해준다.
        return stringBuilder.toString().toCharArray();
    }

    static void solve(int idx) throws IOException {
        N = Integer.parseInt(bufferedReader.readLine());
        chars = bufferedReader.readLine().toCharArray();
        char[] postfix = makePostfix(chars);
        Stack<Integer> stack = new Stack<>();
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0 ; i < N; i++) {
            if(Character.isDigit(postfix[i])) {
                stack.push(Character.getNumericValue(postfix[i])); // 숫자라면 스택에 담아준다.
            } else {
                if(stack.size() > 1) { //연산자라면?
                    int num1 = stack.pop();
                    int num2 = stack.pop(); // 스택에서 피연산자 두개를 뽑아준다.
                    int result = 0;
                    if(postfix[i] == '*') {
                        result = num1 * num2;
                    } else {
                        result = num1 + num2;
                    } // 곱하기 더하기 해주고
                    stack.push(result); // 결과값을 다시 스택에 담는다.
                }
            }
        }
        stringBuilder.append("#").append(idx).append(" ").append(stack.pop()).append("\n");
        bufferedWriter.write(stringBuilder.toString());
    }

    public static void main(String[] args) throws IOException {
        for (int i = 1 ; i <= 10; i++) {
            solve(i);
        }
        bufferedWriter.close();
        bufferedReader.close();
    }
}
