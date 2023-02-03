package bootstrap;

import domain.Author;
import domain.Book;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import repositories.AuthorRepository;
import repositories.BookRepository;

@Component //mark as a component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author kushal = new Author("Kushal", "Shrestha");
        Book b1 = new Book("Java 8", "123456");
        kushal.getBooks().add(b1);
        b1.getAuthors().add(kushal);

        authorRepository.save(kushal);
        bookRepository.save(b1);

        Author anil = new Author("Anil","Shrestha");
        Book b2 = new Book("AngularJS", "123412");
        anil.getBooks().add(b2);
        b2.getAuthors().add(anil);

        authorRepository.save(anil);
        bookRepository.save(b2);

        System.out.println("Started in Bootstrap");
        System.out.println("Number of books :" + bookRepository.count());
    }
}