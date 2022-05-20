package com.epam.library.entity.dto;

import com.epam.library.entity.Author;
import com.epam.library.entity.Genre;
import com.epam.library.entity.WishBook;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * Class {@link BookDto} is an entity that represents the ability to compose multiple 'Book', 'Author', 'Genre' table entities.
 *
 * @author Alexander Pishchala
 *
 */

public class BookDto implements Serializable {

    private static final long serialVersionUID = -1306289623887816054L;

    private long bookDtoId;
    private String cityLibrary;
    private List<Author> authors;
    private List<Genre> genres;
    private String title;
    private int quantity;
    private int borrow;
    private String publisher;
    private String description;
    private String year;
    private LocalDate added;
    private String isbn;
    private String shelf;


    public BookDto() {
    }

    public BookDto(String title, String isbn) {
        this.title = title;
        this.isbn = isbn;
    }

    public long getBookDtoId() {
        return bookDtoId;
    }

    public void setBookDtoId(long bookDtoId) {
        this.bookDtoId = bookDtoId;
    }

    public String getCityLibrary() {
        return cityLibrary;
    }

    public void setCityLibrary(String cityLibrary) {
        this.cityLibrary = cityLibrary;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getBorrow() {
        return borrow;
    }

    public void setBorrow(int borrow) {
        this.borrow = borrow;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public LocalDate getAdded() {
        return added;
    }

    public void setAdded(LocalDate added) {
        this.added = added;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getShelf() {
        return shelf;
    }

    public void setShelf(String shelf) {
        this.shelf = shelf;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        BookDto bookDto = (BookDto) obj;

        if (bookDtoId != bookDto.bookDtoId) {
            return false;
        }
        if (quantity != bookDto.quantity) {
            return false;
        }
        if (borrow != bookDto.borrow) {
            return false;
        }
        if (!cityLibrary.equals(bookDto.cityLibrary)) {
            return false;
        }
        if (!authors.equals(bookDto.authors)) {
            return false;
        }
        if (!genres.equals(bookDto.genres)) {
            return false;
        }
        if (!title.equals(bookDto.title)) {
            return false;
        }
        if (!publisher.equals(bookDto.publisher)) {
            return false;
        }
        if (!description.equals(bookDto.description)) {
            return false;
        }
        if (!year.equals(bookDto.year)) {
            return false;
        }
        if (!added.equals(bookDto.added)) {
            return false;
        }
        if (!isbn.equals(bookDto.isbn)) {
            return false;
        }
        return shelf.equals(bookDto.shelf);
    }

    @Override
    public int hashCode() {
        int result = (int) (bookDtoId ^ (bookDtoId >>> 32));
        result = 31 * result + (cityLibrary != null ? cityLibrary.hashCode() : 0);
        result = 31 * result + (authors != null ? authors.hashCode() : 0);
        result = 31 * result + (genres != null ? genres.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + quantity;
        result = 31 * result + borrow;
        result = 31 * result + (publisher != null ? publisher.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (year != null ? year.hashCode() : 0);
        result = 31 * result + (added != null ? added.hashCode() : 0);
        result = 31 * result + (isbn != null ? isbn.hashCode() : 0);
        result = 31 * result + (shelf != null ? shelf.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuffer line = new StringBuffer();
        line.append("Book{" ).append("bookDtoId='").append(bookDtoId)
                .append("', isbn='").append(isbn)
                .append("', title='").append(title)
                .append("', authors='").append(authors)
                .append("', genres='").append(genres)
                .append("', shelf='").append(shelf)
                .append("', cityLibrary='").append(cityLibrary)
                .append("', quantity=").append(quantity)
                .append("', borrow=").append(borrow)
                .append("', publisher='").append(publisher)
                .append("', year='").append(year)
                .append("', added=").append(added)
                .append("', description='").append(description).append('}');
        return line.toString();
    }
}
