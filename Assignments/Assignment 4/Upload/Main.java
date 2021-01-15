/**
 * Name:
 * Student Number:
 */

public class Main {

    public static void main(String[] args) {
        Character [][][] map = new  Character[3][4][4];
        for(int k=0; k<3; k++) {
            for(int i=0; i<4; i++)
                for(int j=0; j<4; j++)
                    map[k][i][j] = 'x';
        }
            
        /*for(int i=1; i<3; i++) {
            map[0][i][1] = ' ';
            map[0][i][2] = ' ';
        }*/
        
        map[0][3][0] = '.';
        map[0][3][1] = 'u';
        map[0][2][1] = '.';
        map[0][2][2] = '.';
        map[0][1][2] = '.';
        map[0][1][3] = 'u';
        
        map[1][1][0] = '.';
        map[1][3][1] = 'b';
        map[1][1][2] = '.';
        map[1][1][3] = 'd';
        map[1][3][2] = '.';
        map[1][1][1] = '.';
        
        map[2][3][1] = 'd';
        
        
        
        Graph maze = new Graph();
        maze.createGraphFrom3DMaze(map);
        
        Vertex[] v = maze.getVertex(1, 3, 1).getAdj();
        
        for(Vertex hold: v)
            System.out.println(v.toString());
        
    }
}