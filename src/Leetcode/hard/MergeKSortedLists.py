import unittest
from typing import List, Optional
import heapq


class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next


class Solution:
    def mergeKLists(self, lists: List[Optional[ListNode]]) -> Optional[ListNode]:
        que = []
        for curNode in lists:
            while curNode:
                heapq.heappush(que, curNode.val)
                curNode = curNode.next

        prev = None
        head = None
        while que:
            cur = heapq.heappop(que)
            curNode = ListNode(cur)
            if not prev:
                prev = curNode
                head = curNode
            else:
                prev.next = curNode
                prev = curNode

        return head


class TestSolution(unittest.TestCase):
    def setUp(self):
        self.solution = Solution()

    def test_case1(self):
        lists = [[1,4,5],[1,3,4],[2,6]]
        expect = [1,1,2,3,4,4,5,6]
        result = self.solution.mergeKLists(self.convertToListNodeList(lists))
        self.assertEqual(expect, self.convertToList(result))

    def test_case2(self):
        lists = []
        expect = []
        result = self.solution.mergeKLists(self.convertToListNodeList(lists))
        self.assertEqual(expect, self.convertToList(result))

    def test_case3(self):
        lists = [[]]
        expect = []
        result = self.solution.mergeKLists(self.convertToListNodeList(lists))
        self.assertEqual(expect, self.convertToList(result))

    def convertToListNodeList(self, lists:List[List[int]]) -> List[Optional[ListNode]]:
        result = []
        for curList in lists:
            listNode = self.convertToListNode(curList)
            result.append(listNode)

        return result

    def convertToListNode(self, curList:List[int]) -> Optional[ListNode]:
        prev = None
        head = None
        for e in curList:
            cur = ListNode(e)
            if not prev:
                prev = cur
                head = prev
            else:
                prev.next = cur
                prev = cur
        return head

    def convertToList(self, listNode: Optional[ListNode]) -> List[int]:
        result = []

        while listNode:
            result.append(listNode.val)
            listNode = listNode.next
        return result


if __name__ == '__main__':
    unittest.main()