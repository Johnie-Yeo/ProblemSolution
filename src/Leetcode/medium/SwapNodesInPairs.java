package Leetcode.medium;

import Test.Test;

import java.util.Arrays;
import java.util.List;

public class SwapNodesInPairs {
    public static void main(String[] args) {
        new SwapNodesInPairs().test();
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }

        @Override
        public boolean equals(Object obj) {
            ListNode o = (ListNode) obj;

            ListNode cur = this;

            if(o == null || cur.val != o.val) {
                return false;
            }
            if(cur.next != null) {
                return cur.next.equals(o.next);
            }

            return o.next == null;
        }
    }

    private void test() {
        ListNode head, expect, result;

        head = initListNode(Arrays.asList(2,5,3,4,6,2,2));
        expect = initListNode(Arrays.asList(5,2,4,3,2,6,2));
        result = swapPairs(head);

        head = initListNode(Arrays.asList(1, 2, 3, 4));
        expect = initListNode(Arrays.asList(2,1,4,3));
        result = swapPairs(head);
        Test.test(result, expect).printResult();

        head = initListNode(Arrays.asList(1, 2, 3, 4, 5));
        expect = initListNode(Arrays.asList(2,1,4,3, 5));
        result = swapPairs(head);
        Test.test(result, expect).printResult();

        head = initListNode(Arrays.asList(1, 2));
        expect = initListNode(Arrays.asList(2, 1));
        result = swapPairs(head);
        Test.test(result, expect).printResult();

        head = initListNode(Arrays.asList());
        expect = initListNode(Arrays.asList());
        result = swapPairs(head);
        Test.test(result, expect).printResult();

        head = initListNode(Arrays.asList(1));
        expect = initListNode(Arrays.asList(1));
        result = swapPairs(head);
        Test.test(result, expect).printResult();
    }

    private ListNode initListNode(List<Integer> list) {
        if(list.size() == 0) {
            return null;
        } else if(list.size() == 1) {
            return new ListNode(list.get(0));
        } else {
            return new ListNode(list.get(0), initListNode(list.subList(1, list.size())));
        }
    }

    public ListNode swapPairs(ListNode head) {
        ListNode result = swapPairs(head, head, null);
        return result;
    }

    private ListNode swapPairs(ListNode head, ListNode cur, ListNode prev) {
        if(cur == null || cur.next == null) {
            return head;
        }

        if(head == cur) {
            if(cur.next == null){
                return cur;
            } else {
                head = cur.next;
            }
        }

        ListNode next = cur.next;

        cur.next = next.next;
        next.next = cur;

        if(prev != null) {
            prev.next = next;
        }
        prev = cur;

        return swapPairs(head, cur.next, prev);
    }
}
