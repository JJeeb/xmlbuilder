package xmlbuildr.writer;

import org.junit.Test;
import xmlbuildr.xml.Attribute;
import xmlbuildr.xml.Element;

import java.util.Collections;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class WriterTest {

    private final Writer writer = new Writer();

    @Test
    public void writeEmptyElement() throws Exception {
        assertEquals("<author/>",
                writer.write(new Element("author",
                        Collections.<Attribute>emptyList(),
                        Collections.<Element>emptyList())));
    }

    @Test
    public void writeTextNode() throws Exception {
        assertEquals("<author>J K. Rowling</author>",
                writer.write(new Element("author",
                        Collections.<Attribute>emptyList(),
                        Collections.<Element>emptyList(),
                        "J K. Rowling")));
    }

    @Test
    public void writeElementAttributes() throws Exception {
        assertEquals("<author id=\"12\" category=\"web\"/>",
                writer.write(new Element("author",
                        asList(new Attribute("id", "12"), new Attribute("category", "web")),
                        Collections.<Element>emptyList())));
    }

    @Test
    public void writeNestedElement() throws Exception {
        assertEquals("<bookstore><book id=\"3\" category=\"web\"><author id=\"12\"/></book><book id=\"2\" category=\"children\"><author id=\"14\">J K. Rowling</author></book></bookstore>",
                writer.write(
                        new Element("bookstore",
                                Collections.<Attribute>emptyList(),
                                asList(new Element("book",
                                        asList(new Attribute("id", "3"), new Attribute("category", "web")),
                                        asList(new Element("author",
                                                asList(new Attribute("id", "12")),
                                                Collections.<Element>emptyList()))),
                                        new Element("book",
                                                asList(new Attribute("id", "2"), new Attribute("category", "children")),
                                                asList(new Element("author",
                                                        asList(new Attribute("id", "14")),
                                                        Collections.<Element>emptyList(),
                                                        "J K. Rowling")))))));
    }
}
