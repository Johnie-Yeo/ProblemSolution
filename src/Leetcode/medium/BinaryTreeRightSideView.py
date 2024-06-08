import unittest
from typing import Optional, List


class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class Solution:
    def rightSideView(self, root: Optional[TreeNode]) -> List[int]:
        result = []
        curLayer = [root] if root else []
        while curLayer:
            result.append(curLayer[-1].val)
            nextLayer = []
            for node in curLayer:
                if node.left:
                    nextLayer.append(node.left)
                if node.right:
                    nextLayer.append(node.right)
            curLayer = nextLayer
        return result


class TestSolution(unittest.TestCase):
    def setUp(self):
        self.solution = Solution()

    def test_case1(self):
        root = [1, 2, 3, None, 5, None, 4]
        expect = [1, 3, 4]
        result = self.solution.rightSideView(self.convert_to_tree_node(root))
        self.assertEqual(expect, result)

    def test_case2(self):
        root = [1, None, 3]
        expect = [1, 3]
        result = self.solution.rightSideView(self.convert_to_tree_node(root))
        self.assertEqual(expect, result)

    def test_case3(self):
        root = []
        expect = []
        result = self.solution.rightSideView(self.convert_to_tree_node(root))
        self.assertEqual(expect, result)

    def test_case4(self):
        root = [1, 2]
        expect = [1, 2]
        result = self.solution.rightSideView(self.convert_to_tree_node(root))
        self.assertEqual(expect, result)

    def convert_to_tree_node(self, rootList: List[int]) -> Optional[TreeNode]:
        nodeList = []
        parentIdx = 0
        left = True
        for i in range(len(rootList)):
            cur = TreeNode(rootList[i]) if rootList[i] else None
            if i > 0:
                if left:
                    nodeList[parentIdx].left = cur
                    left = False
                else:
                    nodeList[parentIdx].right = cur
                    parentIdx += 1
                    left = True
            nodeList.append(cur)

        return nodeList[0] if nodeList else None
