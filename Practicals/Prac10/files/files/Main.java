/**
 * Name:
 * Student Number:
 */

public class Main {

    public static void main(String[] args) {

        DynamicHashMap map = new DynamicHashMap(3, 3.0);
        System.out.println(map.toString());

        System.out.println("h(I) = " + map.hash("I"));
        map.put("I", 4);

        System.out.println("h(COS) = " + map.hash("COS"));
        map.put("COS", 216);
        System.out.println(map.toString());
        map.put("COS", 212);
        System.out.println(map.toString());

        System.out.println("h(KEY) = " + map.hash("KEY"));
        map.put("KEY", 50);
        System.out.println(map.toString());

        System.out.println("h(TREE) = " + map.hash("TREE"));
        map.put("TREE", 63);
        System.out.println(map.toString());

        map.put("A", 1);
        map.put("B", 2);
        map.put("C", 3);
        map.put("D", 4);
        map.put("E", 5);
        System.out.println(map.toString());

        map.put("F", 6);
        System.out.println(map.toString());

        System.out.println("h(COS) = " + map.hash("COS"));

        System.out.println(map.remove("COS"));

        System.out.println(map.toString());


        // Expected output:
        /**
            [][][]
            h(I) = 1
            h(COS) = 2
            [][4][216]
            [][4][212]
            h(KEY) = 0
            [50][4][212]
            h(TREE) = 0
            [50,63][4][212]
            [50,63,2,5][4,3][212,1,4]
            [63,2][4,3][4][50,5][6][212,1]
            h(COS) = 5
            212
            [63,2][4,3][4][50,5][6][1]
         */
    }

}