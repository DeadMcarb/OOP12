package com.example.oop12.data;


import java.io.Serializable;
import java.util.Objects;

public class Book implements Serializable {

    private int id; //id
    private String name; //Назва
    private String author; //Автор
    private double price; //Ціна
    private int year; //Рік видання
    private String publisher; //Видавництво
    private int numberOfPages; //Кількість сторінок

////    ТУТ НИЧЕГО НЕ ПОМЕНЯЛОСЬ

    public Book(int id, String name, String author, double price, int year, String publisher, int numberOfPages) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.price = price;
        this.year = year;
        this.publisher = publisher;
        this.numberOfPages = numberOfPages;
    }

    public Book() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    @Override
    public String toString() {
        return "id = " + id +
                ", name = " + name +
                ", author = " + author +
                ", price = " + price +
                ", year = " + year +
                ", publisher = " + publisher +
                ", numberOfPages = " + numberOfPages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id
                && price == book.price
                && Objects.equals(name, book.name)
                && Objects.equals(author, book.author);
    }
}
