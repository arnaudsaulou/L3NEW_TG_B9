public class L3NEW_TG_B9_DirectedEdge {

    //region Variables

    private L3NEW_TG_B9_Node originNode;
    private L3NEW_TG_B9_Node destinationNode;
    private int weight;

    //endregion

    //region Constructor

    public L3NEW_TG_B9_DirectedEdge(L3NEW_TG_B9_Node originNode, L3NEW_TG_B9_Node destinationNode, int weight) {
        this.originNode = originNode;
        this.destinationNode = destinationNode;
        this.weight = weight;
    }

    //endregion

    //region Getter

    public L3NEW_TG_B9_Node getOriginNode() {
        return originNode;
    }

    public L3NEW_TG_B9_Node getDestinationNode() {
        return destinationNode;
    }

    public int getWeight() {
        return weight;
    }

    //endregion

    //region Setter

    public void setOriginNode(L3NEW_TG_B9_Node originNode) {
        this.originNode = originNode;
    }

    public void setDestinationNode(L3NEW_TG_B9_Node destinationNode) {
        this.destinationNode = destinationNode;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    //endregion

    //region Override

    @Override
    public String toString() {
        return  String.format("%4s", originNode.getLabel()) +
                String.format("%6s", "==")+
                String.format("%4d", weight)+
                String.format("%6s", "==>")+
                String.format("%4s", originNode.getLabel());
    }

    //endregion
}
