//https://programmers.co.kr/learn/courses/30/lessons/12902
package Programmers;

import Test.OldTest;

public class Tiling3N{
    public static void main(String[] args) {
        new Tiling3N().test();
    }
    public void test(){
        OldTest test = new OldTest();

        int[] expects = {
                3, 11, 41, 153,  571,
                2131, 7953, 29681, 110771, 413403,
                1542841, 5757961, 21489003, 80198051, 299303201,
                117014746, 168755783, 558008386, 63277747, 695102609
        };
        int index = 0;
        for(int n = 2; n <= 40; n+=2){
            int result = solution(n);
            test.test(result, expects[index++]);
        }

        int n = 5000;
        int expect = 658712818;
        int result = solution(n);
        test.test(result, expect);
    }
    public int solution(int n){
        int[] data = new int[n+1];
        data[0] = 1;
        data[2] = 3;
        int MOD = 1000000007;
        int sum = 0;

        for(int i = 2; i <= n; i += 2){
            long simpleComb = data[i-2];
            simpleComb *= 3;
            simpleComb %= MOD;
            long duplicatedArea = sum;
            duplicatedArea *= 2;
            duplicatedArea %= MOD;
            data[i] = (int) ((simpleComb + duplicatedArea) % MOD);
            sum = (sum + data[i-2]) % MOD;
        }

        return data[n];
    }
}