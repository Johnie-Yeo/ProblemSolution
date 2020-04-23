//https://programmers.co.kr/learn/courses/30/lessons/42842
package Programmers;

import Test.OldTest;

public class Carpet{
    public static void main(String[] args) {
        new Carpet().test();
    }
    public void test(){
        OldTest test = new OldTest();

        int brown = 10;
        int red = 2;
        int[] expect = {4,3};
        int[] result = solution(brown, red);
        test.test(result, expect);

        brown = 8;
        red = 1;
        expect = new int[]{3, 3};
        result = solution(brown, red);
        test.test(result, expect);

        brown = 24;
        red = 24;
        expect = new int[]{8, 6};
        result = solution(brown, red);
        test.test(result, expect);
    }
    public int[] solution(int brown, int red) {
        int maxLength = (brown - 2) / 2;

        int length = getLength(brown, red, maxLength);
        int height = getHeight(brown, length);
        int[] answer = {length, height};
        return answer;
    }

    private int getLength(int brown, int red, int maxLength) {
        for(int length = maxLength; length >= 0; length--){
            int height = getHeight(brown, length);
            int innerLength = length - 2;
            int innerHeight = height - 2;
            int area = innerHeight * innerLength;
            if(area == red){
                return length;
            }
        }
        return -1;
    }

    private int getHeight(int brown, int length){
        return (brown - length*2)/2 + 2;
    }
}