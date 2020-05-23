package Programmers;


import Test.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BriansConcern{
    public static void main(String[] args) {
        new BriansConcern().test();
    }
    public void test(){
        Test test = new Test();

        String sentence = "HaEaLaLaObWORLDb";
        String expect = "HELLO WORLD";
        String result = solution(sentence);
        test.test(result, expect).printResult();

        sentence = "aHbEbLbLbOacWdOdRdLdDc";
        expect = "HELLO WORLD";
        result = solution(sentence);
        test.test(result, expect).printResult();

        sentence = "HELLOWORLD";
        expect = "HELLOWORLD";
        result = solution(sentence);
        test.test(result, expect).printResult();

        sentence = "SpIpGpOpNpGJqOqA";
        expect = "SIGONG JOA";
        result = solution(sentence);
        test.test(result, expect).printResult();

        sentence = "AxAxAxAoBoBoB";
        expect = "invalid";
        result = solution(sentence);
        test.test(result, expect).printResult();

        sentence = "oIoaAbMacAcdBEEd";
        expect = "I AM A BEE";
        result = solution(sentence);
        test.test(result, expect).printResult();

        sentence = "aWOWabIbAcMdYeEeAeHd";
        expect = "WOW I AM YEAH";
        result = solution(sentence);
        test.test(result, expect).printResult();

        sentence = "TxTxTxbAb";
        expect = "invalid";
        result = solution(sentence);
        test.test(result, expect).printResult();

        sentence = "bTxTxTaTxTbkABaCDk";
        expect = "invalid";
        result = solution(sentence);
        test.test(result, expect).printResult();

        sentence = "abHELLObaWORLD";
        expect = "invalid";
        result = solution(sentence);
        test.test(result, expect).printResult();

        sentence = "aHELLOWORLDa";
        expect = "HELLOWORLD";
        result = solution(sentence);
        test.test(result, expect).printResult();

        sentence = "HaEaLaLaOWaOaRaLaD";
        expect = "invalid";
        result = solution(sentence);
        test.test(result, expect).printResult();

        sentence = "aBcAadDeEdvAvlElmEEEEm";
        expect = "BA DE A E EEEE";
        result = solution(sentence);
        test.test(result, expect).printResult();

        sentence = "aBcAadDeEdvAvlElmEEEEm";
        expect = "BA DE A E EEEE";
        result = solution(sentence);
        test.test(result, expect).printResult();

        sentence = "aIabAcMbdAdeBOYeYfOfUzAgRgEzhAhGiIiRiL";
        expect = "I AM A BOY YOU ARE A GIRL";
        result = solution(sentence);
        test.test(result, expect).printResult();

        sentence = "HELLOfWORLDf";
        expect = "HELLO WORLD";
        result = solution(sentence);
        test.test(result, expect).printResult();

        sentence = "aHbEbLbLbOaWORLD";
        expect = "HELLO WORLD";
        result = solution(sentence);
        test.test(result, expect).printResult();

        sentence = "HELLOAaBWORLD";
        expect = "HELLO AB WORLD";
        result = solution(sentence);
        test.test(result, expect).printResult();

        sentence = "aHbIbHbIbHaIc";
        expect = "invalid";
        result = solution(sentence);
        test.test(result, expect).printResult();

        sentence = "aHbIbHbIbHaIcb";
        expect = "invalid";
        result = solution(sentence);
        test.test(result, expect).printResult();

        sentence = "aHAbLbOa";
        expect = "invalid";
        result = solution(sentence);
        test.test(result, expect).printResult();
    }

    public final String PARSED_WORD = "parsed word";
    public final String REMAINED = "remained sentence";
    public final String INVALID = "invalid";

    public String solution(String sentence) {
        StringBuilder answer = new StringBuilder();

        if(!containsSpecialCharacter(sentence)){
            return sentence;
        }
        if(sentence == null || sentence.length() == 0 || containsBlank(sentence)){
            return INVALID;
        }

        HashSet<Character> check = new HashSet<>();
        while(sentence != null && sentence.length() > 0){
            HashMap<String, String> parsed = parseWord(sentence, check);
            String word = parsed.get(PARSED_WORD);
            sentence = parsed.get(REMAINED);
            if(word == null){
                return INVALID;
            }else{
                answer.append(word);
                answer.append(" ");
            }
        }

        return answer.toString().trim();
    }


    private boolean containsSpecialCharacter(String word) {
        if(word == null){
            return false;
        }
        Pattern p = Pattern.compile(".*[a-z].*");
        Matcher m = p.matcher(word);
        return m.find();
    }

    private boolean containsBlank(String s) {
        Pattern p = Pattern.compile("\\s");
        Matcher m = p.matcher(s);
        return m.find();
    }

    private HashMap<String, String> parseWord(String sentence, HashSet<Character> check) {
        HashMap<String, String> result = new HashMap<>();

        int index = getFirstLowerCaseIndex(sentence);
        if(index < 0){
            result.put(PARSED_WORD, sentence);
            result.put(REMAINED, null);
        }else{
            if(check.contains(sentence.charAt(index))){
                result.put(PARSED_WORD, null);
                result.put(REMAINED, null);
                return result;
            }

            if(index == 0){
                // 1 & 2 가능, 2 가
                int nextIndex = getNextIndex(sentence, index);
                if(isLowerCase(sentence.charAt(index+1)) || nextIndex < 0){
                    result.put(PARSED_WORD, null);
                    result.put(REMAINED, null);
                }else{
                    check.add(sentence.charAt(0));
                    if(nextIndex - index == 2){
                        result.put(PARSED_WORD, ""+sentence.charAt(1));
                        result.put(REMAINED, sentence.substring(3));
                    }else{
                        String head = sentence.substring(1, nextIndex);
                        head = refineHeadAfter2ndRule(head, check);
                        String tail = sentence.substring(nextIndex+1);
                        result.put(PARSED_WORD, head);
                        result.put(REMAINED, tail);
                    }
                }
            }else{
                // 갯수, 위치에 따라 다름
                int nextIndex = getNextIndex(sentence, index);
                if(index == 1){

                    if(nextIndex < 0){
                        check.add(sentence.charAt(1));
                        try{
                            if(isLowerCase(sentence.charAt(0)) || isLowerCase(sentence.charAt(2))){
                                result.put(PARSED_WORD, null);
                                result.put(REMAINED, null);
                            }else{
                                String parsed = "" + sentence.charAt(0) + sentence.charAt(2);
                                String remained = sentence.substring(3);
                                result.put(PARSED_WORD, parsed);
                                result.put(REMAINED, remained);
                            }
                        }catch(StringIndexOutOfBoundsException e){
                            result.put(PARSED_WORD, null);
                            result.put(REMAINED, null);
                        }
                    }else{
                        if(nextIndex - index == 2){
                            check.add(sentence.charAt(1));
                            refineWith1stRule(result, sentence);
                        }else{
                            result.put(PARSED_WORD, ""+sentence.charAt(0));
                            result.put(REMAINED, sentence.substring(1));
                        }
                    }
                }else{
                    if(nextIndex < 0){
                        String head = sentence.substring(0, index-1);
                        String tail = sentence.substring(index-1);
                        result.put(PARSED_WORD, head);
                        result.put(REMAINED, tail);
                    }else{
                        String head = sentence.substring(0, index);
                        String tail = sentence.substring(index);
                        result.put(PARSED_WORD, head);
                        result.put(REMAINED, tail);
                    }
                }
            }
        }

        return result;
    }

    private void refineWith1stRule(HashMap<String, String> result, String sentence) {
        char target = sentence.charAt(1);
        int index = 0;
        String parsed = "";
        int length = sentence.length();
        while(index < length){
            char cur = sentence.charAt(index);
            if(index % 2 == 0){
                if(isLowerCase(cur)){
                    result.put(PARSED_WORD, null);
                    result.put(REMAINED, null);
                    return;
                }else{
                    parsed += cur;
                }
            }else{
                if(cur != target){
                    String remained = sentence.substring(index);
                    result.put(PARSED_WORD, parsed);
                    result.put(REMAINED, remained);
                    return;
                }
            }
            index++;
        }
        if(length % 2 == 0){
            result.put(PARSED_WORD, null);
            result.put(REMAINED, null);
        }else{
            result.put(PARSED_WORD, parsed);
            result.put(REMAINED, null);
        }
    }

    private String refineHeadAfter2ndRule(String head, HashSet<Character> check) {
        if(isLowerCase(head.charAt(1))){
            char target = head.charAt(1);
            int size = head.length();
            if(check.contains(target) || size % 2 == 0){
                return null;
            }
            check.add(target);
            String result = "";
            for(int i = 0; i < size; i++){
                char cur = head.charAt(i);
                if(i % 2 == 0){
                    if(isLowerCase(cur)){
                        return null;
                    }else{
                        result += cur;
                    }
                }else{
                    if(cur != target){
                        return null;
                    }
                }
            }
            return result;
        }else{
            if(containsSpecialCharacter(head)){
                return null;
            }else{
                return head;
            }
        }
    }

    private int getNextIndex(String sentence, int index) {
        char target = sentence.charAt(index);
        int length = sentence.length();
        while(++index < length){
            if(sentence.charAt(index) == target){
                return index;
            }
        }
        return -1;
    }

    private int getFirstLowerCaseIndex(String str) {
        char[] arr = str.toCharArray();
        int index = 0;

        for(char c : arr){
            if(isLowerCase(c)){
                return index;
            }
            index++;
        }

        return -1;
    }

    private boolean isLowerCase(char c) {
        return (c >= 'a' && c <= 'z');
    }

//    private HashMap<String, String> parseWord(String sentence, HashSet<String> check) {
//        HashMap<String, String> parsed = parsed1stRule(sentence, check);
//
//        if(parsed != null){
//            return parsed;
//        }else{
//            parsed = parsed2ndRule(sentence, check);
//            if(parsed == null){
//                if(!isSpecialCharacter(sentence.charAt(0)) && !isSpecialCharacter(sentence.charAt(1))){
//                    parsed.put(PARSED_WORD, sentence.substring(0, 1));
//                    parsed.put(REMAINED, sentence.substring(1));
//                    return parsed;
//                }else{
//                    return setParsedWordAndRemainedSentenceToNull();
//                }
//            }else{
//                return parsed;
//            }
//        }
//    }
//
//    private HashMap<String, String> parsed2ndRule(String str, HashSet<String> check) {
//        if(isSpecialCharacter(str, 0)){
//            return null;
//        }
//        if(isSpecialCharacter(str, 1)){
//            HashMap<String, String> parsed = new HashMap<>();
//            String special = str.substring(1, 2);
//            if(check.contains(special)){
//                return null;
//            }else{
//                check.add(special);
//            }
//
//            char[] chars = str.toCharArray();
//            StringBuilder word = new StringBuilder().append(chars[0]);
//
//            int index = 2;
//            int length = str.length();
//            while(index < length){
//                char cur = chars[index];
//                if(index % 2 == 0){
//                    if(isSpecialCharacter(cur)){
//                        return null;
//                    }else{
//                        word.append(cur);
//                    }
//                }else{
//                    if(isSpecialCharacter(cur)){
//                        if(!special.equals(""+cur)){
//                            break;
//                        }
//                    }else{
//                        break;
//                    }
//                }
//                index++;
//            }
//
//            String remained = str.substring(index);
//            parsed.put(PARSED_WORD, word.toString());
//            parsed.put(REMAINED, remained.length() == 0 ? null : remained);
//            return parsed;
//        }else{
//            return null;
//        }
//    }
//
//    private HashMap<String, String> parsed1stRule(String str, HashSet<String> check) {
//        HashMap<String, String> parsed = new HashMap<>();
//
//        if(isSpecialCharacter(str, 0)){
//            String first = str.substring(0, 1);
//
//            if(check.contains(first)){
//                return null;
//            }else{
//                check.add(first);
//            }
//
//            str = str.substring(1);
//
//            int index = getFirstSpecialCharacterIndex(str);
//            if(index < 0){
//                return null;
//            }
//            String second = str.substring(index, index+1);
//            if(first.equals(second)){
//                String word = str.substring(0, index);
//                String remained = str.substring(index+1);
//
//                if(word.length() == 0){
//                    return null;
//                }
//
//                parsed.put(PARSED_WORD, word);
//                parsed.put(REMAINED, remained.length() == 0 ? null : remained);
//                return parsed;
//            }else{
//                parsed = parsed2ndRule(str, check);
//                if(parsed != null){
//                    String remained = parsed.get(REMAINED);
//                    if(remained.substring(0,1).equals(first)){
//                        remained = remained.substring(1);
//                        parsed.put(REMAINED, remained.length() == 0 ? null : remained);
//                    }
//                }
//                return parsed;
//            }
//        }else{
//            return null;
//        }
//    }
//
//    private int getFirstSpecialCharacterIndex(String str) {
//        char[] chars = str.toCharArray();
//        int length = chars.length;
//
//        for(int i = 0; i < length; i++){
//            if(isSpecialCharacter(chars[i])){
//                return i;
//            }
//        }
//        return -1;
//    }
//
//    private boolean isSpecialCharacter(char c) {
//        if(c >= 'a' && c <= 'z'){
//            return true;
//        }
//        return false;
//    }
//
//    public boolean isSpecialCharacter(String s, int index) {
//        s = s.substring(index);
//        return s.matches("^[a-z].*$");
//    }
//
//    private HashMap<String, String> setParsedWordAndRemainedSentenceToNull() {
//        HashMap<String, String> parsed = new HashMap<>();
//        parsed.put(PARSED_WORD, null);
//        parsed.put(REMAINED, null);
//        return parsed;
//    }

}