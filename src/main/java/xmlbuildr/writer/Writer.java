package xmlbuildr.writer;

import xmlbuildr.xml.Attribute;
import xmlbuildr.xml.Element;
import xmlbuildr.xml.Node;
import xmlbuildr.xml.TextNode;

public class Writer {

    public String write(Element element) {
        return writeElement(element, 0).toString();
    }

    private StringBuilder writeElement(Element element, int depth) {
        StringBuilder xml = new StringBuilder(indent(depth)).append("<").append(element.getName());

        if (!element.getAttributes().isEmpty()) {
            for (Node node : element.getAttributes()) {
                Attribute attribute = (Attribute) node;
                xml.append(" ").append(attribute.getName()).append("=\"").append(escape(attribute.getValue())).append("\"");
            }
        }

        if (element.hasChildren()) {
            xml.append(">");

            boolean hasTextChild = element.hasTextChild();
            if (! hasTextChild) {
                xml.append("\n");
            }

            for (Node child : element.getChildren()) {
                xml.append(write(child, depth + 1));
            }

            if (! hasTextChild) {
                xml.append(indent(depth));
            }

            xml.append("</").append(element.getName()).append(">\n");
        } else {
            xml.append("/>\n");
        }

        return xml;
    }

    private String escape(String value) {
        return value.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("'", "&apos;")
                .replace("\"", "&aquot;");
    }


    private StringBuilder write(Node child, int i) {
        StringBuilder xml = new StringBuilder();

        if (child instanceof TextNode) {
            xml.append(escape(((TextNode) child).getText()));
        }

        if (child instanceof Element) {
            xml.append(writeElement((Element) child, i));
        }

        return xml;
    }

    private StringBuilder indent(int depth) {
        StringBuilder spaces = new StringBuilder();
        for (int i = 0; i < 4 * depth; i++) {
            spaces.append(" ");
        }
        return spaces;
    }

}
