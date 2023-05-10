import unittest


class ListNode(object):
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next


class Solution(object):
    def insertionSortList(self, head):
        result = head
        cur = head
        prev = None
        while cur is not None:
            nextNode = cur.next

            if prev is not None and prev.val > cur.val:

                if result.val > cur.val:
                    cur.next = result
                    result = cur
                    prev.next = nextNode

                else:
                    tmp = result
                    while tmp.next is not None and tmp.next.val <= cur.val:
                        tmp = tmp.next
                    tmpNext = tmp.next
                    tmp.next = cur
                    cur.next = tmpNext
                    prev.next = nextNode
                cur = nextNode
            else:
                prev = cur
                cur = nextNode
        return result


class TestSolution(unittest.TestCase):
    def setUp(self):
        self.solution = Solution()

    def test_case1(self):
        head = self.createLinkedList([-1,5,3,4,0])
        result = self.solution.insertionSortList(head)
        expect = [-1,0,3,4,5]

        cur = result
        for i in range(len(expect)):
            self.assertEqual(expect[i], cur.val)
            cur = cur.next
        self.assertEqual(None, cur)

    def createLinkedList(self, inputList):
        head = None
        node = None
        for elem in inputList:
            cur = ListNode(elem)
            if head is None:
                head = cur
                node = cur
            else:
                node.next = cur
                node = node.next
        return head

if __name__ == '__main__':
    unittest.main()