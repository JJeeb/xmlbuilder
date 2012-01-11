package xmlbuildr.dsl;

import com.google.common.base.Function;
import com.google.common.io.Resources;
import org.junit.Test;
import xmlbuildr.beans.Author;
import xmlbuildr.beans.Book;
import xmlbuildr.xml.Element;

import java.util.List;

import static com.google.common.base.Charsets.UTF_8;
import static com.google.common.io.Resources.getResource;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static xmlbuildr.dsl.Xml.*;

public class XmlTest {

    @Test
    public void xmlDsl() throws Exception {
        assertEquals(Resources.toString(getResource(getClass(), "books.xml"), UTF_8),
                xml(element("bookstore",
                        element("book", attr("category", "COOKING"),
                                element("title", attr("lang", "en"), text("Everyday Italian")),
                                element("authors",
                                        element("author", text("Giada De Laurentiis"))),
                                element("year", text("2005")),
                                element("price", text("30.00"))
                        ),
                        element("book", attr("category", "CHILDREN"),
                                element("title", attr("lang", "en"), text("Harry Potter")),
                                element("authors",
                                        element("author", text("J K. Rowling"))),
                                element("year", text("2005")),
                                element("price", text("29.99"))
                        ),
                        element("book", attr("category", "WEB"),
                                element("title", attr("lang", "en"), text("XQuery Kick Start")),
                                element("authors",
                                        element("author", text("James McGovern")),
                                        element("author", text("Per Bothner")),
                                        element("author", text("Kurt Cagle")),
                                        element("author", text("James Linn")),
                                        element("author", text("Vaidyanathan Nagarajan"))),
                                element("year", text("2003")),
                                element("price", text("49.99"))
                        ),
                        element("book", attr("category", "WEB"),
                                element("title", attr("lang", "en"), text("Learning XML")),
                                element("authors",
                                        element("author", text("Erik T. Ray"))),
                                element("year", text("2003")),
                                element("price", text("39.95"))
                        )
                )));
    }

    @Test
    public void beanTransformation() throws Exception {

        final Function<Author, Element> authorToElement = new Function<Author, Element>() {

            @Override
            public Element apply(Author author) {
                return element("author", text(author.getName()));
            }
        };


        final Function<Book, Element> bookToElement = new Function<Book, Element>() {

            @Override
            public Element apply(Book book) {
                return element("book", attr("category", book.getCategory()),
                        element("title", attr("lang", book.getLang()), text(book.getTitle())),
                        beans("authors", book.getAuthors(), authorToElement),
                        element("year", text(book.getYear())),
                        element("price", text(book.getPrice()))
                );
            }
        };

        assertEquals(Resources.toString(getResource(getClass(), "books.xml"), UTF_8),
                xml(element("bookstore",
                        bean(new Book("Everyday Italian", "30.00", "2005", "en", "COOKING",
                                asList(new Author("Giada De Laurentiis"))),
                                bookToElement),

                        bean(new Book("Harry Potter", "29.99", "2005", "en", "CHILDREN",
                                asList(new Author("J K. Rowling"))),
                                bookToElement),

                        bean(new Book("XQuery Kick Start", "49.99", "2003", "en", "WEB",
                                asList(new Author("James McGovern"),
                                        new Author("Per Bothner"),
                                        new Author("Kurt Cagle"),
                                        new Author("James Linn"),
                                        new Author("Vaidyanathan Nagarajan"))),
                                bookToElement),

                        // can be mixed
                        element("book", attr("category", "WEB"),
                                element("title", attr("lang", "en"), text("Learning XML")),
                                element("authors",
                                        element("author", text("Erik T. Ray"))),
                                element("year", text("2003")),
                                element("price", text("39.95"))
                        )
                )));
    }

    @Test
    public void bunchOfbeanTransformation() throws Exception {

        final Function<Author, Element> authorToElement = new Function<Author, Element>() {

            @Override
            public Element apply(Author author) {
                return element("author", text(author.getName()));
            }
        };


        final Function<Book, Element> bookToElement = new Function<Book, Element>() {

            @Override
            public Element apply(Book book) {
                return element("book", attr("category", book.getCategory()),
                        element("title", attr("lang", book.getLang()), text(book.getTitle())),
                        beans("authors", book.getAuthors(), authorToElement),
                        element("year", text(book.getYear())),
                        element("price", text(book.getPrice()))
                );
            }
        };


        final List<Book> books = asList(
                new Book("Everyday Italian", "30.00", "2005", "en", "COOKING",
                        asList(new Author("Giada De Laurentiis"))),

                new Book("Harry Potter", "29.99", "2005", "en", "CHILDREN",
                        asList(new Author("J K. Rowling"))),

                new Book("XQuery Kick Start", "49.99", "2003", "en", "WEB",
                        asList(new Author("James McGovern"),
                                new Author("Per Bothner"),
                                new Author("Kurt Cagle"),
                                new Author("James Linn"),
                                new Author("Vaidyanathan Nagarajan"))),

                new Book("Learning XML", "39.95", "2003", "en", "WEB",
                        asList(new Author("Erik T. Ray"))));

        assertEquals(Resources.toString(getResource(getClass(), "books.xml"), UTF_8), 
                xml(beans("bookstore", books, bookToElement)));
    }
}
