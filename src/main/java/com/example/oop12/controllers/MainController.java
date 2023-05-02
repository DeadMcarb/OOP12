package com.example.oop12.controllers;


import com.example.oop12.data.Book;
import com.example.oop12.services.BookService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class MainController {

    BookService bookList;


    @GetMapping("/booksTable")
    public String showBooks(Model model) {
        model.addAttribute("booksTable", bookList.getBooks());
        return "booksTable";
    }


    @PostMapping("/addBook")
    public String addBook(
            @RequestParam("id") int id,
            @RequestParam("name") String name,
            @RequestParam("author") String author,
            @RequestParam("price") double price,
            @RequestParam("year") int year,
            @RequestParam("publisher") String publisher,
            @RequestParam("numberOfPages") int numberOfPages
    ) {
        bookList.addBook(new Book(id, name, author, price, year, publisher, numberOfPages));
        return "redirect:/booksTable";
    }

    @GetMapping("/deleteThisBook")
    public String deleteThisBook(@RequestParam("id") int id) {
        bookList.deleteById(id);
        return "redirect:/booksTable";
    }


    @GetMapping("/save")
    public String save() {
        bookList.writeToJSON("books.json");
        return "redirect:/booksTable";
    }




    @GetMapping("/booksByAuthor")
    public String booksByAuthor(
            @RequestParam("author") String author,
            Model model
    ) {
        model.addAttribute("booksTable",
                bookList.getByAuthorSortedByYears(author));
        return "filteredTable";
    }

    @GetMapping("/booksByPublisher")
    public String booksByPublisher(
            @RequestParam("publisher") String publisher,
            Model model
    ) {
        model.addAttribute("booksTable",
        bookList.getBooksByPublisher(publisher));
        return "filteredTable";
    }

    @GetMapping("/booksAfterYear")
    public String booksAfterYear(
            @RequestParam("year") int year,
            Model model
    ) {
        model.addAttribute("booksTable",
                bookList.getBooksAfterYear(year));
        return "filteredTable";
    }




//    a) список книг заданого автора;
//    b) список книг, що видані заданим видавництвом;
//    c) список книг, що випущені після заданого року.

}
