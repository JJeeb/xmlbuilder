package xmlbuildr.writer;

import com.google.common.io.Resources;
import org.junit.Test;
import xmlbuildr.xml.Attribute;
import xmlbuildr.xml.Element;
import xmlbuildr.xml.Node;
import xmlbuildr.xml.TextNode;

import java.util.Collections;

import static com.google.common.base.Charsets.UTF_8;
import static com.google.common.io.Resources.getResource;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class WriterTest {

    private final Writer writer = new Writer();

    @Test
    public void writeEmptyElement() throws Exception {
        assertEquals("<author/>\n",
                writer.write(new Element("author",
                        Collections.<Attribute>emptyList(),
                        Collections.<Node>emptyList())));
    }

    @Test
    public void writeTextNode() throws Exception {
        assertEquals("<author>J K. Rowling</author>\n",
                writer.write(new Element("author",
                        Collections.<Attribute>emptyList(),
                        asList(new TextNode("J K. Rowling"))
                )));
    }

    @Test
    public void writeElementAttributes() throws Exception {
        assertEquals("<author id=\"12\" category=\"web\"/>\n",
                writer.write(new Element("author",
                        asList(new Attribute("id", "12"), new Attribute("category", "web")),
                        Collections.<Node>emptyList())));
    }

    @Test
    public void escapeEntitiesInAttributes() throws Exception {
        assertEquals("<author category=\"&lt;web&gt;\"/>\n",
                writer.write(new Element("author",
                        asList(new Attribute("category", "<web>")),
                        Collections.<Element>emptyList())));

        assertEquals("<author category=\"&amp;web&amp;\"/>\n",
                writer.write(new Element("author",
                        asList(new Attribute("category", "&web&")),
                        Collections.<Element>emptyList())));

        assertEquals("<author category=\"&apos;web&apos;\"/>\n",
                writer.write(new Element("author",
                        asList(new Attribute("category", "'web'")),
                        Collections.<Element>emptyList())));

        assertEquals("<author category=\"&aquot;web&aquot;\"/>\n",
                writer.write(new Element("author",
                        asList(new Attribute("category", "\"web\"")),
                        Collections.<Element>emptyList())));
    }

    @Test
    public void escapeEntitiesInText() throws Exception {
        assertEquals("<author>J K. &lt;Rowling&gt; &amp; Foo </author>\n",
                writer.write(new Element("author",
                        Collections.<Attribute>emptyList(),
                        asList(new TextNode("J K. <Rowling> & Foo "))
                )));
    }

    @Test
    public void writeNestedElement() throws Exception {
        assertEquals(Resources.toString(getResource(getClass(), "nestedElementsExpected.xml"), UTF_8),
                writer.write(
                        new Element("bookstore",
                                Collections.<Attribute>emptyList(),
                                asList(new Element("book",
                                        asList(new Attribute("id", "3"), new Attribute("category", "web")),
                                        asList(new Element("author",
                                                asList(new Attribute("id", "12")),
                                                Collections.<Node>emptyList()))),
                                        new Element("book",
                                                asList(new Attribute("id", "2"), new Attribute("category", "children")),
                                                asList(new Element("author",
                                                        asList(new Attribute("id", "14")),
                                                        asList(new TextNode("J K. Rowling")))))))));
    }
}
