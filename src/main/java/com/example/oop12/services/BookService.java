package com.example.oop12.services;

import com.example.oop12.data.Book;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

@Service
public class BookService {
    private List<Book> books = new ArrayList<>();

    @PostConstruct
    public void init () {
        readFromJSON("books.json");
    }
    /////////////////////////////////////////

    public List<Book> readFromFile(String filePath) {
        books = new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            books = (List<Book>) ois.readObject();
            System.out.println("File read success!");
            return books;
        } catch (IOException e) {
            System.out.println("File Read ERROR!");
        } catch (ClassNotFoundException e) {
            System.out.println("File Not Found!");
        }
        return books;
    }

    public void writeToFile(String filePath) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(books);
            System.out.println("File write success!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public List<Book> readFromJSON(String filePath) {
        ObjectMapper objectMapper = new ObjectMapper();

        books = new ArrayList<>();

        try {
            books = objectMapper.readValue(new File(filePath), new TypeReference<List<Book>>(){});
            System.out.println("File read success!");
            return books;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    public void writeToJSON(String filePath) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File(filePath), books);
            System.out.println("File write success!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


////////////////////////////////////////////////////////////////////////
    public List<Book> getBooks() {
        return books;
    }
    public void addBook(Book book) {
        books.add(book);
    }

    public void deleteById(int id){
        books.removeIf(e -> (e.getId() == id));
    }



    /// УБРАТЬ???
    public void setBooksList(List<Book> arr){
        books = arr;
    }





    //f) для кожного видавництва визначити список книг, виданих ним
    public HashMap<String, List<Book>> getBookListForEveryPublisher () {
        HashMap<String, List<Book>> BookListForEveryPublisher = new HashMap<>();

        for (Book book : books) {
            String publisher = book.getPublisher();

            if (BookListForEveryPublisher.containsKey(publisher)) {
                BookListForEveryPublisher.get(publisher).add(book);
            } else {
                List<Book> arr = new ArrayList<>();
                arr.add(book);
                BookListForEveryPublisher.put(publisher, arr);
            }
        }

        return BookListForEveryPublisher;
    }


    //e) список видавництв, книги яких зареєстровані в системі без повторів
    public Set<String> getPublisherSet () {
        Set<String> publisherSet = new HashSet<>();

//        for (Book book : books) {
//            publisherSet.add(book.getAuthor());
//        }
//
        books.forEach(e -> publisherSet.add(e.getPublisher()));

        return publisherSet;

    }



//    public List<String> getAuthorListSortedByAlphabet() {
//        ArrayList<String> authorList = new ArrayList<>();
//
//        for (Book book : books) {
//            authorList.add(book.getAuthor());
//        }
//
//        Collections.sort(authorList);
//
//        return authorList;
//    }


    //d. список авторів в алфавітному порядку     ПЕРЕДЕЛАТЬ без повторений
    public Set<String> getAuthorListSortedByAlphabet() {
        Set<String> publisherSet = new TreeSet<>();

        books.forEach(e -> publisherSet.add(e.getAuthor()));

        return publisherSet;

    }



    public List<Book> getByAuthorSortedByYears(String author) {
        ArrayList<Book> authorList = new ArrayList<>();

        for (Book book : books) {
            if (book != null) {
                if (book.getAuthor().equals(author)) {
                    authorList.add(book);
                }}
        }

        authorList.sort(Comparator.comparingInt(Book::getYear));

        return authorList;
    }

    public List<Book> getBooksAfterYear(int year) {
        ArrayList<Book> newArray = new ArrayList<>();

        for (Book book : books) {
            if (book.getYear() > year) {
                newArray.add(book);
            }
        }

        return newArray;
    }


    public List<Book> getBooksByPublisher(String publisher) {
        ArrayList<Book> newArray = new ArrayList<>();

        for (Book book : books) {
            if (book.getPublisher().equals(publisher)) {
                newArray.add(book);
            }
        }

        return newArray;
    }




    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Book book : books) {
            sb.append(book).append("\n");
        }
        return sb.toString();
    }
}
