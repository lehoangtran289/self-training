package SkipList;

@SuppressWarnings("unchecked")
public class SkipList<K extends Comparable<K>, V> {
    public static final double DEFAULT_PROBABILITY = 0.5;
    public static final int MAX_LEVEL = 16; // If p = 1/2, using MaxLevel = 16
    private final double probability;

    // fields
    private Node<K, V> head;
    private int level; // cur level
    private int size;

    public SkipList() {
        this(DEFAULT_PROBABILITY);
    }

    public SkipList(double probability) {
        this.probability = probability;
        this.head = new Node<K, V>(null, null, 0);
        this.level = 0;
        this.size = 0;
    }

    private int getRandomLevel() {
        int lvl = 0;
        while (Math.random() < this.probability && lvl < MAX_LEVEL) {
            lvl++;
        }
        return lvl;
    }

    public V search(K searchKey) {
        Node<K, V> ptr = this.head;
        for (int i = level; i >= 0; i--) {
            while (ptr.forward[i] != null && searchKey.compareTo(ptr.forward[i].key()) > 0) {
                ptr = ptr.forward[i];
            }
        }
        ptr = ptr.forward[0];
        if (ptr != null && searchKey.compareTo(ptr.key()) == 0) {
            return ptr.value();
        } else {
            return null;
        }
    }

    public void insert(K key, V value) {
        Node<K, V>[] update = (Node<K, V>[]) new Node[MAX_LEVEL];
        Node<K, V> ptr = this.head;
        for (int i = level; i >= 0; i--) {
            while (ptr.forward[i] != null && key.compareTo(ptr.forward[i].key()) > 0) {
                ptr = ptr.forward[i];
            }
            update[i] = ptr;
        }
        ptr = ptr.forward[0];
        if (ptr != null && key.compareTo(ptr.key()) == 0) {
            ptr.setValue(value);
        } else {
            int newLevel = getRandomLevel();
            if (newLevel > level) {
                for (int i = level + 1; i <= newLevel; i++) {
                    update[i] = head;
                }
                level = newLevel;
            }
            ptr = new Node<>(key, value, newLevel);
            for (int i = 0; i <= newLevel; ++i) {
                ptr.forward[i] = update[i].forward[i];
                update[i].forward[i] = ptr;
            }
            this.size++;
        }
    }

    public void delete(K key) {
        Node<K, V>[] update = (Node<K, V>[]) new Node[MAX_LEVEL];
        Node<K, V> ptr = this.head;
        for (int i = level; i >= 0; --i) {
            while (ptr.forward[i] != null && key.compareTo(ptr.forward[i].key()) > 0) {
                ptr = ptr.forward[i];
            }
            update[i] = ptr;
        }
        ptr = ptr.forward[0];
        if (ptr != null && key.compareTo(ptr.key()) == 0) {
            for (int i = 0; i <= this.level; i++) {
                if (update[i].forward[i] != ptr) {
                    break;
                }
                update[i].forward[i] = ptr.forward[i];
            }
            ptr = null;
            while (this.level > 0 && this.head.forward[level] == null) {
                this.level--;
            }
        }
    }

    // =======================

    public void printSkipList() {
        System.out.println("\nSkip list ");
        for (int i = level; i >= 0; --i) {
            Node<K, V> ptr = head.forward[i];
            System.out.print("Level " + i + ": HEAD --> ");
            while (ptr != null) {
                System.out.print("(" + ptr.key() + ", " + ptr.value() + ") ");
                ptr = ptr.forward[i];
            }
            System.out.println();
        }
    }
}
