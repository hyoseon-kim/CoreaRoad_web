package kr.corearoad.bean;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Naver on 2016-05-05.
 */
@Component
public class Action {
    private String no;
    private String name;
    private String city;
    private String text;
    private String totalTime;
    private String meetPlace;
    private String exception;
    private String tagList;
    private String thumbList;
    private String cartList;
    private String bookList;
    private String pictureList;
    private String jingermanList;

    public Action() {
    }

    public Action(String no, String name, String city, String text, String totalTime, String meetPlace, String exception, String tagList, String thumbList, String cartList, String bookList, String pictureList, String jingermanList) {
        this.no = no;
        this.name = name;
        this.city = city;
        this.text = text;
        this.totalTime = totalTime;
        this.meetPlace = meetPlace;
        this.exception = exception;
        this.tagList = tagList;
        this.thumbList = thumbList;
        this.cartList = cartList;
        this.bookList = bookList;
        this.pictureList = pictureList;
        this.jingermanList = jingermanList;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(String totalTime) {
        this.totalTime = totalTime;
    }

    public String getMeetPlace() {
        return meetPlace;
    }

    public void setMeetPlace(String meetPlace) {
        this.meetPlace = meetPlace;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public String getTagList() {
        return tagList;
    }

    public void setTagList(String tagList) {
        this.tagList = tagList;
    }

    public String getThumbList() {
        return thumbList;
    }

    public void setThumbList(String thumbList) {
        this.thumbList = thumbList;
    }

    public String getCartList() {
        return cartList;
    }

    public void setCartList(String cartList) {
        this.cartList = cartList;
    }

    public String getBookList() {
        return bookList;
    }

    public void setBookList(String bookList) {
        this.bookList = bookList;
    }

    public String getPictureList() {
        return pictureList;
    }

    public void setPictureList(String pictureList) {
        this.pictureList = pictureList;
    }

    public String getJingermanList() {
        return jingermanList;
    }

    public void setJingermanList(String jingermanList) {
        this.jingermanList = jingermanList;
    }

    @Override
    public String toString() {
        return "Action{" +
                "no='" + no + '\'' +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", text='" + text + '\'' +
                ", totalTime='" + totalTime + '\'' +
                ", meetPlace='" + meetPlace + '\'' +
                ", exception='" + exception + '\'' +
                ", tagList='" + tagList + '\'' +
                ", thumbList='" + thumbList + '\'' +
                ", cartList='" + cartList + '\'' +
                ", bookList='" + bookList + '\'' +
                ", pictureList='" + pictureList + '\'' +
                ", jingermanList='" + jingermanList + '\'' +
                '}';
    }
}
