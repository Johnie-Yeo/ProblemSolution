package Programmers;

import Test.Test;

import java.util.*;
// Programmers 지형 이동
public class MoveTerrain {
    public static void main(String[] args) {
        new MoveTerrain().test();
    }
    private void test(){
        Test test = new Test();

        int[][] land = {
                {1,   4,  8, 10},
                {5,   5,  5,  5},
                {10, 10, 10, 10},
                {10, 10, 10, 20}
        };
        int height = 3;
        int result = solution(land, height);
        int expect = 15;
        test.test(result, expect);

        land = new int[][]{
                {1,   4,  8, 10},
                {8,   5,  7, 10},
                {15, 15, 15, 15},
                {15, 15, 15, 25}
        };
        height = 3;
        result = solution(land, height);
        expect = 15;
        test.test(result, expect);

        land = new int[][]{
                {10, 11, 10, 11},
                { 2, 21, 20, 10},
                { 1, 20, 21, 11},
                { 2,  1,  2,  1}
        };
        height = 1;
        result = solution(land, height);
        expect = 18;
        test.test(result, expect);
    }

    private final int[] dirX = {0,-1,0,1};
    private final int[] dirY = {-1,0,1,0};
    private class Point{
        int x,  y;
        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    private class Node implements Comparable<Node>{
        int depart, destination, weight;
        public Node(int depart, int destination, int weight){
            this.depart = depart;
            this.destination = destination;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }

        @Override
        public int hashCode() {
            String str = this.depart + "," + this.destination;
            return str.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            Node o = (Node)obj;
            if(this.depart == o.depart && this.destination == o.destination){
                return true;
            }
            return false;
        }
    }
    public int solution(int[][] land, int height) {
        int N = land.length;

        int[][] sections = divideSections(land, height, N);
        ArrayList<Node> ladderList = getLadderList(land, sections, N);
        int numOfSections = getNumOfSections(sections, N);
        int min = mst(ladderList, numOfSections+1);

        return min;
    }

    private int getNumOfSections(int[][] sections, int n) {
        int max = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                max = Math.max(sections[i][j], max);
            }
        }
        return max;
    }

    private int[][] divideSections(int[][] land, int height, int n) {
        int[][] result = new int[n][n];
        boolean[][] check = new boolean[n][n];

        int section = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(check[i][j]){
                    continue;
                }

                ArrayDeque<Point> queue = new ArrayDeque<>();
                queue.add(new Point(i, j));

                while(!queue.isEmpty()){
                    Point cur = queue.pop();
                    result[cur.x][cur.y] = section;
                    check[cur.x][cur.y] = true;

                    for(int d = 0; d < 4; d++){
                        int x = cur.x + dirX[d];
                        int y = cur.y + dirY[d];

                        if(isOutOfRange(x, y, n) || check[x][y]){
                            continue;
                        }
                        int diff = Math.abs(land[x][y] - land[cur.x][cur.y]);
                        if(diff <= height){
                            queue.add(new Point(x, y));
                            check[x][y] = true;
                        }
                    }
                }

                section++;
            }
        }

        return result;
    }

    private ArrayList<Node> getLadderList(int[][] land, int[][] sections, int n) {
        ArrayList<Node> result = new ArrayList<>();

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                ArrayList<Node> ladders = getLadder(land, sections, i, j, n);
                result.addAll(ladders);
            }
        }
        result = cleanNodes(result);
        return result;
    }

    private ArrayList<Node> cleanNodes(ArrayList<Node> nodes) {
        HashMap<Node, Integer> hash = new HashMap<>();

        for(Node node : nodes){
            if(hash.containsKey(node)){
                if(hash.get(node) > node.weight){
                    hash.remove(node);
                    hash.put(node, node.weight);
                }
            }else{
                hash.put(node, node.weight);
            }
        }

        ArrayList<Node> result = new ArrayList<>();
        result.addAll(hash.keySet());
        return result;
    }

    private ArrayList<Node> getLadder(int[][] land, int[][] sections, int i, int j, int n) {
        ArrayList<Node> result = new ArrayList<>();
        for(int d = 0; d < 4; d++){
            int x = i + dirX[d];
            int y = j + dirY[d];
            if(isOutOfRange(x, y, n)){
                continue;
            }
            int section1 = sections[i][j];
            int section2 = sections[x][y];
            if(section1 < section2){
                int weight = Math.abs(land[i][j] - land[x][y]);
                result.add(new Node(section1, section2, weight));
            }
        }
        return result;
    }
    private boolean isOutOfRange(int x, int y, int n){
        if(x < 0 || y < 0 || x >= n || y >= n){
            return true;
        }
        return false;
    }
    private int mst(ArrayList<Node> nodes, int numOfVertex) {
        int result = 0;
        ArrayList<Integer> parents = makeSet(numOfVertex);
        Collections.sort(nodes);

        for(Node node : nodes){
            int u = node.depart;
            int v = node.destination;
            int parentOfU = getParent(parents, u);
            int parentOfV = getParent(parents, v);

            if(parentOfU != parentOfV){
                parents = union(parents, parentOfU, parentOfV);
                result += node.weight;
            }
        }
        return result;
    }
    private int getParent(ArrayList<Integer> parents, int vertex){
        while(vertex != parents.get(vertex)){
            int parent = parents.get(vertex);
            parents.set(vertex, parent);
            vertex = parent;
        }
        return vertex;
    }
    private ArrayList<Integer> union(ArrayList<Integer> parents, int u, int v) {
        parents.set(u, v);
        return parents;
    }

    private ArrayList<Integer> makeSet(int numOfVertex){
        ArrayList<Integer> set = new ArrayList<>();
        for(int i = 0; i < numOfVertex; i++){
            set.add(i);
        }
        return set;
    }
}
