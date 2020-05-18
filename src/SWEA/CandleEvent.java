package SWEA;

import Test.Test;

import java.math.BigInteger;
import java.util.Scanner;

public class CandleEvent {
    public static void main(String[] args) {
//        new Solution().solve();
        new CandleEvent().test();
    }
    public void test(){
        Test test = new Test();

        long numberOfCandles;
        long result, expect;

        numberOfCandles = 1;
        result = getNumberOfLayers(numberOfCandles);
        expect = 1;
        test.test(result, expect).printResult();

        numberOfCandles = 3;
        result = getNumberOfLayers(numberOfCandles);
        expect = 2;
        test.test(result, expect).printResult();

        numberOfCandles = 6;
        result = getNumberOfLayers(numberOfCandles);
        expect = 3;
        test.test(result, expect).printResult();

        numberOfCandles = 14;
        result = getNumberOfLayers(numberOfCandles);
        expect = -1;
        test.test(result, expect).printResult();

        numberOfCandles = 762078456028L;
        result = getNumberOfLayers(numberOfCandles);
        expect = 1234567;
        test.test(result, expect).printResult();

        numberOfCandles = 7620789436823655L;
        result = getNumberOfLayers(numberOfCandles);
        expect = 123456789;
        test.test(result, expect).printResult();

        numberOfCandles = 1676491987170L;
        result = getNumberOfLayers(numberOfCandles);
        expect = 1831115;
        test.test(result, expect).printResult();
    }

    public void solve(){
        Scanner kb = new Scanner(System.in);

        int T = kb.nextInt();
        for(int t = 1; t <= T; t++){
            long num = kb.nextLong();
            long result = getNumberOfLayers(num);
            System.out.println("#"+t+" "+result);
        }
        kb.close();
    }

    private long getNumberOfLayers(long target) {
        long end = getEnd(target);
        long start = 0;

        while(start <= end){
            long mid = (start+end)/2;
            long candle = getNumberOfCandle(mid);
            if(candle < 0 || candle > target){
                end = mid - 1;
            }else if(candle < target){
                start = mid + 1;
            }else{
                return mid;
            }
        }
        return -1;
    }

    private long getEnd(long target) {
        if(target <= pow10(2)){
            return target / 2 + 1;
        }else{
            String tmp = Long.toString(target);
            int length = tmp.length();
            tmp = tmp.substring(0, (length+1)/2+1);
            return Long.parseLong(tmp);
        }
    }

    private long pow10(int n){
        return (long)Math.pow(10, n);
    }

    private long getNumberOfCandle(long layer){
        final int MOD = 1000000;

        long head = layer / MOD;
        long tail = layer % MOD + 1;
        long tmp1 = head * layer;
        long tmp2 = tail * layer;
        long tmp3 = tmp1 + tmp2 / MOD;
        long tmp4 = tmp2 % MOD;
        long tmp5 = tmp3 * (MOD/2);
        long tmp6 = tmp4 / 2;
        long result = tmp5 + tmp6;
        return result;
    }
}
