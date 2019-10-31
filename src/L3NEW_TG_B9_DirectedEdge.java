public class L3NEW_TG_B9_DirectedEdge {

    private L3NEW_TG_B9_Node originNode;
    private L3NEW_TG_B9_Node destinationNode;
    private int weight;

    public L3NEW_TG_B9_DirectedEdge(L3NEW_TG_B9_Node originNode, L3NEW_TG_B9_Node destinationNode, int weight) {
        this.originNode = originNode;
        this.destinationNode = destinationNode;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return originNode.getLabel() + " == " + weight + " ==> " + destinationNode.getLabel();
    }
}
