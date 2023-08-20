class Solution(object):
    sum = float('-inf')
    level = 0
    def maxLevelSum(self, root):
        lis = [root] #creating a list containing only the root node
        self.go(lis, 1)
        return self.level

    def go(self, lis, curr):
        if(len(lis)==0): return
        lvl = []
        s = 0
        for node in lis: #traversing through the level
            if node.left is not None:
                lvl.append(node.left) #adding the child nodes
            if node.right is not None:
                lvl.append(node.right) #adding the child nodes

            s = s + node.val
        self.go(lvl, (curr + 1))

        if s >= self.sum: #updating the sum and level counter
            self.sum = s
            self.level = curr        