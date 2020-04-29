package BOJ;

import Test.Test;

import java.util.ArrayList;
import java.util.Scanner;

public class NewGame2{
    private final int WHITE = 0;
    private final int RED = 1;
    private final int BLUE = 2;
    private final int[] dirX = {0, 0, -1, 1};
    private final int[] dirY = {1, -1, 0, 0};
    private final int RIGHT = 0;
    private final int LEFT = 1;
    private final int UP = 2;
    private final int DOWN = 3;
    private final int MAX_TURN = 1000;

    private class Horse{
        int index;
        int x, y, dir;
        public Horse(int index, int x, int y, int dir){
            this.index = index;
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
        public void move(){
            int dir = this.dir;
            if(isDirection(dir)){
                this.x += dirX[dir];
                this.y += dirY[dir];
            }
        }
        public void move(int dir){
            if(isDirection(dir)){
                this.x += dirX[dir];
                this.y += dirY[dir];
            }
        }
        public void reverseDirection(){
            switch (this.dir){
                case UP:
                    this.dir = DOWN;
                    break;
                case DOWN:
                    this.dir = UP;
                    break;
                case LEFT:
                    this.dir = RIGHT;
                    break;
                case RIGHT:
                    this.dir = LEFT;
                    break;
            }
        }
        public Horse set(int x, int y){
            this.x = x;
            this.y = y;
            return this;
        }
        private boolean isDirection(int dir){
            return (dir == UP || dir == DOWN || dir == LEFT || dir == RIGHT);
        }
    }
    private class Position{
        int color;
        ArrayList<Horse> horses;
        public Position(int color){
            this.color = color;
            this.horses = new ArrayList<>();
        }
        public void addHorse(Horse horse){
            this.horses.add(horse);
        }
        public void addHorses(ArrayList<Horse> horses){
            this.horses.addAll(horses);
        }
        public ArrayList<Horse> extractStack(int index){
            int size = this.horses.size();
            ArrayList<Horse> stack = subList(this.horses, index, size);
            this.horses = subList(this.horses, 0, index);
            return stack;
        }
        private ArrayList<Horse> subList(ArrayList<Horse> list, int start, int end){
            ArrayList<Horse> subList = new ArrayList<>();
            subList.addAll(list.subList(start, end));
            return subList;
        }

        public void setPosition(int x, int y) {
            this.horses.forEach(horse -> horse.set(x, y));
        }
    }

    public static void main(String[] args) {
        new NewGame2().test();
//        new NewGame2().solve();
    }
    private void test(){
        Test<Integer> test = new Test<Integer>();
        int N, K;
        int[][] map;
        int[][] horse;
        int result, expect;

        //Test 1
        N = 4; K = 4;
        map = new int[][]{
                {0, 0, 2, 0},
                {0, 0, 1, 0},
                {0, 0, 1, 2},
                {0, 2, 0, 0}
        };
        horse = new int[][]{
                {2, 1, 1},
                {3, 2, 3},
                {2, 2, 1},
                {4, 1, 2}
        };
        result = gameResult(N, K, map, horse);
        expect = -1;
        test.test(result, expect).printResult();

        //Test2
        N = 4; K = 4;
        map = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        };
        horse = new int[][]{
                {1, 1, 1},
                {1, 2, 1},
                {1, 3, 1},
                {1, 4, 1}
        };
        result = gameResult(N, K, map, horse);
        expect = 1;
        test.test(result, expect).printResult();

        //Test3
        N = 4; K = 4;
        map = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        };
        horse = new int[][]{
                {1, 1, 1},
                {1, 2, 1},
                {1, 3, 1},
                {2, 4, 3}
        };
        result = gameResult(N, K, map, horse);
        expect = 1;
        test.test(result, expect).printResult();

        //Test4
        N = 4; K = 4;
        map = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        };
        horse = new int[][]{
                {1, 1, 1},
                {1, 2, 1},
                {1, 3, 1},
                {3, 3, 3}
        };
        result = gameResult(N, K, map, horse);
        expect = 2;
        test.test(result, expect).printResult();

        //Test5
        N = 6; K = 10;
        map = new int[][]{
                {0, 1, 2, 0, 1, 1},
                {1, 2, 0, 1, 1, 0},
                {2, 1, 0, 1, 1, 0},
                {1, 0, 1, 1, 0, 2},
                {2, 0, 1, 2, 0, 1},
                {0, 2, 1, 0, 2, 1}
        };
        horse = new int[][]{
                {1, 1, 1},
                {2, 2, 2},
                {3, 3, 4},
                {4, 4, 1},
                {5, 5, 3},
                {6, 6, 2},
                {1, 6, 3},
                {6, 1, 2},
                {2, 4, 3},
                {4, 2, 1}
        };
        result = gameResult(N, K, map, horse);
        expect = 7;
        test.test(result, expect).printResult();
    }
    private void solve(){
        Scanner kb = new Scanner(System.in);

        int N = kb.nextInt();
        int K = kb.nextInt();
        int[][] map = new int[N][N];
        for(int i = 0 ; i < N; i++){
            for(int j = 0; j < N; j++){
                map[i][j] = kb.nextInt();
            }
        }
        int[][] horse = new int[K][3];
        for(int i = 0; i < K; i++){
            for(int j = 0; j < 3; j++){
                horse[i][j] = kb.nextInt();
            }
        }
        int result = gameResult(N, K, map, horse);
        System.out.println(result);
    }

    private int gameResult(int n, int k, int[][] map, int[][] horses) {
        Position[][] position = getPosition(n, k, map, horses);
        ArrayList<Horse> horseList = getHorseList(horses);
        position = initHorse(position, horseList);

        int turn = 0;
        while(turn <= MAX_TURN){
            for(Horse horse : horseList){
                horse = move(n, horse, horseList, position);
                if(isEnd(position[horse.x][horse.y])){
                    return turn+1;
                }
            }

            turn++;
        }
        return -1;
    }

    private Horse move(int n, Horse horse, ArrayList<Horse> horseList, Position[][] position) {
        ArrayList<Horse> stack = getHorseStack(position, horse);
        moveStack(n, position, stack, horse);
        setConsistency(position, horseList, stack, horse);

        return horse;
    }

    private void setConsistency(Position[][] position, ArrayList<Horse> horseList, ArrayList<Horse> stack, Horse horse) {
        int x = horse.x;
        int y = horse.y;
        position[x][y].setPosition(x, y);
        setConsistencyList(horseList, stack, x, y);
    }

    private void setConsistencyList(ArrayList<Horse> horseList, ArrayList<Horse> stack, int x, int y) {
        for(Horse horse : stack){
            int index = horse.index;
            horseList.get(index).set(x, y);
        }
    }

    private void moveStack(int n, Position[][] position, ArrayList<Horse> stack, Horse horse) {
        horse.move();
        int x = horse.x;
        int y = horse.y;
        if(isOutOfRange(x, y, n)){
            moveBlue(position, stack, horse, n);
        }else{
            switch (position[x][y].color){
                case WHITE:
                    moveWhite(position, stack, horse);
                    break;
                case RED:
                    moveRed(position, stack, horse);
                    break;
                case BLUE:
                    moveBlue(position, stack, horse, n);
                    break;
            }
        }
    }

    private void moveBlue(Position[][] position, ArrayList<Horse> stack, Horse horse, int n) {
        moveReverseWay(horse);
        int x = horse.x;
        int y = horse.y;
        if(isOutOfRange(x, y, n)){
            setBack(position, stack, horse);
        }else{
            switch (position[x][y].color){
                case WHITE:
                    moveWhite(position, stack, horse);
                    break;
                case RED:
                    moveRed(position, stack, horse);
                    break;
                case BLUE:
                    setBack(position, stack, horse);
                    break;
            }
        }
    }

    private void setBack(Position[][] position, ArrayList<Horse> stack, Horse horse){
        horse.reverseDirection();
        horse.move();
        horse.reverseDirection();
        int x = horse.x;
        int y = horse.y;
        position[x][y].addHorses(stack);
    }

    private void moveReverseWay(Horse horse) {
        horse.reverseDirection();
        horse.move();
        horse.move();
    }

    private void moveRed(Position[][] position, ArrayList<Horse> stack, Horse horse) {
        int x = horse.x;
        int y = horse.y;
        ArrayList<Horse> list = reverseList(stack);
        position[x][y].addHorses(list);
    }

    private ArrayList<Horse> reverseList(ArrayList<Horse> origin){
        ArrayList<Horse> list = new ArrayList<>();
        int size = origin.size();
        for(int i = size - 1; i >= 0; i--){
            list.add(origin.get(i));
        }
        return list;
    }

    private void moveWhite(Position[][] position, ArrayList<Horse> stack, Horse horse) {
        int x = horse.x;
        int y = horse.y;
        position[x][y].addHorses(stack);
    }

    private boolean isOutOfRange(int x, int y, int n) {
        return (x < 0 || y < 0 || x >= n || y >= n);
    }

    private ArrayList<Horse> getHorseStack(Position[][] position, Horse horse) {
        int x = horse.x;
        int y = horse.y;
        ArrayList<Horse> horses = position[x][y].horses;
        int size = horses.size();
        int index = getIndex(horses, size, horse.index);
        ArrayList<Horse> stack = position[x][y].extractStack(index);
        return stack;
    }

    private int getIndex(ArrayList<Horse> horses, int size, int index) {
        for(int i = 0; i < size; i++){
            if(horses.get(i).index == index){
                return i;
            }
        }
        return -1;
    }

    private Position[][] initHorse(Position[][] position, ArrayList<Horse> horseList) {
        for(Horse horse : horseList){
            int x = horse.x;
            int y = horse.y;
            position[x][y].addHorse(horse);
        }
        return position;
    }

    private boolean isEnd(Position position) {
        if(position.horses.size() >= 4){
            return true;
        }
        return false;
    }

    private ArrayList<Horse> getHorseList(int[][] horses) {
        ArrayList<Horse> list = new ArrayList<>();
        int index = 0;
        for(int[] horse : horses){
            int r = horse[0]-1;
            int c = horse[1]-1;
            int dir = horse[2]-1;
            Horse tmp = new Horse(index++, r, c, dir);
            list.add(tmp);
        }
        return list;
    }

    private Position[][] getPosition(int n, int k, int[][] map, int[][] horses) {
        Position[][] position = new Position[n][n];

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                position[i][j] = new Position(map[i][j]);
            }
        }

        return position;
    }

}