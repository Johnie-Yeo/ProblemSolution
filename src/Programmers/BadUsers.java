package Programmers;

import Test.OldTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class BadUsers{
    private class Candidate{
        ArrayList<String> list;
        public Candidate(){
            this.list = new ArrayList<>();
        }
        public Candidate(ArrayList<String> list){
            this.list = list;
        }
        public void add(String id){
            this.list.add(id);
        }
        public void remove(String id){
            int index = 0;
            for(String cur : this.list){
                if(cur.equals(id)){
                    this.list.remove(index);
                    return;
                }
                index++;
            }
        }
        public void sort(){
            Collections.sort(this.list);
        }
        public Candidate clone(){
            ArrayList<String> list = new ArrayList<>();
            list.addAll(this.list);
            return new Candidate(list);
        }
        @Override
        public int hashCode() {
            StringBuilder sb = new StringBuilder();
            for(String id : this.list){
                sb.append(id);
                sb.append(",");
            }
            return sb.toString().hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            Candidate oth = (Candidate)obj;
            int size = this.list.size();
            if(size != oth.list.size()){
                return false;
            }
            for(int i = 0; i < size; i++){
                if(this.list.get(i) != oth.list.get(i)){
                    return false;
                }
            }
            return true;
        }
    }
    public static void main(String[] args) {
        new BadUsers().test();
    }
    public void test(){
        OldTest test = new OldTest();

        String[] userId, bannedId;
        int result, expect;

        userId = new String[]{"frodo", "fradi", "crodo", "abc123", "frodoc"};
        bannedId = new String[]{"fr*d*", "abc1**"};
        result = solution(userId, bannedId);
        expect = 2;
        test.test(result, expect).printResult();

        userId = new String[]{"frodo", "fradi", "crodo", "abc123", "frodoc"};
        bannedId = new String[]{"*rodo", "*rodo", "******"};
        result = solution(userId, bannedId);
        expect = 2;
        test.test(result, expect).printResult();

        userId = new String[]{"frodo", "fradi", "crodo", "abc123", "frodoc"};
        bannedId = new String[]{"fr*d*", "*rodo", "******", "******"};
        result = solution(userId, bannedId);
        expect = 3;
        test.test(result, expect).printResult();
    }
    public int solution(String[] user_id, String[] banned_id) {
        ArrayList<ArrayList<String>> candidates = getCandidateList(user_id, banned_id);
        HashSet<String> set = new HashSet<>();
        int index = 0;
        int max = candidates.size();
        Candidate list = new Candidate();
        HashSet<Candidate> answer = dfs(candidates, index, max, set, list);
        return answer.size();
    }

    private HashSet<Candidate> dfs(ArrayList<ArrayList<String>> candidates, int index, int max, HashSet<String> set, Candidate list) {
        HashSet<Candidate> result = new HashSet<>();
        if(index == max){
            Candidate tmp = list.clone();
            tmp.sort();
            result.add(tmp);
            return result;
        }
        ArrayList<String> cur = candidates.get(index);
        for(String candidate : cur){
            if(!set.contains(candidate)){
                set.add(candidate);
                list.add(candidate);
                HashSet<Candidate> tmp = dfs(candidates, index+1, max, set, list);
                result.addAll(tmp);
                set.remove(candidate);
                list.remove(candidate);
            }
        }
        return result;
    }

    private ArrayList<ArrayList<String>> getCandidateList(String[] user_id, String[] banned_id) {
        ArrayList<ArrayList<String>> list = new ArrayList<>();

        for(String id : banned_id){
            ArrayList<String> candidate = getCandidates(id, user_id);
            list.add(candidate);
        }

        return list;
    }

    private ArrayList<String> getCandidates(String banned_id, String[] user_id) {
        ArrayList<String> list = new ArrayList<>();

        for(String user : user_id){
            if(isBanned(banned_id, user)){
                list.add(user);
            }
        }

        return list;
    }

    private boolean isBanned(String banned_id, String user_id){
        int length = banned_id.length();
        if(length != user_id.length()){
            return false;
        }
        for(int i = 0; i < length; i++){
            if(banned_id.charAt(i) != '*' && banned_id.charAt(i) != user_id.charAt(i)){
                return false;
            }
        }
        return true;
    }
}