package Programmers.kakao;

import Test.Test;

import java.util.*;

public class SearchLyrics {
    public static void main(String[] args) {
        new SearchLyrics().test();
    }

    private void test() {
        String[] words = {"frodo", "front", "frost", "frozen", "frame", "kakao"};
        String[] queries = {"fro??", "????o", "fr???", "fro???", "pro?", "?????"};
        int[] expect = {3, 2, 4, 1, 0, 5};
        int[] result = solution(words, queries);

        Test.test(result, expect).printResult();
    }

    final char WILDCARD = '?';

    private class Trie {
        private TrieNode rootNode;

        public Trie() {
            this.rootNode = new TrieNode();
        }

        public void insert(String input) {
            TrieNode trieNode = this.rootNode;

            this.rootNode.addCount();

            for(char word : input.toCharArray()) {
                trieNode = trieNode.getChildNodes().computeIfAbsent(word, c -> new TrieNode());
                trieNode.addCount();
            }

            trieNode.setLastChar(true);
        }

        public int countMatch(String query) {
            int count = this.dfsTrie(this.rootNode, query, 0);

            return count;
        }

        private int dfsTrie(TrieNode currentNode, String query, int index) {
            char word = query.charAt(index);

            TrieNode nextNode = currentNode.getChildNodes().get(word);

            if(nextNode != null) {
                return dfsTrie(nextNode, query, index+1);
            } else if(word == WILDCARD) {
                int result = currentNode.getCount();
                return result;
            }
            return 0;
        }
    }

    private class TrieNode {
        private Map<Character, TrieNode> childNodes;
        int count;
        private boolean isLastChar;

        public TrieNode() {
            this.childNodes = new HashMap<>();
            this.count = 0;
        }

        public Map<Character, TrieNode> getChildNodes() {
            return this.childNodes;
        }

        public boolean isLastChar() {
            return this.isLastChar;
        }

        public void setLastChar(boolean isLastChar) {
            this.isLastChar = isLastChar;
        }

        public void addCount() {
            this.count++;
        }

        public int getCount() {
            return this.count;
        }
    }
    public int[] solution(String[] words, String[] queries) {
        String[] reversedWords = getReversedStrings(words);
        Map<Integer, Trie> forwardTries = buildTrie(words);
        Map<Integer, Trie> reverseTries = buildTrie(reversedWords);


        int querySize = queries.length;
        int[] result = new int[querySize];

        for(int i = 0; i < querySize; i++) {
            String query = queries[i];
            int queryLength = query.length();

            Trie trie;

            if(query.charAt(0) == WILDCARD) {
                trie = reverseTries.get(queryLength);
                query = getReversedString(query);
            } else {
                trie = forwardTries.get(queryLength);
            }

            if(trie == null) {
                result[i] = 0;
            } else {
                result[i] = trie.countMatch(query);
            }
        }

        return result;
    }

    private String[] getReversedStrings(String[] words) {
        return Arrays.stream(words)
                .map(word -> getReversedString(word))
                .toArray(String[]::new);
    }

    public String getReversedString(String word) {
        StringBuilder sb = new StringBuilder(word);
        return sb.reverse().toString();
    }

    private  HashMap<Integer, Trie> buildTrie(String[] words) {
        HashMap<Integer, Trie> tries = new HashMap<>();

        for(String word : words) {
            int length = word.length();
            Trie trie = tries.computeIfAbsent(length, c -> new Trie());
            trie.insert(word);
        }

        return tries;
    }

}
