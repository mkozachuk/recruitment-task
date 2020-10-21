import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class CompositeNode extends Node implements ICompositeNode {
    private List<INode> nodes = new LinkedList<>();

    public CompositeNode(String code, String renderer) {
        super(code, renderer);
    }

    public void addNode(INode node) {
        nodes.add(node);
    }

    @Override
    public List<INode> getNodes() {
        return Collections.unmodifiableList(nodes);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CompositeNode that = (CompositeNode) o;
        return nodes.equals(that.nodes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), nodes);
    }


}
