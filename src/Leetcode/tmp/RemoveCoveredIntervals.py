class Solution(object):
    def removeCoveredIntervals(self, intervals):
        intervals.sort(key = lambda x:(x[0], -x[1]))
        last = -1
        removed = 0
        for i in intervals:
            if i[1] <= last:
                removed += 1
            else:
                last = i[1]
        return len(intervals) - removed