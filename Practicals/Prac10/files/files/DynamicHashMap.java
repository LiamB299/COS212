/**
 * Name:
 * Student Number:
 */

public class DynamicHashMap {

    /**
     * This interface is partly based on Java's HashMap:
     * https://docs.oracle.com/javase/8/docs/api/java/util/HashMap.html
     */

    /**
     * Create a new empty hash map
     * @param tSize - the number of cells in the table
     *      or the maximum number of chain that can be in the table
     * @param loadFactor - Value to determine when to double the table
     *      size and rehash all entries. The loadFactor is defined
     *      as the average chain length.
     *      If the average chain length exceeds the loadFactor
     *      The table size should be doubled, and rehashing done.
     *      The loadFactor given here stays constant.
     */
    public DynamicHashMap(int tSize, Double loadFactor) {
       // TODO: your code here...
    }

    /**
     * Calculate and return the hash of the given key.
     * The input key should be interpreted as an ASCII string.
     * 
     * The hash should be calculated by XORing all the characters of the string
     * Example: h(abcd) = (a XOR b XOR c XOR d) mod TSize
     *          h(a)    = (a) mod TSize
     * 
     * NOTE: This hash function limits the size of the table to 127.
     * This can be fixed by XORing chunks for characters.
     * See section 10.1.2 in the textbook
     * 
     * For information on the XOR operator:
     * https://en.wikipedia.org/wiki/Exclusive_or
     * 
     * For information on ASCII:
     * https://en.wikipedia.org/wiki/ASCII
     */
    public int hash(String key) {
        // TODO: your code here...
    }

    /**
     * Return the value associated with the key. If no value is associated, return null.
     */
    public Integer get(String key) {
       // TODO: your code here...
    }

    /**
     * Set the value asociated with the key.
     * If the average chain length is greater than the given loadFactor,
     * the table size should be doubled and all key-values should be reinserted.
     * The average chain length should be calculated after inserting.
     * 
     * Return the previous value or null if no value was associated with the key.
     */
    public Integer put(String key, Integer value) {
       // TODO: your code here...
    }

    /**
     * Remove the value associated with the key.
     * 
     * Return the value associated prior to removal or null
     * if no value was associated.
     */
    public Integer remove(String key) {
        // TODO: your code here...
    }

    /**
     * Return the number of cells in the table.
     * This is also equal to the maximum amount of chains that can be in the table.
     */
    public int tableSize() {
        return capacity;
    }

    /**
     * Return an array of values stored in a chain.
     * If there are no values in the chain, return an empty array.
     * 
     * Example:
     * A DynamicHashMap of size 5 is created:
     * 0: []
     * 1: []
     * 2: []
     * 3: []
     * 4: []
     * 
     * where 0 to 4 represent the hashes, and [] represents an empty chain.
     * Inserting the value 77 and 88 that are both associated with *distinct* keys
     * that hash to 3
     * 0: []
     * 1: []
     * 2: []
     * 3: [77, 88]
     * 4: []
     * 
     * chain(0) should return an empty array.
     * chain(3) should return an array containing 77 and 88
     * 
     * Please see the given main for usage examples.
     */
    public Integer[] chain(int index) {
        // TODO: your code here...
    }

    /**
     * DO NOT MODIFY!
     */
    public String chainToString(Integer[] chain) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        for (int i = 0; i < chain.length; i++) {
            sb.append(chain[i]);
            if (i + 1 != chain.length) {
                sb.append(",");
            }
        }

        sb.append("]");

        return sb.toString();
    }

    /**
     * DO NOT MODIFY!
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tableSize(); i++) {
            sb.append(chainToString(chain(i)));  
        }

        return sb.toString();
    }
}