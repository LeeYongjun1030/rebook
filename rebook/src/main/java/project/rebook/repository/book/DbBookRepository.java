package project.rebook.repository.book;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import project.rebook.domain.book.Book;
import project.rebook.domain.member.Member;

import javax.persistence.EntityManager;
import javax.xml.stream.events.EntityReference;
import java.util.List;

@Repository
@Primary
@RequiredArgsConstructor
public class DbBookRepository implements BookRepository{

    private final EntityManager em;

    @Override
    public Long save(Book book) {
        em.persist(book);
        return book.getId();
    }

    @Override
    public Book findById(Long id) {
        return em.find(Book.class, id);
    }

    @Override
    public List<Book> findByIdList(List<Long> ids) {
        return em.createQuery(
                "select b from Book b" +
                " where b.id in :ids", Book.class)
                .setParameter("ids", ids)
                .getResultList();
    }

    @Override
    public List<Book> findAll() {
        return em.createQuery("select b from Book b", Book.class)
                .getResultList();
    }

    @Override
    public void clear() {
        em.createQuery("delete from Book").executeUpdate();
    }
}
