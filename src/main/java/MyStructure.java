import java.util.LinkedList;
import java.util.List;

public class MyStructure implements IMyStructure {
    private List<INode> nodes = new LinkedList<>();

    private List<INode> compositeNodes = new LinkedList<>();

    public INode findByCode(String code) {
        return search(code);
    }

    public INode findByRenderer(String renderer) {
        return search(renderer);
    }

    public int count() {
        int counter = 0;

        for (INode node : nodes) {
            if (isICompositeNode(node)) {
                counter++;
                counter += findAllInnerNodes((ICompositeNode) node).size();
            } else {
                counter++;
            }
        }

        return counter;
    }

    private List<INode> findAllInnerNodes(ICompositeNode node) {
        compositeNodes.addAll(node.getNodes());
        for (INode node1 : node.getNodes()) {
            if (isICompositeNode(node1)) {
                compositeNodes.addAll(((ICompositeNode) node1).getNodes());
            }
        }

        return compositeNodes;
    }

    private boolean isICompositeNode(INode node) {
        return node instanceof ICompositeNode;
    }

    private INode search(String codeOrRenderer) {
        if (codeOrRenderer == null) {
            throw new IllegalArgumentException();
        }
        for (INode node : nodes) {

            if (isICompositeNode(node)) {
                findAllInnerNodes((ICompositeNode) node);
            }
        }

        compositeNodes.addAll(nodes);
        for (INode node : compositeNodes) {
            if (node.getCode().equals(codeOrRenderer)) {
                return node;
            }
            if (node.getRenderer().equals(codeOrRenderer)) {
                return node;
            }
        }

        return null;
    }

}
