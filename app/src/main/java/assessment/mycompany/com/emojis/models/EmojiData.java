package assessment.mycompany.com.emojis.models;

/**
 * Created by Debasis on 10/07/2015.
 */
public class EmojiData {
    public String getEmojisImageUrl() {
        return emojisImageUrl;
    }

    public void setEmojisImageUrl(String emojisImageUrl) {
        this.emojisImageUrl = emojisImageUrl;
    }

    public String getEmojisName() {
        return emojisName;
    }

    public void setEmojisName(String emojisName) {
        this.emojisName = emojisName;
    }

    private String emojisImageUrl;
    private String emojisName;
}
