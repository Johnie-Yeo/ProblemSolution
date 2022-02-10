import unittest


class TreeNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None


class Solution:
    def lowestCommonAncestor(self, root: 'TreeNode', p: 'TreeNode', q: 'TreeNode') -> 'TreeNode':
        pParent = TreeNode(root.val)
        self.getParent(root, p, pParent)
        qParent = TreeNode(root.val)
        self.getParent(root, q, qParent)
        
        while (pParent.left is not None and qParent.left is not None) and (pParent.left.val == qParent.left.val):
            pParent = pParent.left
            qParent = qParent.left
        
        return pParent

    def getParent(self, root, target, parent):
        if root.val == target.val:
            return True

        if root.left is not None:
            parent.left = TreeNode(root.left.val)
            if self.getParent(root.left, target, parent.left):
                return True

        if root.right is not None:
            parent.left = TreeNode(root.right.val)
            if self.getParent(root.right, target, parent.left):
                return True

        return False


class TestSolution(unittest.TestCase):
    def setUp(self):
        self.solution = Solution()

    def test_case1(self):
        root = TreeNode(3)
        a = TreeNode(5)
        b = TreeNode(1)
        c = TreeNode(6)
        d = TreeNode(2)
        e = TreeNode(0)
        f = TreeNode(8)
        g = None
        h = None
        i = TreeNode(7)
        j = TreeNode(4)
        root.left = a
        root.right = b
        a.left = c
        a.right = d
        b.left = e
        b.right = f
        c.left = g
        c.right = h
        d.left = i
        d.right = j

        p = a
        q = b
        expect = root
        result = self.solution.lowestCommonAncestor(root, p, q)
        self.assertEqual(expect.val, result.val)

    def test_case2(self):
        root = TreeNode(3)
        a = TreeNode(5)
        b = TreeNode(1)
        c = TreeNode(6)
        d = TreeNode(2)
        e = TreeNode(0)
        f = TreeNode(8)
        g = None
        h = None
        i = TreeNode(7)
        j = TreeNode(4)
        root.left = a
        root.right = b
        a.left = c
        a.right = d
        b.left = e
        b.right = f
        c.left = g
        c.right = h
        d.left = i
        d.right = j
        p = a
        q = j

        expect = a
        result = self.solution.lowestCommonAncestor(root, p, q)
        self.assertEqual(expect.val, result.val)

    def test_case3(self):
        root = TreeNode(1)
        a = TreeNode(2)

        p = root
        q = a
        expect = root
        result = self.solution.lowestCommonAncestor(root, p, q)
        self.assertEqual(expect.val, result.val)


if __name__ == '__main__':
    unittest.main()
