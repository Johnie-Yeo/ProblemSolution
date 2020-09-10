package Programmers.kakao;

import Test.Test;

import java.util.*;

public class Autocomplete {
    public static void main(String[] args) {
        new Autocomplete().test();
    }

    private void test() {
        String[] words = {"go", "gone", "guild"};
        int expect = 7;
        int result = solution(words);
        Test.test(result, expect).printResult();

        words = new String[]{"abc","def","ghi","jklm"};
        expect = 4;
        result = solution(words);
        Test.test(result, expect).printResult();

        words = new String[]{"word","war","warrior","world"};
        expect = 15;
        result = solution(words);
        Test.test(result, expect).printResult();
    }


    private class Trie {
        private TrieNode rootNode;

        public Trie() {
            this.rootNode = new TrieNode();
        }

        public void insert(String word) {
            TrieNode node = this.rootNode;
            node.addCount();

            for(char cur : word.toCharArray()) {
                node = node.getChildNodes().computeIfAbsent(cur, c -> new TrieNode());
                node.addCount();
            }

            node.setLastChar(true);
        }

        public int getMinimumIdentifiableLength(String word) {
            int count = 0;
            TrieNode node = this.rootNode;

            for(char cur : word.toCharArray()) {
                node = node.getChildNodes().get(cur);
                count++;
                if(node.getCount() == 1) {
                    break;
                }
            }

            return count;
        }
    }

    private class TrieNode {
        private Map<Character, TrieNode> children;
        private int count;
        private boolean isLastChar;

        public TrieNode() {
            this.children = new HashMap<>();
            this.count = 0;
            this.isLastChar = false;
        }

        public Map<Character, TrieNode> getChildNodes() {
            return this.children;
        }

        public int getCount() {
            return this.count;
        }

        public void addCount() {
            this.count++;
        }

        public boolean isLastChar() {
            return this.isLastChar;
        }

        public void setLastChar(boolean value) {
            this.isLastChar = value;
        }
    }
    public int solution(String[] words) {
        int count = 0;

        Trie trie = buildTrie(words);
        for(String word : words) {
            int minimumLengthToIdentify = trie.getMinimumIdentifiableLength(word);
            count += minimumLengthToIdentify;
        }

        return count;
    }

    private Trie buildTrie(String[] words) {
        Trie trie = new Trie();

        for(String word : words) {
            trie.insert(word);
        }

        return trie;
    }
}
