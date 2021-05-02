package SkipList3;

public class SkipNode3<Key, E> {
    private final E element;
    private Key key;

    public SkipNode3<Key, E>[] forward;

    public E element() {
        return element;
    }

    public Key key() {
        return key;
    }

    public SkipNode3(Key k, E r, int level) {
        key = k;
        element = r;
        forward = (SkipNode3<Key, E>[]) new SkipNode3[level + 1];
        for (int i = 0; i < level; i++) {
            forward[i] = null;
        }
    }
}
