import unittest


class Node(object):
    def __init__(self, val=0, left=None, right=None, next=None):
        self.val = val
        self.left = left
        self.right = right
        self.next = next


class Solution(object):
    def connect(self, root):
        q = [root] if root.val else []
        result = []

        while q:
            nextQ = []
            for elem in q:
                result.append(elem)
                if elem.left:
                    nextQ.append(elem.left)
                    nextQ.append(elem.right)
            q = nextQ

        return result


class TestSolution(unittest.TestCase):
    def setUp(self):
        self.solution = Solution()

    def test_case1(self):
        root = [1, 2, 3, 4, 5, 6, 7]
        expect = [1, '#', 2, 3, '#', 4, 5, 6, 7, '#']
        result = self.solution.connect(self.parseToNode(root))
        self.assertEqual(expect, result)

    def test_case2(self):
        root = []
        expect = []
        result = self.solution.connect(self.parseToNode(root))
        self.assertEqual(expect, result)

    def parseToNode(self, root):
        nodeList = list(map(Node, root))

        for i in range(len(root)):
            node = nodeList[i]
            leftIndex = (i+1)*2-1
            rightIndex = (i+1)*2
            if leftIndex >= len(root):
                break
            node.left = nodeList[leftIndex]
            node.right = nodeList[rightIndex]

        return nodeList[0] if nodeList else Node(None)

if __name__ == '__main__':
    unittest.main()