package gall_test.jytest.item;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    // 1. 물품 추가
    @PostMapping
    public ResponseEntity<Item> createItem(@RequestBody ItemRequest request) {
        Item item = new Item();
        item.setArtist(request.getArtist());
        item.setItemName(request.getItemName());
        item.setStartingPrice(request.getStartingPrice());
        item.setAuctionStart(request.getAuctionStart());
        item.setAuctionEnd(request.getAuctionEnd());
        item.setCategory(request.getCategory());
        item.setOwnerId(request.getOwnerId());
        item.setImageUrl(request.getImageUrl()); // ✅ 이미지 URL 설정

        return ResponseEntity.ok(itemService.create(item));
    }

    // 2. 특정 물품 조회
    @GetMapping("/{id}")
    public ResponseEntity<Item> getItem(@PathVariable("id") Long id) {
        return ResponseEntity.of(itemService.findById(id));
    }

    // 3. 물품 수정
    @PatchMapping("/{id}")
    public ResponseEntity<String> updateItem(@PathVariable("id") Long id,
                                             @RequestBody ItemRequest request) {
        Item item = new Item();
        item.setItemId(id);
        item.setArtist(request.getArtist());
        item.setItemName(request.getItemName());
        item.setStartingPrice(request.getStartingPrice());
        item.setCurrentPrice(request.getStartingPrice());
        item.setAuctionStart(request.getAuctionStart());
        item.setAuctionEnd(request.getAuctionEnd());
        item.setCategory(request.getCategory());
        item.setOwnerId(request.getOwnerId());
        item.setImageUrl(request.getImageUrl()); // ✅ 이미지 URL 설정

        itemService.update(item);
        return ResponseEntity.ok("물품 수정 완료");
    }

    // 4. 물품 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable("id") Long id) {
        itemService.delete(id);
        return ResponseEntity.ok("물품 삭제 완료");
    }

    // 5. 조건 검색
    @GetMapping
    public ResponseEntity<List<Item>> searchItems(ItemSearchCondition cond) {
        return ResponseEntity.ok(itemService.search(cond));
    }
}
