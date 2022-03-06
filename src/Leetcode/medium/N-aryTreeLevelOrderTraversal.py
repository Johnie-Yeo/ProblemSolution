import unittest
from typing import List


class Node:
    def __init__(self, val=None, children=None):
        self.val = val
        self.children = children


class Solution:
    def levelOrder(self, root: 'Node') -> List[List[int]]:
        queue = []
        result = []
        if root is not None:
            queue.append(root)
            result.append([root.val])

        while queue:
            children = []
            for node in queue:
                if node.children is not None:
                    children.extend(node.children)
            currentLevel = list(map(lambda child: child.val, children))
            if currentLevel:
                result.append(currentLevel)
            queue = children
        return result


class TestSolution(unittest.TestCase):
    def setUp(self):
        self.solution = Solution()

    def test_case1(self):
        root = Node(1)
        a = Node(3)
        b = Node(2)
        c = Node(4)
        d = Node(5)
        e = Node(6)
        root.children = [a, b, c]
        a.children = [d, e]
        expect = [[1],[3,2,4],[5,6]]
        result = self.solution.levelOrder(root)
        self.assertEqual(expect, result)

    def test_case2(self):
        root = Node(1)
        a = Node(2)
        b = Node(3)
        c = Node(4)
        d = Node(5)
        e = Node(6)
        f = Node(7)
        g = Node(8)
        h = Node(9)
        i = Node(10)
        j = Node(11)
        k = Node(12)
        l = Node(13)
        m = Node(14)
        root.children = [a, b, c, d]
        b.children = [e, f]
        c.children = [g]
        d.children = [h, i]
        f.children = [j]
        g.children = [k]
        h.children = [l]
        j.children = [m]
        expect = [[1],[2,3,4,5],[6,7,8,9,10],[11,12,13],[14]]
        result = self.solution.levelOrder(root)
        self.assertEqual(expect, result)

    def test_case3(self):
        root = None
        expect = []
        result = self.solution.levelOrder(root)
        self.assertEqual(expect, result)


if __name__ == '__main__':
    unittest.main()