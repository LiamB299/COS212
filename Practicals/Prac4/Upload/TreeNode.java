/**
 * Name: Liam Burgess
 * 
 * Student Number: u18015001
 */
public class TreeNode<T extends Comparable<T>> {
    public T key;
    public TreeNode<T> left;
    public TreeNode<T> right;
    public TreeNode<T> parent = null;

    public TreeNode(T key) {
        // Your Code here...
        this.key = key;
        left = right = null;
    }

    /**
     * Return a string representation of the TreeNode
     */
    public String toString() {
        String left = this.left == null ? "null" : this.left.key.toString();
        String right = this.right == null ? "null" : this.right.key.toString();

        return "[" + this.key + ", L:" + left + ", R:" + right + "]";   
    }
}