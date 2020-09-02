package Programmers;

import Test.OldTest;

import java.util.ArrayList;

public class Cache{
    public static void main(String[] args) {
        new Cache().test();
    }
    private void test(){
        OldTest test = new OldTest();

        int cacheSize;
        String[]  cities;
        int result, expect;

        cacheSize = 3;
        cities = new String[]{"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"};
        result = solution(cacheSize, cities);
        expect = 50;
        test.test(result, expect).printResult();

        cacheSize = 3;
        cities = new String[]{
            "Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul"
        };
        result = solution(cacheSize, cities);
        expect = 21;
        test.test(result, expect).printResult();

        cacheSize = 2;
        cities = new String[]{
            "Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome"
        };
        result = solution(cacheSize, cities);
        expect = 60;
        test.test(result, expect).printResult();

        cacheSize = 5;
        cities = new String[]{
            "Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome"
        };
        result = solution(cacheSize, cities);
        expect = 52;
        test.test(result, expect).printResult();

        cacheSize = 2;
        cities = new String[]{"Jeju", "Pangyo", "NewYork", "newyork"};
        result = solution(cacheSize, cities);
        expect = 16;
        test.test(result, expect).printResult();

        cacheSize = 0;
        cities = new String[]{"Jeju", "Pangyo", "Seoul", "NewYork", "LA"};
        result = solution(cacheSize, cities);
        expect = 25;
        test.test(result, expect).printResult();
    }
    public int solution(int cacheSize, String[] cities) {
        ArrayList<String> list = new ArrayList<>();
        int time = 0;

        for(String city : cities){
            int index = getIndex(list, city);
            if(index >= 0){
                list.remove(index);
                time++;
            }else{
                int size = list.size();
                if(size > 0 && size >= cacheSize){
                    list.remove(0);
                }
                time += 5;
            }
            if(list.size() < cacheSize){
                list.add(city);
            }
        }

        return time;
    }

    private int getIndex(ArrayList<String> list, String target) {
        int index = 0;
        for(String elem : list){
            if(elem.equalsIgnoreCase(target)){
                return index;
            }
            index++;
        }
        return -1;
    }
}