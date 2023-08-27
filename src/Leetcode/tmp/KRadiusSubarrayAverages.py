class Solution:
    def getAverages(self, nums: List[int], k: int) -> List[int]:
        n=len(nums)
        counts=(2*k+1)
        prefix=[0]
        for x in nums:
            prefix.append(prefix[-1]+x)

        ans=[]
        for i in range(n):
            if i-k>=0 and i+k<n:
                ans.append((prefix[i+k+1]-prefix[i-k])//counts)

            else:
                ans.append(-1)

        return ans   