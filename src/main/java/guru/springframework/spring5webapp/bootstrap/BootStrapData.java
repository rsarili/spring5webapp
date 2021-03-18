package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(final AuthorRepository authorRepository,
        final BookRepository bookRepository, final PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(final String... args) throws Exception {
        Publisher oreilly = new Publisher("O'Reilly", "USA", "Springfield", "MO", "65802");

        publisherRepository.save(oreilly);

        System.out.println("Publishers: " + publisherRepository.findAll());

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "123456");

        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        oreilly.getBooks().add(ddd);
        ddd.setPublisher(oreilly);

        authorRepository.save(eric);
        bookRepository.save(ddd);
        publisherRepository.save(oreilly);

        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("j2EE Development without EJB", "234567");

        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);

        oreilly.getBooks().add(noEJB);
        noEJB.setPublisher(oreilly);

        authorRepository.save(rod);
        bookRepository.save(noEJB);
        publisherRepository.save(oreilly);

        System.out.println("Started in Bootstrap");
        System.out.println("Number of Books: " + bookRepository.count());
        System.out.println("Number of Publishers Books: " + oreilly.getBooks().size());
    }
}
