package SkipList3;

class SkipList3<Key extends Comparable<? super Key>, E> implements Dictionary<Key, E> {
    private SkipNode3<Key, E> head;
    private int level;
    private int size;

    public SkipList3() {
        head = new SkipNode3<Key, E>(null, null, 0);
        level = -1;
        size = 0;
    }

    private void AdjustHead(int newLevel) {
        SkipNode3<Key, E> temp = head;
        head = new SkipNode3<Key, E>(null, null, newLevel);
        for (int i = 0; i <= level; i++)
            head.forward[i] = temp.forward[i];
        level = newLevel;
    }

    public void print() {
        SkipNode3<Key, E> temp = head;
        while (temp != null) {
            System.out.print(temp.element() + ": length is "
                    + temp.forward.length + ":");
            for (int i = 0; i < temp.forward.length; i++)
                if (temp.forward[i] == null)
                    System.out.print("null ");
                else
                    System.out.print(temp.forward[i].element() + " ");
            System.out.println();
            temp = temp.forward[0];
        }
        System.out.println();
    }

    public int size() {
        return size;
    }

    public E removeAny() {
        assert false : "removeAny not implemented";
        return null;
    }

    public E remove(Key k) {
        assert false : "remove not implemented";
        return null;
    }

    public void clear() {
        assert false : "clear not implemented";
    }

    /**
     * Pick a level using a geometric distribution
     */
    int randomLevel() {
        int lev;
        for (lev = 0; DSutil.random(2) == 0; lev++) ; // Do nothing
        return lev;
    }

    /**
     * Insert a record into the skiplist
     */
    public void insert(Key k, E newValue) {
        int newLevel = randomLevel();  // New node's level
        if (newLevel > level)          // If new node is deeper
            AdjustHead(newLevel);        //   adjust the header
        // Track end of level
        SkipNode3<Key, E>[] update =
                (SkipNode3<Key, E>[]) new SkipNode3[level + 1];
        SkipNode3<Key, E> x = head;        // Start at header node
        for (int i = level; i >= 0; i--) { // Find insert position
            while ((x.forward[i] != null) &&
                    (k.compareTo(x.forward[i].key()) > 0))
                x = x.forward[i];
            update[i] = x;               // Track end at level i
        }
        x = new SkipNode3<Key, E>(k, newValue, newLevel);
        for (int i = 0; i <= newLevel; i++) {      // Splice into list
            x.forward[i] = update[i].forward[i]; // Who x points to
            update[i].forward[i] = x;            // Who y points to
        }
        size++;                       // Increment dictionary size
    }

    /**
     * Skiplist Search
     */
    public E find(Key searchKey) {
        SkipNode3<Key, E> x = head;          // Dummy header node
        for (int i = level; i >= 0; i--)       // For each level...
            while ((x.forward[i] != null) && // go forward
                    (searchKey.compareTo(x.forward[i].key()) > 0))
                x = x.forward[i];              // Go one last step
        x = x.forward[0];  // Move to actual record, if it exists
        if ((x != null) && (searchKey.compareTo(x.key()) == 0))
            return x.element();              // Got it
        else return null;                  // Its not there
    }
}
