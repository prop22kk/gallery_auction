package gall_test.jytest.item;



import gall_test.jytest.repository.ItemRepository;
import java.util.List;
import java.util.Optional;

public class ItemService {
    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Item create(Item item) {
        return itemRepository.save(item);
    }

    public Optional<Item> findById(Long id) {
        return itemRepository.findById(id);
    }

    public int update(Item item) {
        return itemRepository.update(item);
    }

    public int delete(Long id) {
        return itemRepository.delete(id);
    }

    public List<Item> search(ItemSearchCondition cond) {
        return itemRepository.search(cond);
    }
}