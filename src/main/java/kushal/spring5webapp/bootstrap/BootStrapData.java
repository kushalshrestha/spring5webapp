package kushal.spring5webapp.bootstrap;

import kushal.spring5webapp.domain.Author;
import kushal.spring5webapp.domain.Book;
import kushal.spring5webapp.domain.Publisher;
import kushal.spring5webapp.repositories.AuthorRepository;
import kushal.spring5webapp.repositories.BookRepository;
import kushal.spring5webapp.repositories.PublisherRepository;
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

        authorRepository.deleteAll();
        bookRepository.deleteAll();
        publisherRepository.deleteAll();


        Publisher publisher = new Publisher();
        publisher.setName("SFG Publishing");
        publisher.setCity("St Petersburg");
        publisher.setState("FL");
        publisherRepository.save(publisher);

        System.out.println("Publisher Count: " + publisherRepository.count());

        Author kushal = new Author("Kushal", "Shrestha");
        Book b1 = new Book("Java 8", "123456");
        kushal.getBooks().add(b1);
        b1.getAuthors().add(kushal);

        b1.setPublisher(publisher);
        publisher.getBooks().add(b1);

        authorRepository.save(kushal);
        bookRepository.save(b1);
        publisherRepository.save(publisher);

        Author anil = new Author("Anil","Shrestha");
        Book b2 = new Book("AngularJS", "123412");
        anil.getBooks().add(b2);
        b2.getAuthors().add(anil);

        b2.setPublisher(publisher);
        publisher.getBooks().add(b2);

        authorRepository.save(anil);
        bookRepository.save(b2);
        publisherRepository.save(publisher);


        System.out.println("Number of books :" + bookRepository.count());
        System.out.println("Publisher's Number of books :" + publisher.getBooks().size());
    }
}