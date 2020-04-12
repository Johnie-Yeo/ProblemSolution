// https://programmers.co.kr/learn/courses/30/lessons/43163
package Programmers;

import Test.Test;

import java.util.*;

public class ConvertWord {
    public static void main(String[] args) {
        new ConvertWord().test();
    }
    private void test(){
        Test test = new Test();

        String begin = "hit";
        String target = "cog";
        String[] words = {"hot", "dot", "dog", "lot", "log", "cog"};
        int expect = 4;
        int result = solution(begin, target, words);
        test.test(result, expect);

        begin = "hit";
        target = "cog";
        words = new String[]{"hot", "dot", "dog", "lot", "log"};
        expect = 0;
        result = solution(begin, target, words);
        test.test(result, expect);
    }
    private class Level{
        int level;
        String current;

        public Level(int level, String current){
            this.level = level;
            this.current = current;
        }
    }
    public int solution(String begin, String target, String[] words) {
        HashSet<String> wordsSet = convertArrayToSet(words);
        int length = begin.length();
        ArrayDeque<Level> queue = new ArrayDeque<>();
        queue.add(new Level(0, begin));

        while(!queue.isEmpty()){
            Level cur = queue.pop();
            int level = cur.level;
            String current = cur.current;

            if(current.equals(target)){
                return level;
            }

            ArrayList<String> wordList = getConvertibleWords(current, wordsSet, length);
            for(String word : wordList){
                queue.add(new Level(level+1, word));
                wordsSet.remove(word);
            }
        }

        return 0;
    }

    private HashSet<String> convertArrayToSet(String[] words) {
        HashSet<String> set = new HashSet<>(Arrays.asList(words));
        return set;
    }

    private ArrayList<String> getConvertibleWords(String begin, HashSet<String> words, int length){
        ArrayList<String> list = new ArrayList<>();
        for(String word : words){
            if(isConvertible(begin, word, length)){
                list.add(word);
            }
        }
        return list;
    }
    private boolean isConvertible(String a, String b, int length){
        char[] word1 = a.toCharArray();
        char[] word2 = b.toCharArray();
        boolean check = false;

        for(int i = 0; i < length; i++){
            if(word1[i] != word2[i]){
                if(check){
                    return false;
                }
                check = true;
            }
        }

        return true;
    }
}
