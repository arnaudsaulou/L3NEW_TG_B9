import java.util.Scanner;

public class L3NEW_TG_B9_Main {

    //region Variables

    private static Scanner keyboard;

    //endregion

    public static void main(String[] args) {

        keyboard = new Scanner(System.in);

        do {
            graphProcedure();
        } while (askUserIfWantsToContinue());

        System.out.println("A bientôt !");

    }

    private static void graphProcedure() {
        //Graph importation
        L3NEW_TG_B9_Graph graph = L3NEW_TG_B9_ImportGraph.importGraphProcedure();

        //Print the graph read
        System.out.print(graph);

        //Print adjacency matrix
        graph.displayAdjacencyMatrix();

        //Print value matrix
        graph.displayValuesMatrix();

        //Circuit detection
        graph.circuitDetection();
    }

    private static boolean askUserIfWantsToContinue() {
        System.out.print("Voulez-vous tester un autre graphe ? (O/N) : ");
        String answer = keyboard.nextLine();
        return answer.equals("O");
    }


}
