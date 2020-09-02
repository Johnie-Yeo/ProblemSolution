package Programmers.kakao;

import Test.Test;

import java.util.*;
import java.util.stream.Collectors;

public class CandidateKey {
    public static void main(String[] args) {
        new CandidateKey().test();
    }

    private void test() {
        String[][] relation = {
                {"100", "ryan", "music", "2"},
                {"200", "apeach", "math", "2"},
                {"300", "tube", "computer", "3"},
                {"400", "con", "computer", "4"},
                {"500", "muzi", "music", "3"},
                {"600", "apeach", "music", "2"}
        };

        int result = solution(relation);
        int expect = 2;
        Test.test(result, expect).printResult();
    }

    private class Key {
        private ArrayList<Integer> comb;

        public Key() {
            this.comb = new ArrayList<>();
        }

        public Key(ArrayList<Integer> list) {
            this.comb = list.stream().collect(Collectors.toCollection(ArrayList::new));
        }

        @Override
        public int hashCode() {
            return comb.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            Key o = (Key)obj;
            int size = this.comb.size();
            if(size != o.comb.size()) {
                return false;
            }
            for(int i = 0; i < size; i++) {
                if(this.comb.get(i) != o.comb.get(i)) {
                    return false;
                }
            }

            return true;
        }
    }

    public int solution(String[][] relation) {
        Set<Key> set = new HashSet<>();

        int degree = relation[0].length;
        ArrayList<ArrayList<Integer>> keys = getAllKeyCombination(degree);

        int count = 0;

        for(ArrayList<Integer> key : keys) {
            if(!isSatisfyMinimum(key, set)) {
                continue;
            }
            if(isIdentifiable(key, relation)) {
                Key cur = new Key(key);
                set.add(cur);
                count++;
            }
        }

        return count;
    }


    private ArrayList<ArrayList<Integer>> getAllKeyCombination(int size) {
        ArrayList<ArrayList<Integer>> prev = getInitialCombination(size);
        ArrayList<ArrayList<Integer>> comb = dfs(1, size, prev);
        prev.addAll(comb);

        return prev;
    }

    private ArrayList<ArrayList<Integer>> getInitialCombination(int size) {
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();

        for(int i = 0; i < size; i++) {
            ArrayList<Integer> tmp = new ArrayList<>();
            tmp.add(i);
            list.add(tmp);
        }
        return list;
    }

    private ArrayList<ArrayList<Integer>> dfs(int count, int size, ArrayList<ArrayList<Integer>> prevComb) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();

        if(count >= size) {
            return result;
        }

        for(ArrayList<Integer> prev : prevComb) {
            int last = prev.get(prev.size()-1) + 1;
            for(int i = last; i < size; i++) {
                ArrayList<Integer> tmp = new ArrayList<>(prev);
                tmp.add(i);
                result.add(tmp);
            }
        }

        ArrayList<ArrayList<Integer>> tmp = dfs(count+1, size, result);
        result.addAll(tmp);

        return result;
    }

    private boolean isSatisfyMinimum(ArrayList<Integer> list, Set<Key> set) {
        for(Key key : set) {
            ArrayList<Integer> cur = key.comb;
            if(list.containsAll(cur)) {
                return false;
            }
        }
        return true;
    }

    private boolean isIdentifiable(ArrayList<Integer> key, String[][] relation) {
        int cardinality = relation.length;
        Set<String> set = new HashSet<>();

        for(String[] tuple : relation) {
            String cur = toString(tuple, key);
            set.add(cur);
        }

        return set.size() == cardinality;
    }

    private String toString(String[] strings, ArrayList<Integer> key) {
        StringBuilder sb = new StringBuilder();
        for(int index : key) {
            sb.append(strings[index]);
            sb.append(" ");
        }
        return sb.toString();
    }
}
