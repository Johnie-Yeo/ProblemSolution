import unittest


class Twitter(object):

    def __init__(self):
        self.userFollower = {}
        self.feed = []

    def postTweet(self, userId, tweetId):
        self.feed.append({userId: tweetId})

    def getNewsFeed(self, userId):
        result = []
        followee = self.userFollower.get(userId) or []
        followee.append(userId)
        for i in reversed(range(len(self.feed))):
            if len(result) >= 10:
                break
            if list(self.feed[i].keys())[0] in followee:
                result.append(list(self.feed[i].values())[0])
        return result

    def follow(self, followerId, followeeId):
        followee = []
        if self.userFollower.get(followerId) is not None:
            followee = self.userFollower[followerId]
        followee.append(followeeId)
        self.userFollower[followerId] = followee

    def unfollow(self, followerId, followeeId):
        followee = []
        if self.userFollower.get(followerId) is not None:
            followee = self.userFollower[followerId]
        if followeeId in followee:
            followee.remove(followeeId)
        self.userFollower[followerId] = followee


class TestSolution(unittest.TestCase):
    def test_case(self):
        twitter = Twitter()
        twitter.postTweet(1, 5)  # User 1 posts a new tweet (id = 5).
        feed = twitter.getNewsFeed(1)  # User 1's news feed should return a list with 1 tweet id -> [5]. return [5]
        self.assertEqual([5], feed)
        twitter.follow(1, 2)  # User 1 follows user 2.
        twitter.postTweet(2, 6)  # User 2 posts a new tweet (id = 6).
        feed = twitter.getNewsFeed(1)
        # User 1's news feed should return a list with 2 tweet ids -> [6, 5].
        # Tweet id 6 should precede tweet id 5 because it is posted after tweet id 5.
        self.assertEqual([6, 5], feed)
        twitter.unfollow(1, 2)  # User 1 unfollows user 2.
        feed = twitter.getNewsFeed(1)
        # User 1's news feed should return a list with 1 tweet id -> [5], since user 1 is no longer following user 2.
        self.assertEqual([5], feed)


if __name__ == '__main__':
    unittest.main()
