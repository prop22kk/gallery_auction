package gall_test.jytest.repository;

import gall_test.jytest.activities.Activity;

import java.util.List;
import java.util.Optional;

public interface ActivityRepository {
    Activity save(Activity activity);
    List<Activity> findAll();
    List<Activity> findByMemberId(String memberId);
    List<Activity> findByItemId(Long itemId);
    Optional<Activity> findById(Long id);

}
