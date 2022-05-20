package com.epam.library.entity;

import java.io.Serializable;

/**
 * Class {@link Author} is an entity that represents concrete records from 'Author' table.
 *
 * @author Alexander Pishchala
 *
 */

public class Author implements Serializable {

    private static final long serialVersionUID = 6829963999103023973L;

    private long authorId;
    private String name;

    public Author() {
    }

    public Author(String name) {
        this.name = name;
    }

    public Author(long authorId, String name) {
        this.authorId = authorId;
        this.name = name;
    }

    public long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Author author = (Author) obj;

        if (authorId != author.authorId) {
            return false;
        }
        return name != null ? name.equals(author.name) : author.name == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (authorId ^ (authorId >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuffer line = new StringBuffer();
        line.append("Author{").append("authorId='").append(authorId)
                .append("', name='").append(name).append('}');
        return line.toString();
    }
}
