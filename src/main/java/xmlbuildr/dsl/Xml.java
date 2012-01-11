package xmlbuildr.dsl;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import xmlbuildr.writer.Writer;
import xmlbuildr.xml.Attribute;
import xmlbuildr.xml.Element;
import xmlbuildr.xml.Node;
import xmlbuildr.xml.TextNode;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import static com.google.common.base.Predicates.not;
import static com.google.common.collect.Collections2.filter;
import static com.google.common.collect.Collections2.transform;
import static java.util.Arrays.asList;

public class Xml {

    public static Element element(String name, Node... nodes) {
        return new Element(name,
                new LinkedList<Node>(filter(asList(nodes), isAttribute)),
                new LinkedList<Node>(filter(asList(nodes), not(isAttribute))));
    }

    public static String xml(Element element) {
        return new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n")
                .append(new Writer().write(element)).toString();
    }

    public static Node attr(String name, String value) {
        return new Attribute(name, value);
    }

    public static Node text(String text) {
        return new TextNode(text);
    }

    public static <T> Element bean(T bean, Function<T, Element> transformation) {
        return transformation.apply(bean);
    }

    public static <T> Element beans(String parentName, List<T> beans, Function<T, Element> transformation) {
        return element(parentName, transform(beans, transformation));
    }

    private static Element element(String parentName, Collection<Element> elements) {
        return element(parentName, elements.toArray(new Node[elements.size()]));
    }


    private static final Predicate<Node> isAttribute = new Predicate<Node>() {
        @Override
        public boolean apply(Node node) {
            return node instanceof Attribute;
        }
    };
}
