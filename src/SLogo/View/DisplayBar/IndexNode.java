package SLogo.View.DisplayBar;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

/**
 * Container of an index-node pair, returns a view
 *
 * @author Alex
 */
public class IndexNode implements VisualCommand {
    private int myIndex;
    private Node myNode;
    private GridPane myRoot = new GridPane();

    /**
     * Creates new index node to put in a list somewhere
     *
     * @param index
     * @param node
     */
    public IndexNode(int index, Node node) {
        myIndex = index;
        Text myIndexText = new Text("" + myIndex);
        GridPane.setConstraints(myIndexText, 0, 0);
        myNode = node;
        GridPane.setConstraints(myNode, 1, 0);
        myRoot.getChildren().addAll(myIndexText, myNode);
    }

    /**
     * Gets node
     */
    @Override
    public Node getView() {
        return myRoot;
    }

    /**
     * Equals method
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof IndexNode)) {
            return true;
        }
        IndexNode indexNode = (IndexNode) object;
        return this.myIndex == indexNode.myIndex;
    }

    /**
     * Gets command representation
     */
    @Override
    public String getCommand() {
        return "" + myIndex;
    }
}
