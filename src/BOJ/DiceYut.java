package BOJ;

import Test.OldTest;

import java.util.Scanner;

public class DiceYut{
    private class Horse{
        int location;
        int score;
        int blueMapIndex;
        public Horse(){
            this.location = 0;
            this.score = 0;
            this.blueMapIndex = -1;
        }
        public Horse(int location, int score, int blueMapIndex){
            this.location = location;
            this.score = score;
            this.blueMapIndex = blueMapIndex;
        }
        public void setBlueMapIndex(int index){
            this.blueMapIndex = index;
        }
        public void move(int move){
            this.location += move;
        }
        public void addScore(int score){
            this.score += score;
        }
        public Horse clone(){
            return new Horse(this.location, this.score, this.blueMapIndex);
        }

        public boolean isOnSameLocation(Horse horse) {
            if(this.isOnExactlySameRouteAndLocation(horse)){
                return true;
            }else if(this.isDifferentRouteButSameLocation(horse)){
                return true;
            }
            return false;
        }

        private boolean isDifferentRouteButSameLocation(Horse horse) {
            if(this.location >= 0 && horse.location >= 0){
                if(this.blueMapIndex >= 0 && horse.blueMapIndex >= 0){
                    if(this.getBlueLocation() > 3 && (this.getBlueLocation() == horse.getBlueLocation())){
                        return true;
                    }
                }else if(this.blueMapIndex >= 0){
                    if(this.getBlueLocation() == 7 && horse.location == 20){
                        return true;
                    }
                }else if(horse.blueMapIndex >= 0){
                    if(this.location == 20 && horse.getBlueLocation() == 7){
                        return true;
                    }
                }
            }
            return false;
        }

        private boolean isOnExactlySameRouteAndLocation(Horse horse){
            if(this.location >= 0 && horse.location >= 0){
                return (this.location == horse.location && this.blueMapIndex == horse.blueMapIndex);
            };
            return false;
        }
        private int getBlueLocation(){
            if(this.isOnBluePath() && this.location >= 0){
                if(this.blueMapIndex == 1){
                    return this.location+1;
                }else{
                    return this.location;
                }
            }
            return -1;
        }
        private boolean isOnBluePath(){
            if(this.blueMapIndex >= 0){
                return true;
            }
            return false;
        }
    }
    public static void main(String[] args) {
//        new DiceYut().solve();
        new DiceYut().test();
    }
    private void test(){
        OldTest<Integer> test = new OldTest<Integer>();

        int[] input;
        int result, expect;

        input = new int[]{1, 2, 3, 4, 1, 2, 3, 4, 1, 2};
        result = getMaxScore(input);
        expect = 190;
        test.test(result, expect).printResult();

        input = new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        result = getMaxScore(input);
        expect = 133;
        test.test(result, expect).printResult();

        input = new int[]{5, 1, 2, 3, 4, 5, 5, 3, 2, 4};
        result = getMaxScore(input);
        expect = 214;
        test.test(result, expect).printResult();

        input = new int[]{5, 5, 5, 5, 5, 5, 5, 5, 5, 5};
        result = getMaxScore(input);
        expect = 130;
        test.test(result, expect).printResult();

        input = new int[]{5, 5, 5, 5, 5, 1, 1, 1, 1, 1};
        result = getMaxScore(input);
        expect = 167;
        test.test(result, expect).printResult();

        input = new int[]{5, 5, 5, 5, 5, 2, 2, 2, 2, 2};
        result = getMaxScore(input);
        expect = 160;
        test.test(result, expect).printResult();

        input = new int[]{5, 5, 5, 5, 5, 2, 2, 1, 3, 3};
        result = getMaxScore(input);
        expect = 161;
        test.test(result, expect).printResult();
    }
    private void solve(){
        Scanner kb = new Scanner(System.in);

        int[] input = new int[10];
        for(int i = 0; i < 10; i++){
            input[i] = kb.nextInt();
        }

        int result = getMaxScore(input);
        System.out.println(result);
    }

    private int getMaxScore(int[] input) {
        Horse[] horse = initHorse();

        int[] redMap = getRedMap();
        int[][] blueMap = getBlueMap();

        int max = dfs(0, input, horse, redMap, blueMap);

        return max;
    }

    private Horse[] initHorse() {
        Horse[] horse = new Horse[4];
        for(int i = 0; i < 4; i++){
            horse[i] = new Horse();
        }
        return horse;
    }

    private int dfs(int index, int[] input, Horse[] horses, int[] redMap, int[][] blueMap) {
        if(index >= 10){
            return sum(horses);
        }
        int move = input[index++];
        int max = 0;
        for(int i = 0; i < 4; i++){
            Horse movedHorse = moveHorse(horses, i, move, redMap, blueMap);
            if(movedHorse == null || isDuplicated(horses, movedHorse)){
                continue;
            }
            Horse tmp = horses[i];
            horses[i] = movedHorse;
            int result = dfs(index, input, horses, redMap, blueMap);
            horses[i] = tmp;
            max = Math.max(max, result);
        }
        return max;
    }

    private boolean isDuplicated(Horse[] horses, Horse movedHorse) {
        for(Horse horse : horses){
            if(movedHorse.isOnSameLocation(horse)){
                return true;
            }
        }
        return false;
    }

    private Horse moveHorse(Horse[] horses, int index, int move, int[] redMap, int[][] blueMap) {
        Horse horse = horses[index].clone();
        if(horse.location < 0){
            return null;
        }
        horse.move(move);
        int score = getScore(horse, redMap, blueMap);
        horse.addScore(score);
        if(switchableToBluePath(horse)){
            horse.setBlueMapIndex(horse.location/5-1);
            horse.location = 0;
        }
        return horse;
    }

    private boolean switchableToBluePath(Horse horse) {
        if(horse.blueMapIndex < 0){
            if(horse.location <= 15 && horse.location % 5 == 0){
                return true;
            }
        }
        return false;
    }

    private int getScore(Horse horse, int[] redMap, int[][] blueMap) {
        try{
            if(horse.blueMapIndex < 0){
                return redMap[horse.location-1];
            }else{
                return blueMap[horse.blueMapIndex][horse.location-1];
            }
        }catch (ArrayIndexOutOfBoundsException e){
            horse.location = -1;
            return 0;
        }

    }

    private int[][] getBlueMap() {
        int[][]map = {
                {13, 16, 19, 25, 30, 35, 40},
                {    22, 24, 25, 30, 35, 40},
                {28, 27, 26, 25, 30, 35, 40}
        };
        return map;
    }

    private int[] getRedMap() {
        int[] map = new int[20];
        for(int i = 0; i < 20; i++){
            map[i] = 2*(i+1);
        }
        return map;
    }

    private int sum(Horse[] horses){
        int sum = 0;
        for(Horse horse : horses){
            sum += horse.score;
        }
        return sum;
    }
}