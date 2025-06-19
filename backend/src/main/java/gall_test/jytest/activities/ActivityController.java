package gall_test.jytest.activities;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activities")
public class ActivityController {

    private final ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    // 물품 추가
    @PostMapping
    public ResponseEntity<Activity> create(@RequestBody ActivityRequest request) {
        Activity activity = new Activity(
                request.getMemberId(),
                request.getItemId(),
                request.getPrice()
        );
        return ResponseEntity.ok(activityService.createActivity(activity));
    }

    // 전체 검색
    @GetMapping
    public ResponseEntity<List<Activity>> getAll() {
        return ResponseEntity.ok(activityService.findAllActivities());
    }

    //조건 검색
    @GetMapping("/member/{memberId}")
    public ResponseEntity<List<Activity>> getByMember(@PathVariable Long memberId) {
        return ResponseEntity.ok(activityService.findByMemberId(memberId));
    }

    @GetMapping("/item/{itemId}")
    public ResponseEntity<List<Activity>> getByItem(@PathVariable Long itemId) {
        return ResponseEntity.ok(activityService.findByItemId(itemId));
    }

    @GetMapping("/{activityId}")
    public ResponseEntity<Activity> getActivityById(@PathVariable Long activityId) {
        return activityService.findById(activityId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
