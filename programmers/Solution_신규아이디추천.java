package programmers;

public class Solution_신규아이디추천 {
    private static String upperToLower(String id) {
        return id.toLowerCase();
    }

    private static String deleteNotAllowedChars(String id) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < id.length(); i++) {
            char c = id.charAt(i);
            if((c-'a' >= 0 && 'z' - c >= 0) || c == '-' || c == '_' || c == '.' || (c - '0' >= 0 && '9' - c >= 0)) {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    private static String changeSerialPoints(String id) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < id.length()) {
            char c = id.charAt(i);
            sb.append(c);
            if(c == '.') {
                int next = i;
                while (next < id.length()) {
                    char nextC = id.charAt(next);
                    if(nextC == '.') {
                        next++;
                    } else {
                        break;
                    }
                }
                i = next;
                continue;
            }
            i++;
        }
        return sb.toString();
    }

    private static String deleteFirstOrLastPoints(String id) {
        if(id.length() == 0) return "";
        if(id.charAt(0) == '.') {
            id = id.substring(1);
        }
        if(id.length() == 0) return "";
        if(id.charAt(id.length()-1) == '.') {
            id = id.substring(0, id.length() - 1);
        }
        return id;
    }

    public String solution(String new_id) {
        String answer = "";
        new_id = upperToLower(new_id);
        System.out.println(new_id);
        new_id = deleteNotAllowedChars(new_id);
        System.out.println(new_id);
        new_id = changeSerialPoints(new_id);
        System.out.println(new_id);
        new_id = deleteFirstOrLastPoints(new_id);
        System.out.println(new_id);
        if(new_id.length() == 0) {
            new_id = "a";
        } // 5단계
        if(new_id.length() >= 16) {
            new_id = new_id.substring(0, 15);
            if(new_id.charAt(new_id.length() -1) == '.') {
                new_id = new_id.substring(0, new_id.length() - 1);
            }
        }
        if(new_id.length() <= 2) {
            char last = new_id.charAt(new_id.length()-1);
            StringBuilder sb = new StringBuilder(new_id);
            while (sb.length() < 3) {
                sb.append(last);
            }
            new_id = sb.toString();
        }
        System.out.println(new_id);
        return new_id;
    }

    public static void main(String[] args) {
        new Solution_신규아이디추천().solution(".1." );
    }
}
