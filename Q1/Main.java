import java.util.*;

class LRUCache{
    class Node {
        int key, value;
        Node prev, next;

        Node(int k, int v) {
            key = k;
            value = v;
        }
    }

    private int capacity;
    private Map<Integer, Node> cache;
    private Node head, tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        cache = new HashMap<>();
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            remove(node);
            insertAtFront(node);
            return node.value;
        }
        return -1;
    }

    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            remove(cache.get(key));
        }
        if (cache.size() == capacity) {
            remove(tail.prev);
        }
        Node newNode = new Node(key, value);
        insertAtFront(newNode);
    }

    private void remove(Node node) {
        cache.remove(node.key);
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void insertAtFront(Node node) {
        cache.put(node.key, node);
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    public void printCache() {
        System.out.print("Cache: ");
        Node curr = head.next;
        while (curr != tail) {
            System.out.print("[" + curr.key + ":" + curr.value + "] ");
            curr = curr.next;
        }
        System.out.println();
    }

}

class Main {
    public static void main(String[] args) {
        LRUCache lru = new LRUCache(2);
        lru.put(1, 1);
        lru.printCache();
        lru.put(2, 2);
        lru.printCache();
        System.out.println("Get 1: " + lru.get(1));
        lru.printCache();
        lru.put(3, 3);
        lru.printCache();
        System.out.println("Get 2: " + lru.get(2));
        lru.put(4, 4);
        lru.printCache();
        System.out.println("Get 1: " + lru.get(1));
        System.out.println("Get 3: " + lru.get(3));
        System.out.println("Get 4: " + lru.get(4));
        lru.printCache();
    }
}
