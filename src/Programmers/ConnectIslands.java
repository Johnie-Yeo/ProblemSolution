// https://programmers.co.kr/learn/courses/30/lessons/42861

package Programmers;

import Test.OldestTest;

import java.util.ArrayList;
import java.util.Collections;

public class ConnectIslands {
    public static void main(String[] args) {
        new ConnectIslands().test();
    }
    private void test(){
        OldestTest test = new OldestTest();

        int n = 4;
        int[][] costs = {
                {0,1,1},
                {0,2,2},
                {1,2,5},
                {1,3,1},
                {2,3,8}
        };
        int expect = 4;
        int result = solution(n, costs);
        test.test(result, expect);
    }
    private class Node implements Comparable<Node>{
        int depart;
        int destination;
        int weight;
        public Node(int depart, int destination, int weight){
            this.depart = depart;
            this.destination = destination;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node node) {
            return this.weight - node.weight;
        }
    }
    public int solution(int n, int[][] costs) {
        ArrayList<Node> nodes = buildNodes(costs);
        Collections.sort(nodes);
        int answer = unionFind(n, nodes);
        return answer;
    }

    private int unionFind(int n, ArrayList<Node> nodes) {
        int[] parents = setParents(n);
        int result = 0;
        int count = 0;

        for(Node node : nodes){
            int u = node.depart;
            int v = node.destination;
            int parentU = getParent(u, parents);
            int parentV = getParent(v, parents);

            if(parentU != parentV){
                parents = union(parentU, parentV, parents);
                result += node.weight;
                count++;
                if(count >= n-1){
                    break;
                }
            }
        }
        return result;
    }

    private int[] union(int u, int v, int[] parents) {
        parents[u] = v;
        return parents;
    }

    private int getParent(int current, int[] parents) {
        while(current != parents[current]){
            current = parents[current];
            parents[current] = parents[parents[current]];
        }
        return current;
    }

    private int[] setParents(int n) {
        int[] parents = new int[n];
        for(int i = 0; i < n; i++){
            parents[i] = i;
        }
        return parents;
    }

    private ArrayList<Node> buildNodes(int[][] costs) {
        ArrayList<Node> nodes = new ArrayList<>();
        for(int[] cost : costs){
            int depart = cost[0];
            int destination = cost[1];
            int weight = cost[2];
            nodes.add(new Node(depart, destination, weight));
        }
        return nodes;
    }
}
