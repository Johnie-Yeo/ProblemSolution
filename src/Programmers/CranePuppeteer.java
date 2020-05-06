package Programmers;

import Test.OldestTest;

import java.util.Stack;

// https://programmers.co.kr/learn/courses/30/lessons/64061
public class CranePuppeteer {
    public static void main(String[] args) {
        new CranePuppeteer().test();
    }
    public void test(){
        OldestTest test = new OldestTest();
        int[][] board = {
                {0,0,0,0,0},
                {0,0,1,0,3},
                {0,2,5,0,1},
                {4,2,4,4,2},
                {3,5,1,3,1}
        };
        int[] moves = {1,5,3,5,1,2,1,4};
        int expect = 4;
        int result = solution(board, moves);
        test.test(result, expect);

        board = new int[][]{
                {0,0,0,0,0},
                {0,0,0,0,0},
                {0,0,0,0,0},
                {0,0,0,0,0},
                {0,0,0,0,0}
        };
        moves = new int[]{1,5,3,5,1,2,1,4};
        expect = 0;
        result = solution(board, moves);
        test.test(result, expect);
    }
    public int solution(int[][] board, int[] moves) {
        Stack<Integer>[] stack = buildStackArray(board);
        int count = countPoppedDolls(stack, moves);

        return count;
    }

    private Stack<Integer>[] buildStackArray(int[][] board) {
        int length = board.length;
        Stack<Integer>[] stack = new Stack[length];
        for(int i = 0; i < length; i++){
            stack[i] = new Stack<>();
        }
        for(int i = length-1; i >= 0; i--){
            for(int j = 0; j < length; j++){
                int doll = board[i][j];
                if(doll > 0 && doll <= 100){
                    stack[j].push(doll);
                }
            }
        }

        return stack;
    }

    private int countPoppedDolls(Stack<Integer>[] stack, int[] moves) {
        Stack<Integer> basket = new Stack<>();
        int count = 0;

        for(int move : moves){
            Stack<Integer> currentLane = stack[move-1];
            if(!currentLane.isEmpty()){
                int currentDoll = currentLane.pop();
                if(isSameDoll(basket, currentDoll)){
                    basket.pop();
                    count += 2;
                }else{
                    basket.push(currentDoll);
                }
            }
        }

        return count;
    }

    private boolean isSameDoll(Stack<Integer> basket, int currentDoll) {
        if(!basket.isEmpty() && basket.peek() == currentDoll){
            return true;
        }
        return false;
    }
}