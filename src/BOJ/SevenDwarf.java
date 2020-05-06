package BOJ;

import Test.OldestTest;

import java.util.Arrays;
import java.util.Scanner;

public class SevenDwarf{
    private final int SUM = 100;
    public static void main(String[] args) {
//        new Main().solve();
        new SevenDwarf().test();
    }
    public void test(){
        OldestTest test = new OldestTest();

        int[] dwarfs = {20, 7, 23, 19, 10, 15, 25, 8, 13};
        int[] result = getSevenDwarf(dwarfs);
        int[] expect = {7, 8, 10, 13, 19, 20, 23};
        test.test(result, expect);
    }

    public void solve(){
        Scanner kb = new Scanner(System.in);

        int[] dwarfs = new int[9];
        for(int i = 0; i < 9; i++){
            dwarfs[i] = kb.nextInt();
        }
        int[] result = getSevenDwarf(dwarfs);
        for(int dwarf : result){
            System.out.println(dwarf);
        }
        kb.close();
    }

    private int[] getSevenDwarf(int[] dwarfs) {
        for(int i = 0; i < 8; i++){
            for(int j = i+1; j < 9; j++){
                int[] comb = remove(i, j, dwarfs);
                if(getSum(comb) == SUM){
                    Arrays.sort(comb);
                    return comb;
                }
            }
        }
        return null;
    }

    private int getSum(int[] arr) {
        int sum = 0;
        for(int element : arr){
            sum += element;
        }
        return sum;
    }

    private int[] remove(int x, int y, int[] dwarfs) {
        int[] arr = new int[7];
        int index = 0;
        for(int i = 0; i < 9; i++){
            if(i != x && i != y){
                arr[index++] = dwarfs[i];
            }
        }
        return arr;
    }
}