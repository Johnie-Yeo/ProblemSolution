package Leetcode.medium;

import Test.Test;

import java.util.ArrayList;

public class AddTwoNumbers {
    public class ListNode {
        private int val;
        private ListNode next;

        public ListNode() {}
        public ListNode(int val) { this.val = val; }
        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        public void add(int value) {
            ListNode next = new ListNode(value);
            ListNode cur = this;
            while(cur.next != null) {
                cur = cur.next;
            }
            cur.next = next;
        }
    }

    public static void main(String[] args) {
        new AddTwoNumbers().test();
    }

    private void test() {
        ListNode l1, l2;
        ListNode result, expect;

        l1 = initList(new int[]{2,4,3});
        l2 = initList(new int[]{5, 6, 4});
        expect = initList(new int[]{7,0,8});
        result = addTwoNumbers(l1, l2);
        Test.test(toArrayList(result), toArrayList(expect)).printResult();

        l1 = initList(new int[]{0});
        l2 = initList(new int[]{0});
        expect = initList(new int[]{0});
        result = addTwoNumbers(l1, l2);
        Test.test(toArrayList(result), toArrayList(expect)).printResult();

        l1 = initList(new int[]{9,9,9,9,9,9,9});
        l2 = initList(new int[]{9,9,9,9});
        expect = initList(new int[]{8,9,9,9,0,0,0,1});
        result = addTwoNumbers(l1, l2);
        Test.test(toArrayList(result), toArrayList(expect)).printResult();

        l1 = initList(new int[]{2, 4,9});
        l2 = initList(new int[]{5, 6, 4, 9});
        expect = initList(new int[]{7, 0, 4, 0, 1});
        result = addTwoNumbers(l1, l2);
        Test.test(toArrayList(result), toArrayList(expect)).printResult();

        l1 = initList(new int[]{9});
        l2 = initList(new int[]{1,9,9,9,9,9,9,9,9,9});
        expect = initList(new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1});
        result = addTwoNumbers(l1, l2);
        Test.test(toArrayList(result), toArrayList(expect)).printResult();
    }
    private ArrayList<Integer> toArrayList(ListNode listNode) {
        ArrayList<Integer> list = new ArrayList<>();

        while(listNode != null) {
            list.add(listNode.val);
            listNode = listNode.next;
        }

        return list;
    }
    private ListNode initList(int[] arr) {
        ListNode list = null;
        for(int e : arr) {
            if(list == null) {
                list = new ListNode(e);
            } else {
                list.add(e);
            }
        }
        return list;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = null;
        int prev = 0;

        while(!(l1 == null && l2 == null)) {
            int add = add(l1, l2, prev);
            prev = add / 10;
            result = addLast(result, add % 10);
            l1 = next(l1);
            l2 = next(l2);
        }

        if(prev > 0) {
            result = addLast(result, prev);
        }

        return result;
    }

    private ListNode next(ListNode node) {
        return node == null ? node : node.next;
    }

    private int add(ListNode l1, ListNode l2, int prev) {
        int a = l1 == null ? 0 : l1.val;
        int b = l2 == null ? 0 : l2.val;

        return a + b + prev;
    }

    private ListNode addLast(ListNode node, int value) {
        ListNode next = new ListNode(value);
        if(node == null) {
            return next;
        } else {
            ListNode cur = node;
            while(cur.next != null) {
                cur = cur.next;
            }
            cur.next = next;
        }
        return node;
    }

    private ListNode reverse(ListNode node) {
        ListNode prev = null;
        while(node.next != null) {
            ListNode next = node.next;
            node.next = prev;
            prev = node;
            node = next;
        }
        node.next = prev;
        return node;
    }
}
