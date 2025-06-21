package gall_test.jytest.activities;

public class ActivityRequest {
    private String memberId;
    private Long itemId;
    private Double price;

    public String getMemberId() { return memberId; }
    public void setMemberId(String memberId) { this.memberId = memberId; }

    public Long getItemId() { return itemId; }
    public void setItemId(Long itemId) { this.itemId = itemId; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
}
