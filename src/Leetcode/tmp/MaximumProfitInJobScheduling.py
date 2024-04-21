class Solution:
    def jobScheduling(self, startTime: List[int], endTime: List[int], profit: List[int]) -> int:
        n = len(startTime)
        jobs = sorted([(startTime[i], endTime[i], profit[i]) for i in range(n)])

        nextPossJob = [n]*n
        # startorder_i will iterate through jobs in order of startTime (how jobs is ordered)
        # endorder_i will iterate through jobs in order of endTime
        startorder_i = 0
        for endorder_i in sorted(range(n), key=lambda x:jobs[x][1]):
            while startorder_i < n and jobs[endorder_i][1] > jobs[startorder_i][0]:
                startorder_i += 1
            if startorder_i == n:
                break
            nextPossJob[endorder_i] = startorder_i

        dp = [0]*(n+1)
        for i in range(n-1, -1, -1):
            # Two possibilities: use the current job at i or skip to next job
            dp[i] = max(jobs[i][2] + dp[nextPossJob[i]], dp[i+1])
        return dp[0]