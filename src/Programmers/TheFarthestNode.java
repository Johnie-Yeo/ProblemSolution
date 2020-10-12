package Programmers;

import Test.Test;

import java.util.*;

public class TheFarthestNode {
    public static void main(String[] args) {
        new TheFarthestNode().test();
    }

    private void test() {
        int n = 6;
        int[][] vertex = {
                {3, 6},
                {4, 3},
                {3, 2},
                {1, 3},
                {1, 2},
                {2, 4},
                {5, 2}
        };
        int expect = 3;
        int result = solution(n, vertex);
        Test.test(result, expect).printResult();
    }

    private class Dist {
        int index;
        int dist;
        public Dist(int index, int dist) {
            this.index = index;
            this.dist = dist;
        }
    }

    public int solution(int n, int[][] edge) {
        int start = 0;
        List<List<Integer>> graph = initGraph(n, edge);
        Queue<Dist> queue = new LinkedList<>();
        Map<Integer, Integer> map = new HashMap<>();
        boolean[] visit = new boolean[n];

        queue.add(new Dist(start, 0));
        map.put(0, 1);
        visit[start] = true;

        int max = 0;

        while(!queue.isEmpty()) {
            Dist cur = queue.poll();

            for(int next : graph.get(cur.index)) {
                if(!visit[next]) {
                    visit[next] = true;
                    int dist = cur.dist + 1;
                    queue.add(new Dist(next, dist));
                    max = dist;

                    int count = map.computeIfAbsent(max, c -> 0);
                    map.put(max, count+1);
                }
            }
        }

        return map.get(max);
    }

    private List<List<Integer>> initGraph(int n, int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>();

        for(int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for(int[] edge : edges) {
            int a = edge[0] - 1;
            int b = edge[1] - 1;
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        return graph;
    }

}
