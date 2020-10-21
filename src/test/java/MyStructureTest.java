import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MyStructureTest {

    private static final Node NODE1 = new Node("code1", "renderer1");
    private static final Node NODE2 = new Node("code2", "renderer2");
    private static final Node NODE3 = new Node("code3", "renderer3");
    private static final Node NODE4 = new Node("code4", "renderer4");
    private static final Node NODE5 = new Node("code5", "renderer5");
    private static final CompositeNode COMPOSITE_NODE6 = new CompositeNode("code6", "renderer6");
    private static final CompositeNode COMPOSITE_NODE7 = new CompositeNode("code7", "renderer7");

    private MyStructure emptyStructure;
    private MyStructure nestedStructure;

    @BeforeAll
    static void setUpCompositeNodes() {
        COMPOSITE_NODE6.addNode(NODE3);
        COMPOSITE_NODE6.addNode(COMPOSITE_NODE7);
        COMPOSITE_NODE7.addNode(NODE4);
        COMPOSITE_NODE7.addNode(NODE5);
    }

    @BeforeEach
    void setUp() {
        emptyStructure = new MyStructure();

        nestedStructure = new MyStructure();
        nestedStructure.addNode(NODE1);
        nestedStructure.addNode(NODE2);
        nestedStructure.addNode(COMPOSITE_NODE6);
    }

    @Test
    void shouldBeAbleToInstantiateClass() {
        assertThat(emptyStructure, notNullValue());
    }

    @Test
    void shouldReturnNullWhenNotFoundByCodeAndStructureIsEmpty() {
        assertThat(emptyStructure.findByCode("12345"), is(nullValue()));
    }

    @Test
    void shouldReturnNullWhenNotFoundByRendererAndStructureIsEmpty() {
        assertThat(emptyStructure.findByRenderer("12345"), is(nullValue()));
    }

    @Test
    void shouldReturnZeroWhenStructureIsEmpty() {
        assertThat(emptyStructure.count(), is(equalTo(0)));
    }

    @Test
    void shouldReturnNullWhenNotFoundByCode() {
        assertThat(nestedStructure.findByCode("thereIsNoCode"), is(nullValue()));
    }

    @Test
    void shouldReturnNullWhenNotFoundByRenderer() {
        assertThat(nestedStructure.findByRenderer("thereIsNoRenderer"), is(nullValue()));
    }

    @Test
    void shouldReturnNodeFoundByCode() {
        assertThat(nestedStructure.findByCode("code2"), is(NODE2));
    }

    @Test
    void shouldReturnNodeFoundByRenderer() {
        assertThat(nestedStructure.findByRenderer("renderer2"), CoreMatchers.<INode>is(NODE2));
    }

    @Test
    void shouldThrowExceptionWhenCodeIsNull() {
        assertThrows(IllegalArgumentException.class, () -> emptyStructure.findByCode(null));
    }

    @Test
    void shouldThrowExceptionWhenRendererIsNull() {
        assertThrows(IllegalArgumentException.class, () -> emptyStructure.findByRenderer(null));
    }

    @Test
    void shouldFindCompositeNodeByCode() {
        assertThat(nestedStructure.findByCode("code6"), is(COMPOSITE_NODE6));
    }

    @Test
    void shouldFindCompositeNodeByRenderer() {
        assertThat(nestedStructure.findByRenderer("renderer6"), is(COMPOSITE_NODE6));
    }

    @Test
    void shouldFindNestedNodeByCode() {
        assertThat(nestedStructure.findByCode("code4"), is(NODE4));
    }

    @Test
    void shouldFindNestedNodeByRenderer() {
        assertThat(nestedStructure.findByRenderer("renderer4"), is(NODE4));
    }

    @Test
    void shouldFindDoubleNestedNodeByCode() {
        assertThat(nestedStructure.findByCode("code5"), is(NODE5));
    }

    @Test
    void shouldFindDoubleNestedNodeByRenderer() {
        assertThat(nestedStructure.findByRenderer("renderer5"), is(NODE5));
    }

    @Test
    void shouldCountNodesWithNestedStructure() {
        assertThat(nestedStructure.count(), is(7));
    }
}