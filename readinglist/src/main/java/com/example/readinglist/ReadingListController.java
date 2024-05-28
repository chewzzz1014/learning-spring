package com.example.readinglist;

import com.example.readinglist.amazon.AmazonProperties;
import com.example.readinglist.book.Book;
import com.example.readinglist.book.BookRepository;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
@ConfigurationProperties(prefix = "amazon")
public class ReadingListController {

    private final AmazonProperties amazonProperties;
    private final BookRepository bookRepository;

    public ReadingListController(AmazonProperties amazonProperties, BookRepository bookRepository) {
        this.amazonProperties = amazonProperties;
        this.bookRepository = bookRepository;
    }

    @GetMapping("/{reader}")
    public String readerBooks(@PathVariable("reader") String reader, Model model) {
        List<Book> readingList = bookRepository.findByReader(reader);
        if(reader != null) {
            model.addAttribute("books", readingList);
            model.addAttribute("reader", reader);
            model.addAttribute("amazonID", amazonProperties.getAssociateId());
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
