package Programmers.kakao;

import Test.Test;
import java.util.*;

public class FindWayGame {
    public static void main(String[] args) {
        new FindWayGame().test();
    }

    private void test() {
        int[][] nodeinfo = {
                {5, 3},
                {11, 5},
                {13, 3},
                {3, 5},
                {6, 1},
                {1, 3},
                {8, 6},
                {7, 2},
                {2, 2}
        };
        int[][] expect = {
                {7, 4, 6, 9, 1, 8, 5, 2, 3},
                {9, 6, 5, 8, 1, 4, 3, 2, 7}
        };
        int[][] result = solution(nodeinfo);
        Test.test(result, expect).printResult();
    }

    public class Node implements Comparable<Node> {
        int index;
        int x, y;
        Node left, right;

        public Node(int index, int x, int y) {
            this.index = index;
            this.x = x;
            this.y = y;
            this.left = null;
            this.right = null;
        }

        public Node(Node node) {
            this.index = node.index;
            this.x = node.x;
            this.y = node.y;
            this.left = node.left;
            this.right = node.right;
        }

        public List<Integer> preOrderTraverse() {
            List<Integer> list = new ArrayList<>();

            if(list != null) {
                list.add(this.index);
            }

            if(this.left != null) {
                list.addAll(this.left.preOrderTraverse());
            }
            if(this.right != null) {
                list.addAll(this.right.preOrderTraverse());
            }

            return list;
        }

        public List<Integer> postOrderTraverse() {
            List<Integer> list = new ArrayList<>();

            if(this.left != null) {
                list.addAll(this.left.postOrderTraverse());
            }

            if(this.right != null) {
                list.addAll(this.right.postOrderTraverse());
            }

            if(list != null) {
                list.add(this.index);
            }

            return list;
        }

        @Override
        public int compareTo(Node o) {
            return o.y - this.y;
        }
    }

    public class Tree {
        Node root;

        public Tree() {
            this.root = null;
        }

        public void add(Node node) {
            this.root = addNode(this.root, node);
        }

        private Node addNode(Node root, Node cur) {
            if(root == null) {
                root = new Node(cur);
                return root;
            }

            if(root.x < cur.x) {
                root.right = addNode(root.right, cur);
            } else {
                root.left = addNode(root.left, cur);
            }

            return root;
        }
    }
    public int[][] solution(int[][] nodeinfo) {
        List<Node> nodeList = initNodeList(nodeinfo);

        Tree bst = new Tree();

        for(Node node : nodeList) {
            bst.add(node);
        }

        List<Integer> preOrder = bst.root.preOrderTraverse();
        List<Integer> postOrder = bst.root.postOrderTraverse();

        int[][] answer = { toArray(preOrder), toArray(postOrder) };
        return answer;
    }

    private int[] toArray(List<Integer> list ) {
        return list.stream().mapToInt(e->e).toArray();
    }

    private List<Node> initNodeList(int[][] nodeinfo) {
        List<Node> list = new ArrayList<>();

        int length = nodeinfo.length;

        for(int i = 0; i < length; i++) {
            int x = nodeinfo[i][0];
            int y = nodeinfo[i][1];
            Node node = new Node(i+1, x, y);
            list.add(node);
        }

        Collections.sort(list);

        return list;
    }
}
