import unittest
from typing import List, Optional


class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class Solution:
    def buildTree(self, preorder: List[int], inorder: List[int]) -> Optional[TreeNode]:

        idxMap = {inorder[i]: i for i in range(len(inorder))}

        def buildPartitionTree(preorder: List[int], start: Optional[int], end: Optional[int]):
            if not preorder:
                return None

            root = TreeNode(preorder[start])
            if end is None:
                return root

            rootInorderIdx = idxMap.get(root.val)
            leftEndIdx = end
            rightStartIdx = None
            for i in range(start+1, end+1):
                if idxMap.get(preorder[i]) > rootInorderIdx:
                    leftEndIdx = i-1
                    rightStartIdx = i
                    break

            root.left = buildPartitionTree(preorder, start+1, leftEndIdx) if start + 1 <= leftEndIdx else None
            root.right = buildPartitionTree(preorder, rightStartIdx, end) if rightStartIdx is not None else None
            return root

        return buildPartitionTree(preorder, 0, len(inorder)-1)


class TestSolution(unittest.TestCase):
    def setUp(self):
        self.solution = Solution()

    def test_case1(self):
        preorder = [3,9,20,15,7]
        inorder = [9,3,15,20,7]
        expect = [3,9,20,None,None,15,7]
        result = self.solution.buildTree(preorder, inorder)
        self.assertEqual(expect, self.convertToList(result))

    def test_case2(self):
        preorder = [-1]
        inorder = [-1]
        expect = [-1]
        result = self.solution.buildTree(preorder, inorder)
        self.assertEqual(expect, self.convertToList(result))

    def test_case3(self):
        preorder = [1, 2]
        inorder = [2, 1]
        expect = [1, 2, None]
        result = self.solution.buildTree(preorder, inorder)
        self.assertEqual(expect, self.convertToList(result))

    def test_case4(self):
        preorder = [1, 2]
        inorder = [1, 2]
        expect = [1, None, 2]
        result = self.solution.buildTree(preorder, inorder)
        self.assertEqual(expect, self.convertToList(result))

    def convertToList(self, node: Optional[TreeNode]) -> List[Optional[int]]:
        result = []
        if not node:
            return []

        queue = [node]
        while queue:
            tmp = queue.copy()
            queue.clear()
            for cur in tmp:
                result.append(cur)
                if cur:
                    queue.append(cur.left)
                    queue.append(cur.right)
            if all(v is None for v in queue):
                queue.clear()

        return list(map(lambda e: e.val if e is not None else None, result))
