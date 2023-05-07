class MyCircularQueue:
    def __init__(self, k: int):
        self.size_queue = k
        self.list_queue = []

    def enQueue(self, value: int) -> bool:
        if len(self.list_queue) < self.size_queue:
            try:
                self.list_queue.append(value)
                return True
            except:
                return False

    def deQueue(self) -> bool:
        try:
            self.list_queue.pop(0)
            return True
        except:
            return False

    def Front(self) -> int:
        try:
            return self.list_queue[0]
        except IndexError:
            return -1

    def Rear(self) -> int:
        try:
            return self.list_queue[-1]
        except IndexError:
            return -1

    def isEmpty(self) -> bool:
        if not self.list_queue:
            return True
        else:
            return False

    def isFull(self) -> bool:
        if len(self.list_queue) == self.size_queue:
            return True
        else:
            return False