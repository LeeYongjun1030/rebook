package project.rebook.repository.order;

import project.rebook.domain.Order;

import java.util.List;

public interface OrderRepository {

    public Long save(Order order);

    public Order findById(Long id);

    public List<Order> findByMemberId(Long memberId);

    public List<Order> findByMemberIdV2(Long memberId);

    public void delete(Long id);

    public void clear();
}
