class MedianFinder:
    def __init__(self):
        self.stream = []

    def addNum(self, num: int) -> None:
        self.stream.append(num)
        self.stream.sort() # O(N*Log N)

    def findMedian(self) -> float:
        len_ = len(self.stream)
        if len_ % 2 == 0: # Even Lengthed Stream
            return (self.stream[ len_ // 2 - 1 ] + self.stream[ len_ // 2 ]) / 2
        elif len_ % 2 != 0: # Odd Lengthed Stream
            return self.stream[ len_ // 2 ]
