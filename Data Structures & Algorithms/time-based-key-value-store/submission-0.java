class TimeMap {

    HashMap<String, List<Pair>> map;

    class Pair {
        int time;
        String value;

        Pair(int time, String value){
            this.time = time;
            this.value = value;
        }
    }

    public TimeMap() {
        map = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        map.putIfAbsent(key, new ArrayList<>());
        map.get(key).add(new Pair(timestamp, value));
    }

    public String get(String key, int timestamp) {
        if(!map.containsKey(key)) return "";

        List<Pair> list = map.get(key);

        int left = 0, right = list.size() - 1;
        String res = "";

        while(left <= right){
            int mid = left + (right - left) / 2;

            if(list.get(mid).time <= timestamp){
                res = list.get(mid).value;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return res;
    }
}