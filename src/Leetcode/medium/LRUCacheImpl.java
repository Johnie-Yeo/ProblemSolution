package Leetcode.medium;

import Test.Test;

import java.util.*;

public class LRUCacheImpl {
//    public class DoublyLinkedList {
//        private int value;
//        private DoublyLinkedList head;
//        private DoublyLinkedList tail;
//        private DoublyLinkedList prev;
//        private DoublyLinkedList next;
//        private int size = 0;
//
//        public DoublyLinkedList() {
//            this.value = 0;
//            this.head = null;
//            this.tail = null;
//            this.prev = null;
//            this.next = null;
//            this.size = 0;
//        }
//
//        public DoublyLinkedList(int value) {
//            this.value = value;
//            this.head = null;
//            this.tail = null;
//            this.prev = null;
//            this.next = null;
//            this.size = 1;
//        }
//
//        public int add(int value) {
//            DoublyLinkedList node = new DoublyLinkedList(value);
//
//            if(this.head == null) {
//                this.head = node;
//            } else {
//                node.prev = this.tail;
//                this.tail.next = node;
//            }
//            this.tail = node;
//
//            return this.size++;
//        }
//
//        public int remove(int index) {
//            DoublyLinkedList node = this.getNode(index);
//            if(node == this.head && node == this.tail) {
//                this.head = null;
//                this.tail = null;
//            } else if(node == this.head) {
//                this.head = this.head.next;
//                this.head.prev = null;
//            } else if (node == this.tail) {
//                this.tail = this.tail.prev;
//                this.tail.next = null;
//            } else {
//                node.prev.next = node.next;
//                node.next.prev= node.prev;
//            }
//
//            this.size--;
//            return node.value;
//        }
//
//        public void set(int index, int value) {
//            DoublyLinkedList node = this.getNode(index);
//            node.value = value;
//        }
//
//        public int poll() {
//            return this.remove(0);
//        }
//
//        public int get(int index) {
//            int value = this.getNode(index).value;
//            return value;
//        }
//
//        private DoublyLinkedList getNode(int index) {
//            DoublyLinkedList node = this.head;
//            for(int i = 0; i < index; i++) {
//                node = node.next;
//            }
//            return node;
//        }
//    }

    /**
     * Solution1
     */
    public class LRUCache {
        private final Map<Integer, Integer> cache;

        public LRUCache(int capacity) {
            this.cache = new LinkedHashMap<>(){
                @Override
                protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                    return size() > capacity;
                }
            };
        }

        public int get(int key) {
            int value = cache.getOrDefault(key, -1);
            if(value != -1) {
                cache.remove(key);
                cache.put(key, value);
            }
            return value;
        }

        public void put(int key, int value) {
            cache.remove(key);
            cache.put(key, value);
        }
    }

    /**
     * Solution2
     */
//    public class LRUCache {
//        public class Node {
//            Node prev, next;
//            int key, value;
//
//            public Node() {
//                this(0, 0);
//            }
//            public Node(int key, int value) {
//                this.key = key;
//                this.value = value;
//            }
//        }
//
//        private Node head, tail;
//        private final Map<Integer, Node> cache;
//        private final int capacity;
//
//        public LRUCache(int capacity) {
//            this.cache = new HashMap<>();
//            this.capacity = capacity;
//        }
//
//        public int get(int key) {
//            Node target = this.cache.get(key);
//
//            if(target == null) {
//                return -1;
//            }
//            update(target);
//
//            return target.value;
//        }
//
//        public void put(int key, int value) {
//            Node target = this.cache.get(key);
//
//            if(target != null) {
//                remove(target);
//            }
//
//            target = new Node(key, value);
//
//            this.cache.put(key, target);
//            if(this.cache.size() > this.capacity) {
//                int headKey = head.key;
//                remove(this.cache.get(headKey));
//                this.cache.remove(headKey);
//            }
//
//            add(target);
//        }
//
//        private void update(Node target) {
//            remove(target);
//            add(target);
//        }
//
//        private void add(Node target) {
//            if(this.head == null) {
//                this.head = target;
//                this.tail = target;
//            } else {
//                target.next = null;
//                target.prev = tail;
//                tail.next = target;
//                tail = target;
//            }
//        }
//
//        private void remove(Node target) {
//            if(target.prev == null && target.next == null) {
//                return;
//            } else if(target == head && target == tail){
//                this.head = null;
//                this.tail = null;
//            } else if(target == head) {
//                this.head = this.head.next;
//                this.head.prev = null;
//            } else if(target == tail) {
//                this.tail = this.tail.prev;
//                this.tail.next = null;
//            } else {
//                target.prev.next = target.next;
//                target.next.prev = target.prev;
//            }
//        }
//    }


    public static void main(String[] args) {
        new LRUCacheImpl().test();
    }

    private void test() {
        String[] order;
        int[][] payload;
        Integer[] result, expect;

        order = new String[]{"LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"};
        payload = new int[][]{{2}, {1, 1}, {2, 2}, {1}, {3, 3}, {2}, {4, 4}, {1}, {3}, {4}};
        result = testCase(order, payload);
        expect = new Integer[]{null, null, null, 1, null, -1, null, -1, 3, 4};
        Test.test(result, expect).printResult();

        order = new String[]{"LRUCache", "put", "get"};
        payload = new int[][]{{1}, {2, 1}, {2}};
        result = testCase(order, payload);
        expect = new Integer[]{null, null, 1};
        Test.test(result, expect).printResult();

        order = new String[]{"LRUCache","put","put","get","put","put","get"};
        payload = new int[][]{{2}, {2, 1}, {2, 2}, {2}, {1, 1}, {4, 1}, {2}};
        result = testCase(order, payload);
        expect = new Integer[]{null, null, null, 2, null, null, -1};
        Test.test(result, expect).printResult();

        order = new String[]{"LRUCache","put","put","put","put","get","get"};
        payload = new int[][]{{2}, {2, 1}, {1, 1}, {2, 3}, {4, 1}, {1}, {2}};
        result = testCase(order, payload);
        expect = new Integer[]{null,null,null,null,null,-1,3};
        Test.test(result, expect).printResult();
    }

    private final String INIT = "LRUCache";
    private final String PUT = "put";
    private final String GET = "get";

    private Integer[] testCase(String[] order, int[][] payload) {
        List<Integer> result = new ArrayList<>();
        int size = order.length;
        LRUCache cache = null;

        for(int i = 0; i < size; i++) {
            String command = order[i];
            if(command.equals(INIT)) {
                int capacity = payload[i][0];
                cache = new LRUCache(capacity);
                result.add(null);
            } else if(command.equals(GET)) {
                int value = cache.get(payload[i][0]);
                result.add(value);
            } else if(command.equals(PUT)) {
                int key = payload[i][0];
                int value = payload[i][1];
                cache.put(key, value);
                result.add(null);
            }
        }

        Integer[] answer = result.stream().toArray(Integer[]::new);
        return answer;
    }
}
