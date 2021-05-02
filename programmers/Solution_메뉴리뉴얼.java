package programmers;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Solution_메뉴리뉴얼 {
    private static int[] howManyOrdered; //수정
    private static ArrayList<Character> possibleMenus;
    private static ArrayList<String> result;
    private static int beforeCourse;
    private static final int MAX_MENU_COUNT = 26, SHOULD_ORDERED_COUNT = 2;

    private static void countHowManyOrdered(String[] orders) {
        for(String order : orders) {
            char[] menus = order.toCharArray();
            for(char menu : menus) {
                howManyOrdered[menu - 'A']++;
            }
        }
    }

    private static void setPossibleMenu() {
        for(int i = 0; i < MAX_MENU_COUNT; i++) {
            if(howManyOrdered[i] >= SHOULD_ORDERED_COUNT) possibleMenus.add((char) (i + 'A') );
        }
    }

    public String[] solution(String[] orders, int[] course) {
        howManyOrdered = new int[MAX_MENU_COUNT];
        possibleMenus = new ArrayList<>();
        countHowManyOrdered(orders);
        setPossibleMenu();
        System.out.println(possibleMenus.toString());
        ArrayList<String> answerList = new ArrayList<>();
        for(int c : course) {
            result = new ArrayList<>();
            beforeCourse = 0;
            makeCombination(0,0,new char[c], c, orders);
            //System.out.println(result.toString());
            answerList.addAll(result);
        }
        Collections.sort(answerList);
        String[] answer = new String[answerList.size()];
        for(int i = 0 ; i < answerList.size(); i++) {
            answer[i] = answerList.get(i);
        }
        return answer;
    }

    private static int howManyCustomersOrderedThisCourse(char[] course, String[] orders) {
        int result = 0;
        for(String order : orders) {
            boolean isOrdered = true;
            for (char c : course) {
                if (!order.contains(String.valueOf(c))) {
                    isOrdered = false;
                    break;
                }
            }
            if(isOrdered) result++;
        }
        return  result;
    }

    private static void getCourseResult(char[] course, String[] orders) {
        int howManyLikesCourse = howManyCustomersOrderedThisCourse(course, orders);
        if(howManyLikesCourse < SHOULD_ORDERED_COUNT) return;
        if(beforeCourse < howManyLikesCourse) {
            result = new ArrayList<>();
            result.add(new String(course));
            beforeCourse = howManyLikesCourse;
        } else if(beforeCourse == howManyLikesCourse) {
            result.add(new String(course));
        }
    }

    private static void makeCombination(int cnt, int start, char[] course, int R, String[] orders) {
        if(cnt == R) {
            //System.out.println(Arrays.toString(course));
            for(int i = 0 ; i < course.length; i++) {
                if(howManyOrdered[course[i] - 'A'] < SHOULD_ORDERED_COUNT) return;
            }
            getCourseResult(course, orders);
            return;
        }
        for(int i = start; i < possibleMenus.size(); i++) {
            course[cnt] = possibleMenus.get(i);
            makeCombination(cnt + 1, i + 1, course, R, orders);
        }
    }

    public static void main(String[] args) {
        //System.out.println(Arrays.toString(new Solution_메뉴리뉴얼().solution(new String[]{"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"}, new int[]{2, 3, 4})));
        //System.out.println(Arrays.toString(new Solution_메뉴리뉴얼().solution(new String[]{"ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD"}, new int[]{2, 3, 5})));
        System.out.println(Arrays.toString(new Solution_메뉴리뉴얼().solution(new String[]{"XYZ", "XWY", "WXA"}, new int[]{2, 3, 4})));
    }
}
