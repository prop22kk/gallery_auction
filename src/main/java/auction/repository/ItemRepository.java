package auction.repository;

import auction.item.Item;
import auction.item.ItemSearchCondition;

import java.util.List;
import java.util.Optional;

public interface ItemRepository {
    Item save(Item item);
    Optional<Item> findById(Long id);
    int update(Item item);
    int delete(Long id);
    List<Item> search(ItemSearchCondition cond);
}