package SkipList;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;

import static SkipList.SkipList.MAX_LEVEL;

@Getter
@Setter
@SuppressWarnings("unchecked")
public class Node<K extends Comparable<K>, V> {
    private K key;
    private V value;
    public Node<K, V>[] forward;

    public K key() {
        return this.key;
    }

    public V value() {
        return this.value;
    }

    public Node(K key, V value, int level) {
        this.key = key;
        this.value = value;
        forward = (Node<K, V>[]) new Node[MAX_LEVEL];
        Arrays.fill(forward, null);
    }

    public void printForwards(int level) {
        for (int i = level; i >= 0; --i) {
            if (this.forward[i] == null) {
                System.out.println("\t- lv " + i + ": null");
            } else {
                System.out.println("\t- lv " + i + ": (" + forward[i].key() + ", " + forward[i].value() + ")");
            }
        }
    }
}
