// https://programmers.co.kr/learn/courses/30/lessons/43162

package Programmers;

import Test.OldTest;

public class Network {
    public static void main(String[] args) {
        new Network().test();
    }
    private void test(){
        OldTest test = new OldTest();

        int n = 3;
        int[][] computers = {
                {1, 1, 0},
                {1, 1, 0},
                {0, 0, 1}
        };
        int expect = 2;
        int result = solution(n, computers);
        test.test(result, expect);

        n = 3;
        computers = new int[][]{
                {1, 1, 0},
                {1, 1, 1},
                {0, 1, 1}
        };
        expect = 1;
        result = solution(n, computers);
        test.test(result, expect);
    }

    private final int CONNECTED = 1;
    private final int DISCONNECTED = 0;
    public int solution(int n, int[][] computers) {
        int network = 0;

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(computers[i][j] == CONNECTED){
                    removeConnection(n, computers, i);
                    network++;
                }
            }
        }

        return network;
    }

    private void removeConnection(int n, int[][] computers, int index) {
        if(computers[index][index] == DISCONNECTED){
            return;
        }
        for(int i = 0; i < n; i++){
            if(computers[index][i] == CONNECTED){
                computers[index][i] = DISCONNECTED;
                removeConnection(n, computers, i);
            }
        }
    }
}
