package sy.project2019.itshow.sasohan2019.Model;

public class DiaryModel {
    String token;
    String title;
    String content;
    long writeDate;
    String receiver;
    boolean isSended;
    boolean isPrivate;

    public DiaryModel(String token, String title, String content,
                      long writeDate, String receiver, boolean isSended, boolean isPrivate) {
        this.token = token;
        this.title = title;
        this.content = content;
        this.writeDate = writeDate;
        this.receiver = receiver;
        this.isSended = isSended;
        this.isPrivate = isPrivate;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getWriteDate() {
        return writeDate;
    }

    public void setWriteDate(long writeDate) {
        this.writeDate = writeDate;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public boolean isSended() {
        return isSended;
    }

    public void setSended(boolean sended) {
        isSended = sended;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

}
