import unittest


class Node(object):
    def __init__(self, val=0, left=None, right=None, next=None):
        self.val = val
        self.left = left
        self.right = right
        self.next = next


class Solution(object):
    def connect(self, root):
        queue = [root]

        while queue:
            for i in range(len(queue) - 1):
                queue[i].next = queue[i+1]

            for _ in range(len(queue)):
                current = queue.pop(0)
                if current and current.left:
                    queue.append(current.left)
                    queue.append(current.right)
        return root


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

        # for i in range(len(root)):
        #     node = nodeList[i]
        #     leftIndex = (i+1)*2-1
        #     rightIndex = (i+1)*2
        #     if leftIndex >= len(root):
        #         break
        #     node.left = nodeList[leftIndex]
        #     node.right = nodeList[rightIndex]

        return nodeList[0] if nodeList else Node(None)

if __name__ == '__main__':
    unittest.main()