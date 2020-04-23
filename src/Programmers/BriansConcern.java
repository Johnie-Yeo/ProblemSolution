package Programmers;

import Test.OldTest;

import java.util.HashMap;
import java.util.HashSet;

public class BriansConcern{
    public static void main(String[] args) {
        new BriansConcern().test();
    }
    public void test(){
        OldTest test = new OldTest();

        String sentence = "HaEaLaLaObWORLDb";
        String expect = "HELLO WORLD";
        String result = solution(sentence);
        test.test(result, expect);

        sentence = "aHbEbLbLbOacWdOdRdLdDc";
        expect = "HELLO WORLD";
        result = solution(sentence);
        test.test(result, expect);

        sentence = "HELLOWORLD";
        expect = "HELLOWORLD";
        result = solution(sentence);
        test.test(result, expect);

        sentence = "SpIpGpOpNpGJqOqA";
        expect = "SIGONG JOA";
        result = solution(sentence);
        test.test(result, expect);

        sentence = "AxAxAxAoBoBoB";
        expect = "invalid";
        result = solution(sentence);
        test.test(result, expect);

        sentence = "oIoaAbMacAcdBEEd";
        expect = "I AM A BEE";
        result = solution(sentence);
        test.test(result, expect);

        sentence = "aWOWabIbAcMdYeEeAeHd";
        expect = "WOW I AM YEAH";
        result = solution(sentence);
        test.test(result, expect);

        sentence = "TxTxTxbAb";
        expect = "invalid";
        result = solution(sentence);
        test.test(result, expect);

        sentence = "bTxTxTaTxTbkABaCDk";
        expect = "invalid";
        result = solution(sentence);
        test.test(result, expect);

        sentence = "abHELLObaWORLD";
        expect = "invalid";
        result = solution(sentence);
        test.test(result, expect);

        sentence = "aHELLOWORLDa";
        expect = "HELLOWORLD";
        result = solution(sentence);
        test.test(result, expect);

        sentence = "HaEaLaLaOWaOaRaLaD";
        expect = "invalid";
        result = solution(sentence);
        test.test(result, expect);

        sentence = "aBcAadDeEdvAvlElmEEEEm";
        expect = "BA DE A E EEEE";
        result = solution(sentence);
        test.test(result, expect);

        sentence = "aBcAadDeEdvAvlElmEEEEm";
        expect = "BA DE A E EEEE";
        result = solution(sentence);
        test.test(result, expect);

        sentence = "aIabAcMbdAdeBOYeYfOfUzAgRgEzhAhGiIiRiL";
        expect = "I AM A BOY YOU ARE A GIRL";
        result = solution(sentence);
        test.test(result, expect);
    }

    public final String PARSED_WORD = "parsed word";
    public final String REMAINED = "remained sentence";
    public final String INVALID = "invalid";

    public String solution(String sentence) {
        StringBuilder answer = new StringBuilder();

        if(!containsSpecialCharacter(sentence)){
            return sentence;
        }
        if(sentence == null || sentence.length() == 0 ||
           containsBlank(sentence)){
            return INVALID;
        }

        HashSet<String> check = new HashSet<>();
        while(sentence != null){
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

    private boolean containsBlank(String s) {
        return s.matches("\\s");
    }

    private HashMap<String, String> parseWord(String sentence, HashSet<String> check) {
        HashMap<String, String> parsed = new HashMap<>();

        parsed = parsed1stRule(sentence, check);
        
        if(parsed != null){
            return parsed;
        }else{
            parsed = parsed2ndRule(sentence, check);
            if(parsed == null){
                if(!isSpecialCharacter(sentence, 0) && 
                   !isSpecialCharacter(sentence, 1)){
                    parsed.put(PARSED_WORD, sentence.substring(0, 1));
                    parsed.put(REMAINED, sentence.substring(1));
                    return parsed;
                }else{
                    return setParsedWordAndRemainedSentenceToNull();
                }
            }else{
                return parsed;
            }
        }
    }

    private HashMap<String, String> parsed2ndRule(String str, HashSet<String> check) {
        if(isSpecialCharacter(str, 0)){
            return null;
        }
        if(isSpecialCharacter(str, 1)){
            HashMap<String, String> parsed = new HashMap<>();
            String special = str.substring(1, 2);
            if(check.contains(special)){
                return null;
            }else{
                check.add(special);
            }

            char[] chars = str.toCharArray();
            StringBuilder word = new StringBuilder().append(chars[0]);

            int index = 2;
            int length = str.length();
            while(index < length){
                char cur = chars[index];
                if(index % 2 == 0){
                    if(isSpecialCharacter(cur)){
                       return null;
                    }else{
                        word.append(cur);
                    }
                }else{
                    if(isSpecialCharacter(cur)){
                        if(!special.equals(""+cur)){
                            break;
                        }
                    }else{
                        break;
                    }
                }
                index++;
            }

            String remained = str.substring(index);
            parsed.put(PARSED_WORD, word.toString());
            parsed.put(REMAINED, remained.length() == 0 ? null : remained);
            return parsed;
        }else{
            return null;
        }
    }

    private boolean containsSpecialCharacter(String word) {
        return word.matches(".*[a-z].*");
    }

    private HashMap<String, String> parsed1stRule(String str, HashSet<String> check) {
        HashMap<String, String> parsed = new HashMap<>();

        if(isSpecialCharacter(str, 0)){
            String first = str.substring(0, 1);

            if(check.contains(first)){
                return null;
            }else{
                check.add(first);
            }

            str = str.substring(1);

            int index = getFirstSpecialCharacterIndex(str);
            if(index < 0){
                return null;
            }
            String second = str.substring(index, index+1);
            if(first.equals(second)){
                String word = str.substring(0, index);
                String remained = str.substring(index+1);

                if(word.length() == 0){
                    return null;
                }

                parsed.put(PARSED_WORD, word);
                parsed.put(REMAINED, remained.length() == 0 ? null : remained);
                return parsed;
            }else{
                parsed = parsed2ndRule(str, check);
                if(parsed != null){
                    String remained = parsed.get(REMAINED);
                    if(remained.substring(0,1).equals(first)){
                        remained = remained.substring(1);
                        parsed.put(REMAINED, remained.length() == 0 ? null : remained);
                    }
                }
                return parsed;
            }
        }else{
            return null;
        }
    }

    private int getFirstSpecialCharacterIndex(String str) {
        char[] chars = str.toCharArray();
        int length = chars.length;

        for(int i = 0; i < length; i++){
            if(isSpecialCharacter(chars[i])){
                return i;
            }
        }
        return -1;
    }

    private boolean isSpecialCharacter(char c) {
        if(c >= 'a' && c <= 'z'){
            return true;
        }
        return false;
    }

    public boolean isSpecialCharacter(String s, int index) {
        s = s.substring(index);
        return s.matches("^[a-z].*$");
    }

    private HashMap<String, String> setParsedWordAndRemainedSentenceToNull() {
        HashMap<String, String> parsed = new HashMap<>();
        parsed.put(PARSED_WORD, null);
        parsed.put(REMAINED, null);
        return parsed;
    }
}