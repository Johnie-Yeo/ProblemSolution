from typing import List


class Solution:
    def findClosestElements(self, arr: List[int], k: int, x: int) -> List[int]:
        # It's easy to write, but we need to sort it twice, so it's not the best way
        return sorted(sorted(arr, key = lambda v: abs(v-x))[:k])