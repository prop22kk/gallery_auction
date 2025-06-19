package gall_test.jytest.activities;

import gall_test.jytest.repository.ActivityRepository;

import java.util.List;
import java.util.Optional;

public class ActivityService {
    private final ActivityRepository activityRepository;

    public ActivityService(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    public Activity createActivity(Activity activity) {
        return activityRepository.save(activity);
    }

    public List<Activity> findAllActivities() {
        return activityRepository.findAll();
    }

    public List<Activity> findByMemberId(Long memberId) {
        return activityRepository.findByMemberId(memberId);
    }

    public List<Activity> findByItemId(Long itemId) {
        return activityRepository.findByItemId(itemId);
    }
    public Optional<Activity> findById(Long id) {
        return activityRepository.findById(id);
    }

}
