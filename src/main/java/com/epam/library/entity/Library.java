package com.epam.library.entity;

import java.io.Serializable;

/**
 * Class {@link Library} is an entity that represents concrete records from 'Library' table.
 *
 * @author Alexander Pishchala
 *
 */

public class Library implements Serializable {

    private static final long serialVersionUID = -5728043296240857736L;

    private int libraryId;
    private String city;
    private String street;
    private LibraryStatus status;

    public Library() {
    }

    public Library(String city, String street) {
        this.city = city;
        this.street = street;
    }

    public Library(int libraryId, String city, String street) {
        this.libraryId = libraryId;
        this.city = city;
        this.street = street;
    }

    public Library(int libraryId, String city, String street, LibraryStatus status) {
        this.libraryId = libraryId;
        this.city = city;
        this.street = street;
        this.status = status;
    }

    public int getLibraryId() {
        return libraryId;
    }

    public void setLibraryId(int libraryId) {
        this.libraryId = libraryId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public LibraryStatus getStatus() {
        return status;
    }

    public void setStatus(LibraryStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Library library = (Library) obj;

        if (libraryId != library.libraryId) {
            return false;
        }
        if (!city.equals(library.city)) {
            return false;
        }
        if (!street.equals(library.street)) {
            return false;
        }
        return status == library.status;
    }

    @Override
    public int hashCode() {
        int result = libraryId;
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (street != null ? street.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuffer line = new StringBuffer();
        line.append("Library{").append("libraryId='").append(libraryId)
                .append("', city='").append(city)
                .append("', street='").append(street)
                .append(", status='").append(status).append('}');
        return line.toString();
    }
}
