//https://programmers.co.kr/learn/courses/30/lessons/42884

package Programmers;

import Test.OldestTest;

import java.util.ArrayList;
import java.util.Collections;

public class IntermittentCamera {
    public static void main(String[] args) {
        new IntermittentCamera().test();
    }
    private void test(){
        OldestTest test = new OldestTest();
        int[][] routes = {
                {-20,15},
                {-14,-5},
                {-18,-13},
                {-5,-3}
        };
        int expect = 2;
        int result = solution(routes);
        test.test(result, expect);
    }
    private class Route implements Comparable<Route>{
        int enter;
        int exit;
        public Route(int enter, int exit){
            this.enter = enter;
            this.exit = exit;
        }

        @Override
        public int compareTo(Route route) {
            return this.enter - route.enter;
        }
    }
    public int solution(int[][] routes){
        ArrayList<Route> routeList = buildRouteList(routes);

        int maxEnter = setMinInt();
        int minExit = setMaxInt();
        int count = 1;

        for(Route route : routeList){
            maxEnter = Math.max(maxEnter, route.enter);
            minExit = Math.min(minExit, route.exit);
            if(maxEnter > minExit){
                count++;
                minExit = route.exit;
            }
        }
        return count;
    }

    private int setMaxInt() {
        return Integer.MAX_VALUE;
    }

    private int setMinInt() {
        return Integer.MIN_VALUE;
    }

    private ArrayList<Route> buildRouteList(int[][] routes) {
        ArrayList<Route> list = new ArrayList<>();
        for(int[] route : routes){
            Route cur = new Route(route[0], route[1]);
            list.add(cur);
        }
        Collections.sort(list);
        return list;
    }
}
