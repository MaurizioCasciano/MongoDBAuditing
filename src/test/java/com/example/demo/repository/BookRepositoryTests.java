package com.example.demo.repository;

import com.example.demo.domain.Book;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Rollback
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookRepositoryTests {
    @Autowired
    private BookRepository bookRepository;

    @Test
    @Order(1)
    @Transactional
    public void testCreateBook() {
        this.doCreateBook("1001", "Java Programming");
    }

    @Test
    @Order(2)
    @Transactional
    public void testUpdateBookAndFind() {
        this.doCreateBook("1002", "Python Programming");
        Book existingBook = this.bookRepository.findById("1002").orElse(null);

        // Check Existing Book
        Assertions.assertNotNull(existingBook);

        // Update
        existingBook.setCreated(null);
        existingBook.setCreator(null);
        existingBook.setModifier(null);
        existingBook.setModified(null);

        this.bookRepository.save(existingBook);
        Book existingUpdatedBook = this.bookRepository.findById("1002").orElse(null);

        // Check Existing Updated Book (Working)
        Assertions.assertNotNull(existingUpdatedBook);

        Assertions.assertNotNull(existingUpdatedBook.getCreator());
        Assertions.assertNotNull(existingUpdatedBook.getCreated());

        Assertions.assertNotNull(existingUpdatedBook.getModifier());
        Assertions.assertNotNull(existingUpdatedBook.getModified());
    }

    @Test
    @Order(3)
    @Transactional
    public void testUpdateBookDirect() {
        this.doCreateBook("1003", "Go Programming");
        Book existingBook = this.bookRepository.findById("1003").orElse(null);

        // Check Existing Book
        Assertions.assertNotNull(existingBook);

        // Update
        existingBook.setCreated(null);
        existingBook.setCreator(null);
        existingBook.setModifier(null);
        existingBook.setModified(null);

        Book updatedBook = this.bookRepository.save(existingBook);

        // Check Updated Book (Not working)
        Assertions.assertNotNull(updatedBook);

        Assertions.assertNotNull(updatedBook.getCreator());
        Assertions.assertNotNull(updatedBook.getCreated());

        Assertions.assertNotNull(updatedBook.getModifier());
        Assertions.assertNotNull(updatedBook.getModified());
    }

    private void doCreateBook(String bookID, String bookName) {
        // Create Book
        Book book = Book.builder().id(bookID).name(bookName).build();
        Book createdBook = this.bookRepository.save(book);

        Assertions.assertNotNull(createdBook);
        Assertions.assertEquals(bookID, createdBook.getId());
        Assertions.assertEquals(bookName, createdBook.getName());

        // Check Auditing Fields
        Assertions.assertNotNull(createdBook.getVersion());

        Assertions.assertNotNull(createdBook.getCreator());
        Assertions.assertNotNull(createdBook.getCreated());

        Assertions.assertNotNull(createdBook.getModifier());
        Assertions.assertNotNull(createdBook.getModified());
    }
}
