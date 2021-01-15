/**
 * Name:
 * Student Number:
 */

public class Vertex {

    int val;
    public Vertex(Integer value) {
       val = value;
    }

    /**
     * Return the value of the vertex
     * @return 
     */
    public Integer getValue() {
        return val;
    }
    
    @Override
    public String toString() {
        return String.valueOf(val);
    }
}