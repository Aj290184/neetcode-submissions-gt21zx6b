class Twitter {

    private static int time = 0;
    private Map<Integer, Set<Integer>> followMap;
    private Map<Integer, List<Tweet>> tweetMap;

    public Twitter() {
        followMap = new HashMap<>();
        tweetMap = new HashMap<>();
    }

    class Tweet {
        int id;
        int time;

        Tweet(int id, int time) {
            this.id = id;
            this.time = time;
        }
    }

    public void postTweet(int userId, int tweetId) {

        tweetMap.putIfAbsent(userId, new ArrayList<>());

        tweetMap.get(userId).add(new Tweet(tweetId, time++));
    }

    public List<Integer> getNewsFeed(int userId) {

        PriorityQueue<Tweet> maxHeap =
                new PriorityQueue<>((a, b) -> b.time - a.time);

        followMap.putIfAbsent(userId, new HashSet<>());
        followMap.get(userId).add(userId);

        for (int followee : followMap.get(userId)) {

            List<Tweet> tweets =
                    tweetMap.getOrDefault(followee, new ArrayList<>());

            for (Tweet tweet : tweets) {
                maxHeap.offer(tweet);
            }
        }

        List<Integer> feed = new ArrayList<>();

        int count = 0;

        while (!maxHeap.isEmpty() && count < 10) {
            feed.add(maxHeap.poll().id);
            count++;
        }

        return feed;
    }

    public void follow(int followerId, int followeeId) {

        followMap.putIfAbsent(followerId, new HashSet<>());

        followMap.get(followerId).add(followeeId);
    }

    public void unfollow(int followerId, int followeeId) {

        if (followMap.containsKey(followerId)
                && followeeId != followerId) {

            followMap.get(followerId).remove(followeeId);
        }
    }
}