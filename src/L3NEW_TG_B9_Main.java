public class L3NEW_TG_B9_Main {

    private static L3NEW_TG_B9_Graph graph;

    public static void main(String[] args) {

        //Graph importation
        graph = L3NEW_TG_B9_ImportGraph.importGraphProcedure();

        //Print the graph read
        System.out.print(graph);

        //Print adjacency matrix
        graph.displayAdjacencyMatrix();

        //Print value matrix
        graph.displayValuesMatrix();

        //Circuit detection
        graph.circuitDetection();

    }
}
