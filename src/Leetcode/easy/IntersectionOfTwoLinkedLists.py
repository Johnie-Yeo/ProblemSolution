import unittest


# Definition for singly-linked list.
class ListNode(object):
    def __init__(self, x):
        self.val = x
        self.next = None


class Solution(object):
    def getIntersectionNode(self, headA, headB):
        s = set()
        aNode = headA
        while aNode is not None:
            s.add(aNode)
            aNode = aNode.next
        bNode = headB
        while bNode is not None:
            if bNode in s:
                return bNode
            else:
                bNode = bNode.next
        return None


class TestSolution(unittest.TestCase):
    def setUp(self):
        self.solution = Solution()

    def test_case1(self):
        intersectVal = 8
        listA = [4, 1, 8, 4, 5]
        listB = [5, 6, 1, 8, 4, 5]
        skipA = 2
        skipB = 3
        intersect = ListNode(8)
        headA = self.generateLinkedList(listA, intersect)
        headB = self.generateLinkedList(listB, intersect)
        result = self.solution.getIntersectionNode(headA, headB)
        expect = intersect
        self.assertEqual(expect, result)

    def test_case2(self):
        intersectVal = 2
        listA = [1, 9, 1, 2, 4]
        listB = [3, 2, 4]
        skipA = 3
        skipB = 1
        intersect = ListNode(2)
        headA = self.generateLinkedList(listA, intersect)
        headB = self.generateLinkedList(listB, intersect)
        result = self.solution.getIntersectionNode(headA, headB)
        expect = intersect
        self.assertEqual(expect, result)

    def test_case3(self):
        intersectVal = 0
        listA = [2, 6, 4]
        listB = [1, 5]
        skipA = 3
        skipB = 2
        intersect = ListNode(0)
        headA = self.generateLinkedList(listA, intersect)
        headB = self.generateLinkedList(listB, intersect)
        result = self.solution.getIntersectionNode(headA, headB)
        expect = None
        self.assertEqual(expect, result)

    def generateLinkedList(self, listA, intersect):
        head: ListNode = None
        node: ListNode = None

        for elem in listA:
            curNode = None
            if elem == intersect.val:
                curNode = intersect
            else:
                curNode = ListNode(elem)

            if head is None:
                head = curNode
                node = head
            else:
                node.next = curNode
                node = curNode

            if node.next is not None:
                break
        return head


if __name__ == '__main__':
    unittest.main()
