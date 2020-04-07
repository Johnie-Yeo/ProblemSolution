package Programmers;

import Test.Test;

import java.util.ArrayList;

public class FoldPaper{
    public static void main(String[] args) {
        new FoldPaper().test();
    }
    public void test(){
        Test test = new Test();

        int n = 1;
        int[] result = solution(n);
        int[] expect = new int[]{0};
        test.test(result, expect);

        n = 2;
        result = solution(n);
        expect = new int[]{0,0,1};
        test.test(result, expect);

        n = 3;
        result = solution(n);
        expect = new int[]{0,0,1,0,0,1,1};
        test.test(result, expect);

        n = 4;
        result = solution(n);
        expect = new int[]{0,0,1,0,0,1,1,0,0,0,1,1,0,1,1};
        test.test(result, expect);

        n = 5;
        result = solution(n);
        expect = new int[]{0,0,1,0,0,1,1,0,0,0,1,1,0,1,1,0,0,0,1,0,0,1,1,1,0,0,1,1,0,1,1};
        test.test(result, expect);
    }
    public int[] solution(int n){
        ArrayList<Integer> result = new ArrayList<>();
        result.add(0);

        for(int i = 1; i < n; i++){
            ArrayList<Integer> reversed = reverseAndConvert1With0(result);
            result.add(0);
            result.addAll(reversed);
        }

        int[] answer = result.stream().mapToInt(element -> element).toArray();
        return answer;
    }

    public ArrayList<Integer> reverseAndConvert1With0(ArrayList<Integer> result) {
        ArrayList<Integer> list = new ArrayList<>();
        int size = result.size();

        for(int i = size -1; i >= 0; i--){
            int number = result.get(i);
            list.add(convert1With0(number));
        }

        return list;
    }
    public int convert1With0(int num){
        if(num == 1){
            return 0;
        }else if(num == 0){
            return 1;
        }else{
            return -1;
        }
    }
}