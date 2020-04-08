//https://programmers.co.kr/learn/courses/30/lessons/60059
package Programmers;

import Test.Test;

public class LockerAndKey{
    public static void main(String[] args) {
        new LockerAndKey().test();
    }
    public void test(){
        Test test = new Test();

        int[][] key = {
                {0, 0, 0},
                {1, 0, 0},
                {0, 1, 1}
        };
        int[][] lock = {
                {1, 1, 1},
                {1, 1, 0},
                {1, 0, 1}
        };
        boolean expect = true;
        boolean result = solution(key, lock);

        test.test(result, expect);

        key = new int[][]{
                {1, 0, 0},
                {1, 0, 0},
                {0, 1, 1}
        };
        lock = new int[][]{
                {1, 0, 1},
                {1, 1, 0},
                {1, 1, 1}
        };
        expect = true;
        result = solution(key, lock);

        test.test(result, expect);
    }
    public boolean solution(int[][] key, int[][] lock) {
        int keySize = key.length;
        int lockerSize = lock.length;
        int boardSize = keySize * 2 + lockerSize - 2;

        int[][] board = setBoardWithLocker(lock, keySize, lockerSize, boardSize);

        return isOpenable(board, boardSize, key, keySize, lockerSize);
    }

    private boolean isOpenable(int[][] board, int boardSize, int[][] key, int keySize, int lockerSize) {
        for(int i = 0; i <= boardSize - keySize; i++){
            for(int j = 0; j <= boardSize - keySize; j++){
                if(checkEveryDirection(board, key, keySize, lockerSize, i, j)){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkEveryDirection(int[][] board, int[][] key, int keySize, int lockerSize, int x, int y) {
        for(int d = 0; d < 4; d++){
            key = rotateKeyClockwise(key, keySize);
            if(isOpenWithKey(board, key, keySize, lockerSize, x, y)){
                return true;
            }
        }
        return false;
    }

    private boolean isOpenWithKey(int[][] board, int[][] key, int keySize, int lockerSize, int x, int y) {
        board = addKeyToBoard(board, key, keySize, x, y);
        if(isOpen(board, keySize, lockerSize)){
            return true;
        }else{
            board = rollback(board, key, keySize, x, y);
            return false;
        }
    }

    private int[][] rollback(int[][] board, int[][] key, int keySize, int x, int y) {
        for(int i = 0; i < keySize; i++){
            for(int j = 0; j < keySize; j++){
                board[i+x][j+y] -= key[i][j];
            }
        }
        return board;
    }

    private boolean isOpen(int[][] board, int keySize, int lockerSize) {
        int start = keySize - 1;
        for(int i = start; i < start + lockerSize; i++){
            for(int j = start; j < start + lockerSize; j++){
                if(board[i][j] == 0 || board[i][j] > 1){
                    return false;
                }
            }
        }
        return true;
    }

    private int[][] addKeyToBoard(int[][] board, int[][] key, int keySize, int x, int y) {
        for(int i = 0; i < keySize; i++){
            for(int j = 0; j < keySize; j++){
                board[i+x][j+y] += key[i][j];
            }
        }
        return board;
    }

    private int[][] setBoardWithLocker(int[][] lock, int keySize, int lockerSize, int boardSize) {
        int[][] board = new int[boardSize][boardSize];
        int start = keySize - 1;
        for(int i = 0; i < lockerSize; i++){
            for(int j = 0; j < lockerSize; j++){
                board[i+start][j+start] = lock[i][j];
            }
        }
        return board;
    }

    public int[][] rotateKeyClockwise(int[][] key, int keySize){
        int[][] rotated = new int[keySize][keySize];

        for(int i = 0; i < keySize; i++){
            for(int j = 0; j < keySize; j++){
                rotated[i][j] = key[keySize - 1 - j][i];
            }
        }

        return rotated;
    }
}
//rotate example
//
//0 0 0
//1 0 0
//0 1 1
//
// size = 3
//
// 0,0 -> 0,2 | 0,1 -> 1,2 | 0,2 -> 2,2
// 1,0 -> 0,1 | 1,1 -> 1,1 | 1,2 -> 2,1
// 2,0 -> 0,0 | 2,1 -> 1,0 | 2,2 -> 2,0
// newX = (size-1) - y, newY = x
//
//0 0 0 0
//1 0 0 0
//0 1 1 0
//0 0 0 0
//
// size = 4
// 0,0 -> 0,3 | 0,1 -> 1,3 | 0,2 -> 2,3 | 0,3 -> 3, 3
// 1,0 -> 0,2 | 1,1 -> 1,2 | 1,2 -> 2,2 | 1,3 -> 3, 2
// 2,0 -> 0,1 | 2,1 -> 1,1 | 2,2 -> 2,1 | 2,3 -> 3, 1
// 3,0 -> 0,0 | 3,1 -> 1,0 | 3,2 -> 2,0 | 3,3 -> 3, 0
// newX = (size-1) - y, newY = x
//