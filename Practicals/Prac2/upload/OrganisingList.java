/**
 * Name: Liam Burgess
 * Student Number: 18015001
 */

public class OrganisingList {

    public ListNode root = null;

    public OrganisingList() {
        
    }
    
    /**
     * Calculate the sum of keys recursively, starting with the given node until the end of the list
     * @return The sum of keys from the current node to the last node in the list
     * NOTE: 'int' and not 'Integer' here because it cannot return 'null'
     */
    public static int sumRec(ListNode node) {
        if (node.next==null){
            return node.key;
        }
        else {
            int sum = node.key + sumRec(node.next);
           // System.out.println("Sumrec: "+sum+'\n');
            return sum;
        }
    }

    /**
     * Calculate and set the data for the given node.
     * @return The calculated data for the given node
     * NOTE: 'int' and not 'Integer' here because it cannot return 'null'
     */
    public static int dataRec(ListNode node) {
        if(node.next==null) {
            node.data = node.key;
            return node.key;
        }
        else {
            node.data =(node.key * sumRec(node)) - dataRec(node.next);
            return node.data;
        }
    }

    /**
     * Calculate the data field of all nodes in the list using the recursive functions.
     * DO NOT MODIFY!
     */
    public void calculateData() {
        if (root != null) {
            dataRec(root);
        }
    }

    /**
     * Retrieve the data for the node with the specified key and swap the
     * accessed node with its predecessor, then recalculate data fields.
     * @return The data of the node before it has been moved,
     * otherwise 'null' if the key does not exist.
     */
    public Integer getData(Integer key) {
        ListNode curr = root;
        ListNode prev = null;
        ListNode prev2 = null;
        while(curr!=null) {
            if(curr.key==key) {
                if(prev==null) {
                    calculateData();
                    return curr.data;
                } //if
                else {
                    if(prev2!=null) {
                        prev2.next = curr;
                        prev.next = curr.next;
                        curr.next = prev;
                        int value = prev2.next.data; 
                        calculateData();
                        return value;
                    } //if
                    else {
                        prev.next = curr.next;
                        curr.next = prev;
                        root = curr;
                        calculateData();
                        return root.data;
                    } //if
                } //else    
            } //if
            else {
                if(prev==null) {
                    prev = curr;
                    curr = curr.next;
                } //if
                else {
                    prev2 = prev;
                    prev = curr;
                    curr = curr.next;
                } //if
            } //else
        } //while
        return null;
    }

    /**
     * Delete the node with the given key.
     * calculateData() should be called after deletion.
     * If the key does not exist, do nothing.
     */
    public void delete(Integer key) {
        ListNode curr =root;
        ListNode prev = null;
       // System.out.println("Delete\n");
        while(curr!=null){
            if(curr.key==key) {
                if(prev==null) {
                    root = root.next;
                    calculateData();
                    return;
                }
                else {
                    prev.next = curr.next;
                    calculateData();
                    return;
                }
            }
            else {
                prev = curr;
                curr = curr.next;
            }
        }
    }


    /**
     * Insert a new key into the linked list.
     * 
     * New nodes should be inserted to the back
     * Duplicate keys should not be added.
     */
    public void insert(Integer key) {
        ListNode curr = root;
        ListNode prev = null;
        ListNode newNode = new ListNode(key);
        
        if(root==null) {
            root = newNode;
            calculateData();
            return;
        }
        
        while(curr!=null){
            if(curr.key==key) {
                return;
            }
            prev = curr;
            curr=curr.next;
        }
        prev.next = newNode;
        calculateData();;
    }

    /**
     * Check if a key is contained in the list
     * @return true if the key is in the list, otherwise false
     */
    public Boolean contains(Integer key) {
        ListNode curr = root;
        while(curr!=null) {
            if(curr.key==key) {
                return true;
            }
            else curr = curr.next;
        }
        return false;
    }


    /**
     * Return a string representation of the Linked List.
     * DO NOT MODIFY!
     */
    public String toString() {
        if (root == null) {
            return "List is empty";
        }

        String result = "";
        for (ListNode node = root; node != null; node = node.next) {
            result = result.concat("[K: " + node.key + ", D: " + node.data + "]");

            if (node.next != null) {
                result = result.concat(" ");
            }
        }

        return result;
    }

    /**
     * Return a string representation of the linked list, showing only the keys of nodes.
     * DO NOT MODIFY!
     */
    public String toStringKeysOnly() {

        if (root == null) {
            return "List is empty";
        }

        String result = "";
        for (ListNode node = root; node != null; node = node.next) {
            result = result + node.key;

            if (node.next != null) {
                result = result.concat(", ");
            }
        }

        return result;
    }

    
}