package xmlbuildr.xml;

import com.google.common.base.Predicate;

import java.util.List;

import static com.google.common.collect.Iterators.any;


public class Element implements Node {

    private final String name;
    private final List<? extends  Node> attributes;
    private final List<? extends Node> children;


    public Element(String name, List<? extends Node> attributes, List<? extends Node> children) {
        this.name = name;
        this.attributes = attributes;
        this.children = children;
    }

    public String getName() {
        return name;
    }

    public List<? extends Node> getAttributes() {
        return attributes;
    }

    public List<? extends Node> getChildren() {
        return children;
    }

    public boolean hasChildren() {
        return ! children.isEmpty();
    }

    public boolean hasTextChild() {
        return any(children.iterator(), isTextNode);
    }

    private static final Predicate<Node> isTextNode = new Predicate<Node>() {
        @Override
        public boolean apply(Node node) {
            return node instanceof TextNode;
        }
    };
}
