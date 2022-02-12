package project.rebook.repository.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import project.rebook.domain.Order;
import project.rebook.domain.Review;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class DbOrderRepository implements OrderRepository {

    private final EntityManager em;

    @Override
    public Long save(Order order) {
        em.persist(order);
        return order.getId();
    }

    @Override
    public Order findById(Long id) {
        return em.find(Order.class, id);
    }

    @Override
    public List<Order> findByMemberId(Long memberId) {
        return em.createQuery(
                "select o from Order o " +
                " where o.member.id = :memberId", Order.class)
                .setParameter("memberId", memberId)
                .getResultList();
    }

    @Override
    public void delete(Order order) {
        em.createQuery(
                "delete from Order o" +
                        " where o.id = :orderId")
                .setParameter("orderId", order.getId())
                .executeUpdate();
    }

    @Override
    public void clear() {
        em.createQuery(
                "delete from Order")
                .executeUpdate();
    }
}
