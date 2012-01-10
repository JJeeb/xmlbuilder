package xmlbuildr.xml;

import java.util.List;

public class Element implements Node {

    private final String name;
    private final List<Attribute> attributes;
    private final List<Element> children;
    private final String text;

    public Element(String name, List<Attribute> attributes, List<Element> children, String text) {
        this.attributes = attributes;
        this.name = name;
        this.children = children;
        this.text = text;
    }

    public Element(String author, List<Attribute> attributes, List<Element> elements) {
        this(author, attributes, elements, null);
    }

    public String getName() {
        return name;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public List<Element> getChildren() {
        return children;
    }

    public String getText() {
        return text;
    }

    public boolean hasText() {
        return text != null;
    }
}
