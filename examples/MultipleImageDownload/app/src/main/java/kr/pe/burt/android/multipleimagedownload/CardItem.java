package kr.pe.burt.android.multipleimagedownload;

/**
 * Created by burt on 2016. 5. 2..
 */
public class CardItem {
    String imageURL;
    String title;


    public CardItem(String imageURL, String title) {
        this.imageURL = imageURL;
        this.title = title;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getTitle() {
        return title;
    }
}
