package com.epam.library.entity;

import java.io.Serializable;

/**
 * Class {@link Genre} is an entity that represents concrete records from 'Genre' table.
 *
 * @author Alexander Pishchala
 *
 */

public class Genre implements Serializable {

    private static final long serialVersionUID = -9218508141768679758L;

    private long genreId;
    private String category;

    public Genre() {
    }

    public Genre(String category) {
        this.category = category;
    }

    public Genre(long genreId, String category) {
        this.genreId = genreId;
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public long getGenreId() {
        return genreId;
    }

    public void setGenreId(long genreId) {
        this.genreId = genreId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Genre genre = (Genre) obj;

        if (genreId != genre.genreId) {
            return false;
        }
        return category != null ? category.equals(genre.category) : genre.category == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (genreId ^ (genreId >>> 32));
        result = 31 * result + (category != null ? category.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuffer line = new StringBuffer();
        line.append("Genre{").append("genreId='").append(genreId)
                .append("', category='").append(category).append('}');
        return line.toString();
    }
}
