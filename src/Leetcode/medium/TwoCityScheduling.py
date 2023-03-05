class Solution(object):
    def twoCitySchedCost(self, costs):
        sm=sum([i for i,j in costs])
        diff=sorted([j-i for i,j in costs])
        return sm+sum(diff[:len(costs)//2])