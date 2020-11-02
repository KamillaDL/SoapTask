package models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "findByNameResponse", propOrder = {
        "responseInfo",
        "books"
})
public class FindByNameResponse {
    @XmlElement(name="books")
    List<Book> books;
    @XmlElement(name="responseInfo")
    ResponseInfo responseInfo;

    public List<Book> getBook() {
        if(books == null){
            books = new ArrayList<Book>();
        }
        return this.books;
    }

//    public void setBooks(List<Book> book) {
//        this.books = book;
//    }

    public ResponseInfo getResponseInfo() {
        return responseInfo;
    }

    public void setResponseInfo(ResponseInfo responseInfo) {
        this.responseInfo = responseInfo;
    }
}
