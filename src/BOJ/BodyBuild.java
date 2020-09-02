package BOJ;

import Test.OlderTest;

import java.util.ArrayList;
import java.util.Scanner;

public class BodyBuild{
    private class Person implements Comparable<Person>{
        int height;
        int weight;
        public Person(int weight,int height){
            this.height = height;
            this.weight = weight;
        }
        public boolean isHigher(Person o){
            return this.height > o.height;
        }
        public boolean isHeavier(Person o){
            return this.weight > o.weight;
        }
        @Override
        public int compareTo(Person person) {
            if(this.isHeavier(person) && this.isHigher(person)){
                return 1;
            }else if(person.isHeavier(this) && person.isHigher(this)){
                return - 1;
            }
            return 0;
        }
    }
    public static void main(String[] args) {
//        new Main().solve();
        new BodyBuild().test();
    }
    private void test(){
        OlderTest<String> test = new OlderTest<String>();

        int N;
        int[][] data;
        Person[] people;
        String result, expect;

        N = 5;
        data = new int[][]{{55, 185}, {58, 183}, {88, 186}, {60, 175}, {46, 155}};
        result = getBodyRank(N, data);
        expect = "2 2 1 2 5";
        test.test(result, expect).printResult();
    }

    private void solve(){
        Scanner kb = new Scanner(System.in);

        int N = kb.nextInt();
        int[][] data = new int[N][2];
        for(int i = 0; i < N; i++){
            data[i][0] = kb.nextInt();
            data[i][1] = kb.nextInt();
        }
        String result = getBodyRank(N, data);
        System.out.println(result);
        kb.close();
    }

    private String getBodyRank(int n, int[][] data) {
        ArrayList<Person> people = setPersonList(data);
        ArrayList<Integer> result = getRankingList(people);
        return toString(result);
    }

    private ArrayList<Integer> getRankingList(ArrayList<Person> people) {
        ArrayList<Integer> list = new ArrayList<>();
        for(Person person : people){
            int rank = getRank(person, people);
            list.add(rank);
        }
        return list;
    }

    private int getRank(Person me, ArrayList<Person> people) {
        int count = 0;
        for(Person person : people){
            if(me.compareTo(person) < 0){
                count++;
            }
        }
        return count+1;
    }

    private String toString(ArrayList<Integer> list) {
        StringBuilder sb = new StringBuilder();
        for(int elem : list){
            sb.append(elem);
            sb.append(" ");
        }
        String result = sb.toString();
        int length = result.length();
        return result.substring(0, length-1);
    }

    private ArrayList<Person> setPersonList(int[][] data) {
        ArrayList<Person> list = new ArrayList<>();
        for(int[] personData : data){
            Person person = new Person(personData[0], personData[1]);
            list.add(person);
        }
        return list;
    }
}