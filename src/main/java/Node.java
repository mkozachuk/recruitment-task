import java.util.Objects;

public class Node implements INode {

    private final String code;
    private final String renderer;

    public Node(String code, String renderer) {
        this.code = code;
        this.renderer = renderer;
    }

    public String getCode() {
        return code;
    }

    public String getRenderer() {
        return renderer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return code.equals(node.code) &&
                renderer.equals(node.renderer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, renderer);
    }
}
