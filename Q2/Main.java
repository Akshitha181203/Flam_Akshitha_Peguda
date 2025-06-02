class MyHashMap {
    static class Node {
        int key, value;
        Node next;

        Node(int k, int v) {
            key = k;
            value = v;
        }
    }

    private final int SIZE = 10000;
    private Node[] buckets;

    public MyHashMap() {
        buckets = new Node[SIZE];
    }

    private int getIndex(int key) {
        return key % SIZE;
    }

    private Node find(Node head, int key) {
        Node prev = head;
        Node curr = head.next;
        while (curr != null && curr.key != key) {
            prev = curr;
            curr = curr.next;
        }
        return prev;
    }

    public void put(int key, int value) {
        int index = getIndex(key);

        if (buckets[index] == null) {
            buckets[index] = new Node(-1, -1);
        }

        Node prev = find(buckets[index], key);
        if (prev.next == null) {
            prev.next = new Node(key, value);
        } else {
            prev.next.value = value;
        }
    }

    public int get(int key) {
        int index = getIndex(key);
        if (buckets[index] == null)
            return -1;

        Node prev = find(buckets[index], key);
        if (prev.next == null)
            return -1;
        return prev.next.value;
    }

    public void remove(int key) {
        int index = getIndex(key);
        if (buckets[index] == null)
            return;

        Node prev = find(buckets[index], key);
        if (prev.next != null) {
            prev.next = prev.next.next;
        }
    }

    public void print() {
        System.out.println("HashMap Contents:");
        for (int i = 0; i < SIZE; i++) {
            Node head = buckets[i];
            if (head != null) {
                Node curr = head.next;
                while (curr != null) {
                    System.out.println("Key: " + curr.key + ", Value: " + curr.value);
                    curr = curr.next;
                }
            }
        }
        System.out.println();
    }

}

public class Main {
    public static void main(String[] args) {
        MyHashMap map = new MyHashMap();
        map.put(1, 10);
        map.print();
        map.put(2, 20);
        map.print();
        System.out.println("Get 1: " + map.get(1));
        map.print();
        System.out.println("Get 3: " + map.get(3));
        map.put(2, 30);
        map.print();
        System.out.println("Get 2: " + map.get(2));
        map.remove(2);
        map.print();
        System.out.println("Get 2 after remove: " + map.get(2));
    }
}