package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component //mark as a component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository ,PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Started in Bootstrap");
        Author kushal = new Author("Kushal", "Shrestha");
        Book b1 = new Book("Java 8", "123456");
        Publisher publisher = new Publisher();
        publisher.setName("SFG Publishing");
        publisher.setCity("St Petersburg");
        publisher.setState("FL");

        publisherRepository.save(publisher);


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


        System.out.println("Number of books :" + bookRepository.count());
    }
}