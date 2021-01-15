/**
 * Name: Liam Burgess
 * Student Number: 18015001
 */

/**
 * You may add your own classes and function but you may not modify any of
 * the given attribute names or given method signatures.
 */
public class Graph {
    Integer bounds[] = new Integer[3]; //level, row, collum
    Vertex Vertices[] = new Vertex[0];
    
    public Graph() {
    }

    /**
     * Create a new graph to represent the given maze.
     * 
     * See the specification provided on https://cs.up.ac.za/courses/COS212
     * @param maze
     */
    public void createGraphFrom3DMaze(Character[][][] maze) {
        bounds[0] = maze.length;
        bounds[1] = maze[0].length;
        bounds[2] = maze[0][0].length;
        
        for(int level=0; level<bounds[0]; level++) {
            for(int row=0; row<bounds[1]; row++) {
                for(int col=0; col<bounds[2]; col++) {
                    switch(maze[level][row][col]) {
                        case 'u': {
                            Vertex pass = new Vertex(level, row, col);
                            pass.setLadder(1);
                            AddVert(pass);
                            break;
                        }
                        case 'd': {
                            Vertex pass = new Vertex(level, row, col);
                            pass.setLadder(2);
                            AddVert(pass);
                            break;
                        }
                        case 'b': {
                            Vertex pass = new Vertex(level, row, col);
                            pass.setLadder(3);
                            AddVert(pass);
                            break;
                        }
                        case '.': {
                            Vertex pass = new Vertex(level, row, col);
                            AddVert(pass);
                        }
                    }
                }
            }
        }
        
        Vertex iter = Vertices[0];
        for(int i=0; i<Vertices.length; i++) {
            Vertex hold = Vertices[i];
            int lev= hold.coords.RetEle(1);
            int start=i;
            while(iter.coords.RetEle(1)==lev) {
                if(start-1<0)
                    break;
                start--;
                iter = Vertices[start];
            }
            
            int end=i;
            while(iter.coords.RetEle(1)==lev) {
                if(end+1==Vertices.length)
                    break;
                end++;
                iter = Vertices[end];
            }
            AddNeighbors(hold, start, end);
        }
    }

    /**
     * Return the vertex with the given coordinates (level, row, col)
     * If the vertex does not exist, return null.
     * If the coordinates are out of bounds, return null.
     * @param level
     * @param row
     * @param col
     * @return 
     */
    public Vertex getVertex(Integer level, Integer row, Integer col) {
        int iter=0;
        
        if(level>bounds[0] || row>bounds[1] || col>bounds[2]) {
            return null;
        }
        
        Vertex hold = Vertices[0];
        while(hold.coords.RetEle(1)!=level) {
            hold = Vertices[iter++];
        }
        
        while(iter<Vertices.length && hold.coords.RetEle(1)==level) {
            if(hold.coords.RetEle(2)==row && hold.coords.RetEle(3)==col)
                return hold; 
            else
                hold = Vertices[iter++];
        }
        return null;
    }

    /**
     * Return all the vertices in the graph.
     * The vertices in the returned array can be in any order.
     */
    public Vertex[] getAllVertices() {
        return Vertices;
    }

    /**
     * Return the vertices adjacent to the given vertex.
     * The vertices in the array can be in any order.
     * Return an empty array if there are no adjacent vertices.
     * If the vertex does not exist, return null.
     */
    public Vertex[] getAdjacentVertices(Vertex vertex) { //adjacent will now return an empty array if no neighbors
        for(int i=0; i<Vertices.length; i++)
            if(Vertices[i]==vertex)
                return Vertices[i].getAdj();
        return null;
    }
    
    public boolean Contains(Vertex v) {
        for(int i=0; i<Vertices.length; i++) {
            if(Vertices[i]==v)
                return true;
        }
        return false;
    }
    
    public void AddVert(Vertex v) {
        if(Contains(v)==true) {
            return;
        }
        Vertex hold[] = new Vertex[Vertices.length+1];
        System.arraycopy(Vertices, 0, hold, 0, Vertices.length);
        hold[Vertices.length] = v;
        Vertices = hold;
    }
    /*
    1=level
    2=row
    3=col
    */
    public void AddNeighbors(Vertex v, int start, int end) { //end is the last vertice on the level
        for(int i=start; i<end; i++) {
            Vertex comp = Vertices[i];
            if(v.coords.RetEle(2)-1==comp.coords.RetEle(2) && v.coords.RetEle(3)==comp.coords.RetEle(3))
                v.AddNeighbor(Vertices[i]);
            else if(v.coords.RetEle(2)+1==comp.coords.RetEle(2) && v.coords.RetEle(3)==comp.coords.RetEle(3)) 
                v.AddNeighbor(Vertices[i]);
            else if(v.coords.RetEle(3)-1==comp.coords.RetEle(3) && v.coords.RetEle(2)==comp.coords.RetEle(2)) 
                v.AddNeighbor(Vertices[i]);
            else if(v.coords.RetEle(3)+1==Vertices[i].coords.RetEle(3) && v.coords.RetEle(2)==comp.coords.RetEle(2)) 
                v.AddNeighbor(Vertices[i]);
        }
        
        if(v.GetLadder(1)==true) { //if true, they exist, no null check is necessary
            int i=end+1;
            while(Vertices[i].coords.RetEle(2)!=v.coords.RetEle(2) && Vertices[i].coords.RetEle(3)!=v.coords.RetEle(3))
                i++;
            v.AddNeighbor(Vertices[i]);
        }
        
        if(v.GetLadder(3)==true) {
            int i=start-1;
            while(Vertices[i].coords.RetEle(2)!=v.coords.RetEle(2) && Vertices[i].coords.RetEle(3)!=v.coords.RetEle(3))
                i--;
            v.AddNeighbor(Vertices[i]);
        }
    }
    
    

    /** 
     * =============================
     * ===        TASK 2         ===
     * =============================
     */

    /**
     * Return the length of the longest path.
     * The start and end vertices should be part of the path.
     * The length can be calculated by summing the weights of edges in the path.
     * For Task 2, each edge has an implicit weight of 1, so the path length is
     * the number of edges in the path.
     * If no path exists, return null
     * You may assume there will only be one longest path.
     */
    public Integer getLongestPathLength(Coordinates start, Coordinates end) {
        // TODO: Your code here...
        return null; // Stub line, you can safely remove when required
    }

    /**
     * Return an array of vertices that make up the longest path in order from start to end.
     * The starting vertex should be the first element, and the ending vertex should be the last element.
     * If there is no path, return an empty array.
     * If no vertex exists at the given coordinates, return null.
     * You may assume there will be only one longest path.
     */
    public Vertex[] getLongestPath(Coordinates start, Coordinates end) {
        // TODO: Your code here...
        return null; // Stub line, you can safely remove when required
    }

}
