/**
 * Name:
 * Student Number:
 */

public class Main {

    public static void main(String[] args) {
        // NOTE: This is an incomplete test case used for demonstration.
        // For best results, you should write your own test cases.

        Tests.startTestCase("Graph 1");
        Vertex v1 = new Vertex(1);
        Vertex v2 = new Vertex(2);

        Edge e12 = new Edge(v1, v2);

        Edge[] edges = new Edge[]{e12};

        GraphSelectionSort graphSelSort = new GraphSelectionSort(edges);

        // Init
        Tests.assertEquals(false, graphSelSort.isSorted(), "isSorted() returns false if not finished sorting");
        Tests.assertEquals(true, Tests.arrayEquals(edges, graphSelSort.getEdges()), "getEdges() return original edges");

        // 1
        graphSelSort.doSortIteration();
        Tests.assertEquals(true, Tests.arrayEquals(new Edge[]{}, graphSelSort.getEdges()),
                "doSortIteration() removes vertex with lowest value from the graph");
        Tests.assertEquals(true, Tests.arrayEquals(new Integer[]{1}, graphSelSort.getSorted()),
                "doSortIteration() appends lowest vertex value to sorted array");

        // Incomplete...
        Tests. endTestCase();

        /** Expected output:
         * =========================
         * CASE: Graph 1
         * ...
         * _________________________
         * SUMMARY: PASS
	     * Passed 4/4 assertions
         */

         // TODO: Write your tests here...
    }
}