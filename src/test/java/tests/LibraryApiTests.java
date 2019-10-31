package tests;

import api.Author;
import api.Book;
import api.LibraryApi;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.not;

public class LibraryApiTests extends BasicTest {

    LibraryApi api;
//    the book object which details will be used in the request for adding a book
    Book book;
//    the book object returned in the response of addBook() method
    Book createdBook;
//    an id of the fist author in the list of authors
    int authorId ;

    @Step("Add a new book")
    @BeforeClass
    public void setUp() throws Exception {
        api = new LibraryApi();
//        Get the first auther from the list and take its id
        Author[] authors = api.getAuthors().then().extract().body().as(Author[].class);
        authorId = authors[0].getId();
//        Create an Book object that will be used for adding a new book
        book = new Book();
        book.setAuthorId(authorId);
        book.setGenreId(1);
        book.setName("Name of the book");
        book.setDateAdded("/Date(1421609052000-0500)/");
        book.setDateAddedIso("2015-01-18T19:24:12");
//      Add a new book to the system
        createdBook = api.addBook(book).then().statusCode(200).log().all().extract().body().as(Book.class);

    }
    @AfterClass
    @Step("Delete a created book")
    public void cleanUp(){
        api.deleteBook(createdBook.getId()).then().statusCode(200).log().all();
        api.getBooks().then().assertThat().body("Id",not(hasItems(createdBook.getId())));
    }

    @Test(priority = 0, description = "Retrieves a list of books in the system")
    @Step("Get the list of books and make sure it is not empty")
    public void getBooks() {
        Book[] books = api.getBooks().then().statusCode(200).log().all().extract().body().as(Book[].class);
        Assert.assertTrue(books.length>0);

    }
    @Test(priority = 1, description = "Adds a new book to the system")
    @Step("Check that the added book was actually added")
    public void addBook(){
        Assert.assertTrue(book.equals(createdBook));

    }

    @Test(priority = 2, description = "Updates an existing book in the system")
    @Step("Update a name of an existing book and check that it was updated")
    public void updateBook(){
        String newName = "New book name";
        createdBook.setName(newName);
        api.updateBook(createdBook.getId(),createdBook).then().statusCode(200);
        Assert.assertEquals(createdBook.getName(),newName);

    }



}
