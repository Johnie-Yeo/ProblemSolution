package BOJ;

import Test.Test;

import java.util.Arrays;
import java.util.Scanner;

public class StartingList {
    private int NUMBER_OF_PLAYERS = 11;
    public static void main(String[] args) {
//        new Main().solve();
        new StartingList().solve();
    }
    private void test(){
        Test test = new Test();

        int C;
        String input;
        int[][] abilities;
        int result, expect;

        C = 2;
        input = "100 0 0 0 0 0 0 0 0 0 0\n" +
                "0 80 70 70 60 0 0 0 0 0 0\n" +
                "0 40 90 90 40 0 0 0 0 0 0\n" +
                "0 40 85 85 33 0 0 0 0 0 0\n" +
                "0 70 60 60 85 0 0 0 0 0 0\n" +
                "0 0 0 0 0 95 70 60 60 0 0\n" +
                "0 45 0 0 0 80 90 50 70 0 0\n" +
                "0 0 0 0 0 40 90 90 40 70 0\n" +
                "0 0 0 0 0 0 50 70 85 50 0\n" +
                "0 0 0 0 0 0 66 60 0 80 80\n" +
                "0 0 0 0 0 0 50 50 0 90 88";
        abilities = parseAbilities(input);
        result = getMaxSumOfAbilities(abilities);
        expect = 970;
        test.test(result, expect).printResult();

        input = "10 0 0 0 0 0 0 0 0 0 0\n" +
                "0 10 0 0 0 0 0 0 0 0 0\n" +
                "0 0 10 1 0 0 0 0 0 0 0\n" +
                "0 0 1 0 0 0 0 0 0 0 0\n" +
                "0 0 0 0 1 0 0 0 0 0 0\n" +
                "0 0 0 0 0 1 0 0 0 0 0\n" +
                "0 0 0 0 0 0 1 0 0 0 0\n" +
                "0 0 0 0 0 0 0 1 0 0 0\n" +
                "0 0 0 0 0 0 0 0 1 0 0\n" +
                "0 0 0 0 0 0 0 0 0 1 0\n" +
                "0 0 0 0 0 0 0 0 0 0 1";
        abilities = parseAbilities(input);
        result = getMaxSumOfAbilities(abilities);
        expect = 29;
        test.test(result, expect).printResult();

        input = "1 0 0 0 0 0 0 0 0 0 0\n" +
                "0 1 0 0 0 0 0 0 0 0 0\n" +
                "0 0 1 0 0 0 0 0 0 0 0\n" +
                "0 0 0 1 0 0 0 0 0 0 0\n" +
                "0 0 0 0 1 0 0 0 0 0 0\n" +
                "0 0 0 0 0 1 0 0 0 0 0\n" +
                "0 0 0 0 0 0 1 0 0 0 0\n" +
                "0 0 0 0 0 0 0 1 0 0 0\n" +
                "0 0 0 0 0 0 0 0 1 0 0\n" +
                "0 0 0 0 0 0 0 0 0 1 0\n" +
                "0 0 0 0 0 0 0 0 0 0 1";
        abilities = parseAbilities(input);
        result = getMaxSumOfAbilities(abilities);
        expect = 11;
        test.test(result, expect).printResult();
    }

    private void solve(){
        Scanner kb = new Scanner(System.in);

        int C = Integer.parseInt(kb.nextLine());
        for(int i = 0; i < C; i++){
            int[][] abilities = new int[NUMBER_OF_PLAYERS][NUMBER_OF_PLAYERS];
            for(int j = 0; j < 11; j++){
                String player = kb.nextLine();
                abilities[j] = parsePlayerAbility(player);
            }
            int result = getMaxSumOfAbilities(abilities);
            System.out.println(result);
        }
        kb.close();
    }

    private int[][] parseAbilities(String input){
        String delim = "\n";
        String[] players = input.split(delim);
        int[][] abilities = Arrays.stream(players)
                .map(e-> parsePlayerAbility(e))
                .toArray(int[][]::new);
        return abilities;
    }
    private int[] parsePlayerAbility(String player){
        String delim = " ";
        int[] ability = Arrays.stream(player.split(delim)).mapToInt(e-> Integer.parseInt(e)).toArray();
        return ability;
    }

    private int getMaxSumOfAbilities(int[][] abilities) {
        int index = 0;
        boolean[] check = new boolean[NUMBER_OF_PLAYERS];
        int result = dfs(abilities, index, check, 0);
        return result;
    }

    private int dfs(int[][] abilities, int index, boolean[] check, int sum) {
        if(index >= NUMBER_OF_PLAYERS){
            return sum;
        }
        int result = 0;
        for(int i = 0; i < NUMBER_OF_PLAYERS; i++){
            if(!check[i] && abilities[i][index] > 0){
                check[i] = true;
                int cur = dfs(abilities, index+1, check, sum + abilities[i][index]);
                result = Math.max(cur, result);
                check[i] = false;
            }
        }
        return result;
    }
}
