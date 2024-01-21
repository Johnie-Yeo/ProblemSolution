# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

class Solution:
    def __init__(self):
        self.levels_nodes= {}

    def inorderTraversalUtil(self, root, level_num):
        sign = 1
        if root is None:
            sign *= -1
            return
        if level_num not in self.levels_nodes:
            self.levels_nodes[level_num] = []
        self.levels_nodes[level_num].append(root.val)
        self.inorderTraversalUtil(root.left, level_num + sign)
        self.inorderTraversalUtil(root.right, level_num +sign)
        return

    def levelOrder(self, root: Optional[TreeNode]) -> Dict[int, List[int]]:
        # Returns values at each level of the binary tree.
        self.inorderTraversalUtil(root, 0)
        # Convert the dictionary to a list of lists
        result = [self.levels_nodes[level] for level in sorted(self.levels_nodes.keys())]
        return result
