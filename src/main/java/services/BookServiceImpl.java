package services;

import models.*;
import repos.BookRepository;
import services.BookService;

import javax.jws.HandlerChain;
import javax.jws.WebMethod;
import javax.jws.WebService;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@WebService(endpointInterface = "services.BookService")
public class BookServiceImpl implements BookService {
    private BookRepository bookRepository;

    private BookRepository createBR(){
        if(bookRepository == null){
            bookRepository = new BookRepository();
        }
        return bookRepository;
    }

    @Override
    public CreateResponse create(CreateRequest createRequest) {
        ResponseInfo responseInfo = new ResponseInfo();
        CreateResponse createResponse = new CreateResponse();
        try {
            Book book = createRequest.getBook();
            bookRepository = createBR();
            bookRepository.create(book);
            responseInfo.setCode(200);
            responseInfo.setMessage("Saccessfull");
            responseInfo.setTime(new Date().toString());
            createResponse.setResponseInfo(responseInfo);
            createResponse.setBook(book);
        }catch (Exception ex){
            responseInfo.setCode(500);
            responseInfo.setMessage("Error");
            responseInfo.setTime(new Date().toString());
            createResponse.setResponseInfo(responseInfo);
            createResponse.setBook(null);
        }
        return createResponse;
    }

    @Override
    public FindByNameResponse findByName(FindByNameRequest findByNameRequest) {
        ResponseInfo responseInfo = new ResponseInfo();
        FindByNameResponse response = new FindByNameResponse();
        try {
            bookRepository = createBR();
            List<Book> books = bookRepository.findByName(findByNameRequest.getName());
//            for(Book book : books){
//                System.out.println(book.getAuthor());
//            }
            if(books.size() != 0) {
                responseInfo.setCode(200);
                responseInfo.setMessage("Successfull");
                responseInfo.setTime(new Date().toString());
                response.setResponseInfo(responseInfo);
//                response.setBooks(books);
                for(Book book : books){
                    response.getBook().add(book);
//                    System.out.println(response.getBook().get(0).getAuthor());
                }
            }else {
                responseInfo.setCode(404);
                responseInfo.setMessage("Not found");
                responseInfo.setTime(new Date().toString());
                response.setResponseInfo(responseInfo);
                response.getBook().add(null);
            }

        } catch (SQLException e) {
            responseInfo.setCode(500);
            responseInfo.setMessage("Error");
            responseInfo.setTime(new Date().toString());
            response.setResponseInfo(responseInfo);
            response.getBook().add(null);
        }
        return response;
    }
}
