package project.rebook.repository.book;

import org.springframework.stereotype.Repository;
import project.rebook.domain.book.Book;
import project.rebook.domain.member.Member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class MemoryBookRepository implements BookRepository{

    private static Map<Long, Book> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Long save(Book book) {
        book.setId(++sequence);
        Long id = book.getId();
        store.put(id, book);
        return id;
    }

    @Override
    public Book findById(Long id) {
        return store.get(id);
    }

    @Override
    public List<Book> findAll() {
        return new ArrayList<>(store.values());
    }
}
