package BOJ;

import Test.OldTest;

import java.util.Scanner;

public class RemoteControl{
    public static void main(String[] args) {
//        new Main().solve();
        new RemoteControl().test();
    }
    public void test(){
        OldTest<Integer> test = new OldTest<Integer>();
        int N, M;
        boolean[] buttons;
        int[] brokes;
        int result, expect;

        N = 5457;
        M = 3;
        buttons = new boolean[10];
        brokes = new int[]{6, 7, 8};
        for(int broke : brokes){
            buttons[broke] = true;
        }
        result = getMinimumControl(N, M, buttons);
        expect = 6;
        test.test(result, expect).printResult();

        N = 100;
        M = 5;
        buttons = new boolean[10];
        brokes = new int[]{0, 1, 2, 3, 4};
        for(int broke : brokes){
            buttons[broke] = true;
        }
        result = getMinimumControl(N, M, buttons);
        expect = 0;
        test.test(result, expect).printResult();

        N = 500000;
        M = 8;
        buttons = new boolean[10];
        brokes = new int[]{0, 2, 3, 4, 6, 7, 8, 9};
        for(int broke : brokes){
            buttons[broke] = true;
        }
        result = getMinimumControl(N, M, buttons);
        expect = 11117;
        test.test(result, expect).printResult();

        N = 500000;
        M = 10;
        buttons = new boolean[10];
        brokes = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        for(int broke : brokes){
            buttons[broke] = true;
        }
        result = getMinimumControl(N, M, buttons);
        expect = 499900;
        test.test(result, expect).printResult();

        N = 500000;
        M = 0;
        buttons = new boolean[10];
        brokes = new int[]{};
        for(int broke : brokes){
            buttons[broke] = true;
        }
        result = getMinimumControl(N, M, buttons);
        expect = 6;
        test.test(result, expect).printResult();

        N = 0;
        M = 9;
        buttons = new boolean[10];
        brokes = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 9};
        for(int broke : brokes){
            buttons[broke] = true;
        }
        result = getMinimumControl(N, M, buttons);
        expect = 9;
        test.test(result, expect).printResult();

        N = 0;
        M = 9;
        buttons = new boolean[10];
        brokes = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        for(int broke : brokes){
            buttons[broke] = true;
        }
        result = getMinimumControl(N, M, buttons);
        expect = 1;
        test.test(result, expect).printResult();

        N = 500000;
        M = 6;
        buttons = new boolean[10];
        brokes = new int[]{0, 1, 2, 3, 4, 5};
        for(int broke : brokes){
            buttons[broke] = true;
        }
        result = getMinimumControl(N, M, buttons);
        expect = 166672;
        test.test(result, expect).printResult();

        N = 1555;
        M = 8;
        buttons = new boolean[10];
        brokes = new int[]{0,1,3,4,5,6,7,9};
        for(int broke : brokes){
            buttons[broke] = true;
        }
        result = getMinimumControl(N, M, buttons);
        expect = 670;
        test.test(result, expect).printResult();
    }
    public void solve(){
        Scanner kb = new Scanner(System.in);

        int N = kb.nextInt();
        int M = kb.nextInt();
        boolean[] buttons = new boolean[10];
        for(int i = 0; i < M; i++){
            int button = kb.nextInt();
            buttons[button] = true;
        }
        int result = getMinimumControl(N, M, buttons);
        System.out.println(result);

        kb.close();
    }

    private int getMinimumControl(int target, int m, boolean[] brokeButtons) {
        int cur = 100;
        int click = 0;
        if(cur == target){
            return 0;
        }
        int distance = Math.abs(cur - target);
        if(m == 10){
            return distance;
        }else if(isMovable(target, brokeButtons)){
            return Math.min(distance, getClick(target));
        }

        int move = moveToClosestChannel(target, brokeButtons);
        click += getClick(move);
        int diff = Math.abs(target - move);
        click += diff;

        return Math.min(distance, click);
    }

    private int moveToClosestChannel(int target, boolean[] brokeButtons) {
        int upperClose = getUpperClose(target+1, brokeButtons);
        int lowerClose = getLowerClose(target-1, brokeButtons);
        int upperDiff = upperClose - target;
        int lowerDiff = target - lowerClose;
        if(upperDiff == lowerDiff){
            if(lowerClose > 0){
                return lowerClose;
            }else{
                return upperClose;
            }
        }else if(lowerClose >= 0 && upperDiff > lowerDiff){
            return lowerClose;
        }else{
            return upperClose;
        }
    }

    private int getLowerClose(int channel, boolean[] brokeButtons) {
        while(!isMovable(channel, brokeButtons)){
            channel--;
        }
        return channel;
    }

    private int getUpperClose(int channel, boolean[] brokeButtons) {
        while(!isMovable(channel, brokeButtons)){
            if(channel > 1000000){
                return Integer.MAX_VALUE;
            }
            channel++;
        }
        return channel;
    }

    private boolean isMovable(int channel, boolean[] brokeButtons) {
        if(channel == 0 && brokeButtons[channel]){
            return false;
        }
        while(channel > 0){
            int digit = channel % 10;
            channel /= 10;
            if(brokeButtons[digit]){
                return false;
            }
        }
        return true;
    }

    private int getClick(int channel) {
        if(channel == 0){
            return 1;
        }
        int click = 0;
        while(channel > 0){
            channel /= 10;
            click++;
        }
        return click;
    }
}