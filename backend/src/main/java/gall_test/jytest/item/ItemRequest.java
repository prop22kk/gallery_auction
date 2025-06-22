package gall_test.jytest.item;

import java.time.LocalDateTime;

public class ItemRequest {
    private String artist;
    private String itemName;
    private double startingPrice;
    private String category;
    private String ownerId;
    private double currentPrice;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String imageUrl;

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getStartingPrice() {
        return startingPrice;
    }

    public void setStartingPrice(double startingPrice) {
        this.startingPrice = startingPrice;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public LocalDateTime getAuctionStart() {
        return startTime;
    }

    public void setAuctionStart(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getAuctionEnd() {
        return endTime;
    }

    public void setAuctionEnd(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getImageUrl() {   // getter 추가
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {  // setter 추가
        this.imageUrl = imageUrl;
    }

    public Double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(Double currentPrice) {
        this.currentPrice = currentPrice;
    }
}
