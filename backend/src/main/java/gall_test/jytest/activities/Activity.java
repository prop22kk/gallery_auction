package gall_test.jytest.activities;

import java.sql.Timestamp;

public class Activity {
    private Long activityId;
    private String memberId;
    private Long itemId;
    private Double price;
    private Timestamp activityTime;

    public Activity() {}

    public Activity(String memberId, Long itemId, Double price) {
        this.memberId = memberId;
        this.itemId = itemId;
        this.price = price;
    }

    public Activity(Long activityId, String memberId, Long itemId, Double price, Timestamp activityTime) {
        this.activityId = activityId;
        this.memberId = memberId;
        this.itemId = itemId;
        this.price = price;
        this.activityTime = activityTime;
    }

    public Long getActivityId() { return activityId; }
    public void setActivityId(Long activityId) { this.activityId = activityId; }

    public String getMemberId() { return memberId; }
    public void setMemberId(String memberId) { this.memberId = memberId; }

    public Long getItemId() { return itemId; }
    public void setItemId(Long itemId) { this.itemId = itemId; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public Timestamp getActivityTime() { return activityTime; }
    public void setActivityTime(Timestamp activityTime) { this.activityTime = activityTime; }
}
