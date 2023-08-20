class Solution:
    def splitArray(self, nums: List[int], m: int) -> int:
        low, high = 0, 0
        for n in nums:
            low = max(low, n)
            high += n
        while low < high:
            mid = (low + high) // 2
            partitions = self.split_nums(nums, mid)
            if partitions <= m:
                high = mid
            else:
                low = mid + 1
        return high

    def split_nums(self, nums: List[int], limit: int) -> int:
        partitions, total = 1, 0
        for n in nums:
            if total + n > limit:
                total = 0
                partitions += 1
            total += n
        return partitions
