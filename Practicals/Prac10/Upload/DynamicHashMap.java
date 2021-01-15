/**
 * Name: Liam Burgess
 * Student Number: 18015001
 */

//the hash, with a given key, generates the index for which the value will be stored
//a linked list at each position exists
//if the position is taken, increase the linked list 

class node {
    Integer value;
    String key;
    node next, prev;
    public node(Integer val, String Key) {
        key = Key;
        value = val;
        next = prev = null;
    }
}

public class DynamicHashMap {
    int capacity;
    Double loadFactor;
    node table[];
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
       capacity = tSize;
       this.loadFactor = loadFactor;
       table = new node[capacity];
    }
    
    public Integer Chunk(String key) { //creates an ascii representation of key
        StringBuilder hold = new StringBuilder();
        byte conv[] = key.getBytes();
            for(byte move: conv) {
                hold.append(move);
            }
        return Integer.parseInt(hold.toString());
    }
    
    public Integer SmallHash(String key) {
        if(key.length()==1)
            return Chunk(key);
        else {
            Integer ihold= Chunk(key.substring(0,1));
            for(int i=1; i<key.length(); i++) {
                ihold = ihold^Chunk(key.substring(i,i+1));
            }
            return ihold;
        }
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
     * @param key
     */
    public int hash(String key) {
        int ret = -1;
        
        /*if(key.length()<=4) {
            Integer ihold = SmallHash(key);
            ret =  ihold % capacity;
            return ret;
        }
        else {
            ret =0;
            while(key.length()!=0) {
                int comp=0, ileft, iright;
                String left, right;
                    left = key.substring(0,3);
                    key = key.substring(4, key.length()-1);
                    
                    if(key.length()<4)
                        right = key;
                    else
                        right = key.substring(0,3);
                    ileft = Chunk(left);
                    iright = Chunk(right);
                    comp = ileft^iright;
                    
                if(key.length()>4) {
                    left = key.substring(0,3);
                    key = key.substring(4, key.length()-1);
                    
                    ileft = Chunk(left);
                    comp = comp^ileft;
                }
                else {
                    int x = Chunk(key);
                    comp = comp^x;
                }
            }
            ret = ret % capacity;
            return ret;
        }*/
        
        ret = SmallHash(key);
        ret = ret % capacity;
        return ret;
    }

    /**
     * Return the value associated with the key. If no value is associated, return null.
     */
    public Integer get(String key) {
       Integer index  = hash(key);
       node curr = table[index];
       
       while(curr!=null) {
           if(curr.key==key)
               return curr.value;
           else
               curr = curr.next;
       }
       return null;
    }
    
    public int CountChain(node curr) {
        int tot=0;
        while(curr!=null) {
            tot++;
            curr = curr.next;
        }
        return tot;
    }

    /**
     * Set the value asociated with the key.
     * If the average chain length is greater than the given loadFactor,
     * the table size should be doubled and all key-values should be reinserted.
     * The average chain length should be calculated after inserting.
     * 
     * Return the previous value or null if no value was associated with the key.
     * @param key
     * @param value
     * @return 
     */
    public Integer put(String key, Integer value) {
       Integer index = hash(key);
       node curr = table[index];
        if(curr==null) {
           table[index] = new node(value, key);
        }
        else {
            while(curr.next!=null) {
                if(curr.key==key) {
                    break;
                }
                curr = curr.next;
            }
            if(curr.key==key)
                curr.value=value;
            else {
                curr.next = new node(value, key);
                curr.next.prev = curr;
            }
        }
        double itotal = 0;
        for(node pass : table) {
            itotal += CountChain(pass);
        }
        itotal = itotal/capacity;
        
        if(itotal>loadFactor) {
            DynamicHashMap hold = new DynamicHashMap(capacity*2, loadFactor);
            for(node pass: table) {
                curr = pass;
                while(curr!=null) {
                    hold.put(curr.key, curr.value);
                    curr = curr.next;
                }
            }
            this.table = hold.table;
            capacity = capacity*2;
        }
        
        for(node iter: table) {
            node hold=iter;
            while(hold!=null) {
                if(hold.value==value) {
                    if(hold.prev==null) {
                        return null;
                    }
                    else {
                        return hold.prev.value;
                    }
                }
                hold = hold.next;
            }
        }
        return null;
    }

    /**
     * Remove the value associated with the key.
     * 
     * Return the value associated prior to removal or null
     * if no value was associated.
     */
    public Integer remove(String key) {
        Integer index = hash(key);
        node curr = table[index];
        if(curr==null) {
            return null;
        }
        else {
            while(curr.next!=null) {
                if(curr.key==key) {
                    Integer hold = curr.value;
                    if(table[index]==curr) {
                        table[index] = table[index].next;
                        if(table[index]!=null)
                            table[index].prev =null;
                    }
                    else {
                        if(curr.next==null)
                            curr.prev.next=null;
                        else {
                            curr.next.prev = curr.prev;
                            curr.prev = curr.next;
                        }
                    }
                    return hold;
                }
                curr = curr.next;
            }
            return null;
        }    
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
        int itot=0;
        node curr = table[index];
        
        if(table[index]==null)
            return new Integer[0];
        
        while(curr!=null) {
            itot++;
            curr = curr.next;
        }
        
        Integer hold[] = new Integer[itot];
        curr = table[index];
        itot =0;
        while(curr!=null) {
            hold[itot++] = curr.value;
            curr = curr.next;
        }
        return hold;
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