package com.example.readinglist;

import com.example.readinglist.book.Book;
import com.example.readinglist.book.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class ReadingListController {

    private final BookRepository bookRepository;

    public ReadingListController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/{reader}")
    public String readerBooks(@PathVariable("reader") String reader, Model model) {
        List<Book> readingList = bookRepository.findByReader(reader);
        if(reader != null) {
            model.addAttribute("books", readingList);
        }
        return "readingList";
    }

    @PostMapping("/{reader}")
    public String addToReadingList(@PathVariable("reader") String reader, Book book) {
        book.setReader(reader);
        bookRepository.save(book);
        return "redirect:/{reader}";
    }
}
