package org.limadelrey.spring.rest.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/books")
    public List<Book> readAll() {
        return bookRepository.findAll();
    }

    @GetMapping("/books/{id}")
    public Book readOne(@PathVariable Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book with id " + id + " not found"));
    }

    @PostMapping("/books")
    public void create(@RequestBody Book book) {
        bookRepository.save(book);
    }

    @PutMapping("/books/{id}")
    public Book update(@RequestBody Book newBook, @PathVariable Long id) {
        return bookRepository.findById(id)
                .map(book -> {
                    book.setAuthor(newBook.getAuthor());
                    book.setTitle(newBook.getTitle());

                    return bookRepository.save(book);
                })
                .orElseThrow(() -> new RuntimeException("Book with id " + id + " not found"));
    }

    @DeleteMapping("/books/{id}")
    public void delete(@PathVariable Long id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
        } else {
            throw new RuntimeException("Book with id " + id + " not found");
        }
    }

}
