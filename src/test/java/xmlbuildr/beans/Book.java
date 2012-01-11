package xmlbuildr.beans;

import java.util.List;

public class Book {
    
    private final String title;
    private final String year;
    private final String price;
    private final String lang;
    private final String category;

    private final List<Author> authors;


    public Book(String title, String price, String year, String lang, String category, List<Author> authors) {
        this.price = price;
        this.title = title;
        this.year = year;
        this.lang = lang;
        this.authors = authors;
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public String getPrice() {
        return price;
    }

    public String getLang() {
        return lang;
    }

    public String getCategory() {
        return category;
    }

    public List<Author> getAuthors() {
        return authors;
    }
}
