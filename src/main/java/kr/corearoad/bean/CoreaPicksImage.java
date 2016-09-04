package kr.corearoad.bean;

/**
 * Created by Naver on 2016-09-03.
 */
public class CoreaPicksImage {
    private String key;
    private String img;

    public CoreaPicksImage(){}

    public CoreaPicksImage(String key, String img) {
        this.key = key;
        this.img = img;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("CoreaPicksImage{");
        sb.append("key='").append(key).append('\'');
        sb.append(", img='").append(img).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
