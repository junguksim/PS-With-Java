package programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;

public class Solution_불량사용자 {
    private static int bannedCount, answer;
    private static ArrayList<String>[] ban;
    private static ArrayList<Integer>[] banIdx;
    private static HashSet<int[]> bannedCombi;

    private static void dfs(int depth, boolean[] picked) {
        if(depth == bannedCount) {
            int[] willAdd = new int[bannedCount];
            int idx = 0;
            for(int i = 0 ; i < picked.length; i++) {
                if(picked[i]) {
                    willAdd[idx] = i;
                    idx++;
                }
            }
            if(bannedCombi.size() > 0) {
                Iterator<int[]> iter = bannedCombi.iterator();
                boolean canAdd = true;
                while (iter.hasNext()) {
                    int[] next = iter.next();
                    Arrays.sort(next);
                    int cnt = 0;
                    for(int i = 0 ; i < next.length; i++) {
                        if(next[i] == willAdd[i]) {
                            cnt++;
                        }
                    }
                    if(cnt == willAdd.length) {
                        canAdd = false;
                        break;
                    }
                }
                if(canAdd) {
                    bannedCombi.add(willAdd);
                }
            } else {
                bannedCombi.add(willAdd);
            }
            return;
        }
        ArrayList<Integer> willBan = banIdx[depth];
        for(int willBanIdx : willBan) {
            boolean[] newPicked = picked.clone();
            if(!newPicked[willBanIdx]) { // 아직 안뽑은 수의 경우
                newPicked[willBanIdx] = true;
                dfs(depth + 1, newPicked);
            }
        }
    }

    private static void setBanIdx(String[] user_id, String[] banned_id) {
        ban = new ArrayList[bannedCount];
        banIdx = new ArrayList[bannedCount];
        answer = 0;
        for(int k = 0 ; k < banned_id.length; k++) {
            ban[k] = new ArrayList<>();
            banIdx[k] = new ArrayList<>();
            String banned = banned_id[k];
            for(int j = 0 ; j < user_id.length; j++) {
                String userId = user_id[j];
                if(banned.length() == userId.length()) {
                    boolean isFit = true;
                    for(int i = 0; i < banned.length(); i++) {
                        if(banned.charAt(i) != userId.charAt(i)) {
                            if(banned.charAt(i) != '*') {
                                isFit = false;
                                break;
                            }
                        }
                    }
                    if(isFit) {
                        ban[k].add(userId);
                        banIdx[k].add(j);
                    }
                }
            }
        }
    }

    public static int solution(String[] user_id, String[] banned_id) {
        bannedCount = banned_id.length;
        bannedCombi = new HashSet<>();
        setBanIdx(user_id, banned_id);
        boolean[] picked = new boolean[user_id.length];
        dfs(0, picked);
        answer = bannedCombi.size();
        return answer;
    }

    public static void main(String[] args) {
        //System.out.println(solution(new String[]{"frodo", "fradi", "crodo", "abc123", "frodoc"}, new String[]{"fr*d*"}));
        System.out.println(solution(new String[]{"frodo", "fradi", "crodo", "abc123", "frodoc"}, new String[]{"fr*d*", "abc1**"}));
        System.out.println(solution(new String[]{"frodo", "fradi", "crodo", "abc123", "frodoc"}, new String[]{"*rodo", "*rodo", "******"}));
        System.out.println(solution(new String[]{"frodo", "fradi", "crodo", "abc123", "frodoc"}, new String[]{"fr*d*", "*rodo", "******", "******"}));
    }
}
