package api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Book {

    @JsonProperty("Author")
    private Author author;
    @JsonProperty("AuthorId")
    private int authorId;
    @JsonProperty("DateAdded")
    private String dateAdded;
    @JsonProperty("DateAddedIso")
    private String dateAddedIso;
    @JsonProperty("Genre")
    private Genre genre;
    @JsonProperty("GenreId")
    private int genreId;
    @JsonProperty("Id")
    private int id;
    private boolean isOutOfPrint;
    @JsonProperty("Name")
    private String name;

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getDateAddedIso() {
        return dateAddedIso;
    }

    public void setDateAddedIso(String dateAddedIso) {
        this.dateAddedIso = dateAddedIso;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isIsOutOfPrint() {
        return isOutOfPrint;
    }

    public void setIsOutOfPrint(boolean isOutOfPrint) {
        this.isOutOfPrint = isOutOfPrint;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    @Override
    public boolean equals(Object otherObject){
        if (this == otherObject) return true;

        if (otherObject == null) return false;

        if(getClass()!= otherObject.getClass()) return false;

        Book other = (Book) otherObject;

        return Objects.equals(author, other.author)
                && authorId == other.authorId
                && Objects.equals(genre, other.genre)
                && genreId == other.genreId
                && Objects.equals(name, other.name);

    }
    @Override
    public int hashCode(){
        return Objects.hash(author,authorId,genre,genreId,name);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("author", author)
                .append("authorId", authorId)
                .append("genre", genre)
                .append("genreId", genreId)
                .append("name", name).toString();
    }
}