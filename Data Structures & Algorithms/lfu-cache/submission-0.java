class LFUCache {
    class Node {
        int key, value, freq;
        Node prev, next;
        Node(int k, int v) {
            key = k;
            value = v;
            freq = 1;
        }
    }

    class DLL {
        Node head, tail;
        int size;

        DLL() {
            head = new Node(0, 0);
            tail = new Node(0, 0);
            head.next = tail;
            tail.prev = head;
        }

        void add(Node node) {
            node.next = head.next;
            node.prev = head;
            head.next.prev = node;
            head.next = node;
            size++;
        }

        void remove(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            size--;
        }

        Node removeLast() {
            if (size > 0) {
                Node node = tail.prev;
                remove(node);
                return node;
            }
            return null;
        }
    }

    int capacity, minFreq;
    Map<Integer, Node> map;
    Map<Integer, DLL> freqMap;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        freqMap = new HashMap<>();
    }

    public int get(int key) {
        if (!map.containsKey(key)) return -1;

        Node node = map.get(key);
        update(node);
        return node.value;
    }

    public void put(int key, int value) {
        if (capacity == 0) return;

        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.value = value;
            update(node);
        } else {
            if (map.size() == capacity) {
                DLL minList = freqMap.get(minFreq);
                Node removed = minList.removeLast();
                map.remove(removed.key);
            }

            Node node = new Node(key, value);
            map.put(key, node);
            minFreq = 1;

            freqMap.putIfAbsent(1, new DLL());
            freqMap.get(1).add(node);
        }
    }

    private void update(Node node) {
        int freq = node.freq;
        DLL list = freqMap.get(freq);
        list.remove(node);

        if (freq == minFreq && list.size == 0) {
            minFreq++;
        }

        node.freq++;
        freqMap.putIfAbsent(node.freq, new DLL());
        freqMap.get(node.freq).add(node);
    }
}