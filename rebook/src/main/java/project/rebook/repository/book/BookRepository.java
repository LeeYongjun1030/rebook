package project.rebook.repository.book;

import project.rebook.domain.book.Book;

import java.util.List;

public interface BookRepository {

    public Long save(Book book);

    public Book findById(Long id);

    public List<Book> findByIdList(List<Long> ids);

    public List<Book> findAll();

    public void clear();
}
