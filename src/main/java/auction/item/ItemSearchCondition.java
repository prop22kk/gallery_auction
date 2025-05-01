package auction.item;

public class ItemSearchCondition {
    private String artist;
    private String itemName;
    private String category;

    public ItemSearchCondition(String artist, String itemName, String category) {
        this.artist = artist;
        this.itemName = itemName;
        this.category = category;
    }

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}