/**
 * Name:
 * 
 * Student number:
 */

public class SplayTree<T extends Comparable<T>> {

    public TreeNode<T> root;

    public SplayTree() {
        // Your code here...
    }

    /**
     * Returns true if the key exists in the tree, otherwise false
     */
    public Boolean contains(T key) {
        // Your code here...
    }

    /**
     * Insert the given key into the tree.
     * Duplicate keys should be ignored.
     * No Splaying should take place.
     */
    public void insert(T key) {
        // Your code here...
    }

    /**
     * Return the successor of the given key.
     * If the given key does not exist return null.
     * If the given key does not have a successor, return null.
     */
    public T findSuccessor(T key) {
        // Your code here...
    }

    /**
     * Move the accessed key closer to the root using the semi-splaying strategy.
     * If the key does not exist, insert it without splaying
     */
    public void access(T key) {
        // Your code here...
    }
}