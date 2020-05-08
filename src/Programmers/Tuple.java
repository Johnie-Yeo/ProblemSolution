package Programmers;

import Test.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Tuple{
    public static void main(String[] args) {
        new Tuple().test();
    }
    public void test(){
        Test test = new Test();

        String s;
        int[] result, expect;

        s = "{{2},{2,1},{2,1,3},{2,1,3,4}}";
        result = solution(s);
        expect = new int[]{2, 1, 3, 4};
        test.test(result, expect).printResult();

        s = "{{1,2,3},{2,1},{1,2,4,3},{2}}";
        result = solution(s);
        expect = new int[]{2, 1, 3, 4};
        test.test(result, expect).printResult();

        s = "{{20,111},{111}}";
        result = solution(s);
        expect = new int[]{111, 20};
        test.test(result, expect).printResult();

        s = "{{123}}";
        result = solution(s);
        expect = new int[]{123};
        test.test(result, expect).printResult();

        s = "{{4,2,3},{3},{2,3,4,1},{2,3}}";
        result = solution(s);
        expect = new int[]{3, 2, 4, 1};
        test.test(result, expect).printResult();
    }
    public int[] solution(String s) {
        int[][] elements = parseSet(s);
        HashSet<Integer> set = new HashSet<>();
        ArrayList<Integer> list = new ArrayList<>();

        for(int[] arr : elements){
            for(int elem : arr){
                if(!set.contains(elem)){
                    list.add(elem);
                    set.add(elem);
                }
            }
        }

        int[] answer = list.stream().mapToInt(e->e).toArray();
        return answer;
    }

    private int[][] parseSet(String s) {
        s = removeBracket(s);
        String[] arr = parseElement(s);
        int length = arr.length;
        for(int i = 0; i < length; i++){
            arr[i] = removeBracket(arr[i]);
        }
        int[][] result = new int[length][];
        for(int i =0; i < length; i++){
            String[] elements = arr[i].split(",");
            int size = elements.length;
            result[size-1] = Arrays.stream(elements).mapToInt(Integer::parseInt).toArray();
        }
        return result;
    }

    private String[] parseElement(String s) {
        ArrayList<String> list = new ArrayList<>();

        char[] arr = s.toCharArray();
        boolean open = false;
        StringBuilder sb = new StringBuilder();
        for(char c : arr){
            if(c == '{'){
                open = true;
            }else if(c == '}'){
                open = false;
            }else if(!open && c == ','){
                list.add(sb.toString());
                sb = new StringBuilder();
                continue;
            }
            sb.append(c);
        }
        list.add(sb.toString());

        return list.stream().toArray(String[]::new);
    }

    private String removeBracket(String s) {
        int length = s.length();
        return s.substring(1, length-1);
    }
}