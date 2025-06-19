package gall_test.jytest.repository;

import gall_test.jytest.item.ItemSearchCondition;
import gall_test.jytest.item.Item;

import java.util.List;
import java.util.Optional;

public interface ItemRepository {

    Item save(Item item);
    Optional<Item> findById(Long id);
    int update(Item item);
    int delete(Long id);
    List<Item> search(ItemSearchCondition cond);
}