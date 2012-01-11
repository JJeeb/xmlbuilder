package xmlbuildr.dsl;

import com.google.common.io.Resources;
import org.junit.Test;

import static com.google.common.base.Charsets.UTF_8;
import static com.google.common.io.Resources.getResource;
import static org.junit.Assert.assertEquals;
import static xmlbuildr.dsl.Xml.*;

public class XmlTest {

    @Test
    public void xmlDsl() throws Exception {
        assertEquals(Resources.toString(getResource(getClass(), "books.xml"), UTF_8),
                xml(element("bookstore",
                        element("book", attr("category", "COOKING"),
                                element("title", attr("lang", "en"), text("Everyday Italian")),
                                element("author", text("Giada De Laurentiis")),
                                element("year", text("2005")),
                                element("price", text("30.00"))
                        ),
                        element("book", attr("category", "CHILDREN"),
                                element("title", attr("lang", "en"), text("Harry Potter")),
                                element("author", text("J K. Rowling")),
                                element("year", text("2005")),
                                element("price", text("29.99"))
                        ),
                        element("book", attr("category", "WEB"),
                                element("title", attr("lang", "en"), text("XQuery Kick Start")),
                                element("author", text("James McGovern")),
                                element("author", text("Per Bothner")),
                                element("author", text("Kurt Cagle")),
                                element("author", text("James Linn")),
                                element("author", text("Vaidyanathan Nagarajan")),
                                element("year", text("2003")),
                                element("price", text("49.99"))
                        ),
                        element("book", attr("category", "WEB"),
                                element("title", attr("lang", "en"), text("Learning XML")),
                                element("author", text("Erik T. Ray")),
                                element("year", text("2003")),
                                element("price", text("39.95"))
                        )
                )));
    }
}
