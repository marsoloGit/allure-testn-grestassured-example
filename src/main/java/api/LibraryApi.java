package api;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.PropertiesReader;

import java.util.Properties;

import static io.restassured.RestAssured.given;

public class LibraryApi {
    private static final String PATH_SETTINGS = "./resources/settings";
    protected static Properties settings = PropertiesReader.load(PATH_SETTINGS);

    private String sessionId;
    private String baseUri = settings.getProperty("libraryApiBaseUrl");



    public LibraryApi(String username, String password) throws Exception {
        if((username==null)|| (password ==null)){
            throw new Exception("api username/password should not be null");
        }

        RestAssured.baseURI = baseUri;
        PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();
        authScheme.setUserName(username);
        authScheme.setPassword(password);
        RestAssured.authentication = authScheme;
        RequestSpecification rs = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON);
        RestAssured.requestSpecification=rs;
        sessionId = getNewSessionId();

    }


    public LibraryApi() throws Exception {
        this(settings.getProperty("libraryLogin"),settings.getProperty("libraryPassword"));
    }

    private String getNewSessionId(){
        RequestSpecification req = given();
        Response resp =  req.when().get(EndPoints.session);
        return resp.then().statusCode(200).extract().body().asString();

    }

    public Response getBooks()
    {
        return given().when().get(EndPoints.books, sessionId);
    }

    public Response getAuthors(){
        return given().when().get(EndPoints.authors, sessionId);
    }

    public Response addBook(Book book){
        RequestSpecification req = given().body(book);
        Response resp = req.when().post(EndPoints.books, sessionId);
        return resp;
    }


    public Response updateBook(int bookId, Book book){
        Response resp = given().pathParam("id", bookId).body(book).when().put(EndPoints.book, sessionId);
        return resp;
    }

    public Response deleteBook(int bookId){
        Response resp= given().pathParam("id", bookId).when().delete(EndPoints.book, sessionId);
        return resp;
    }




}
