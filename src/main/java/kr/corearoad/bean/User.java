package kr.corearoad.bean;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Component;

/**
 * Created by Naver on 2016-04-09.
 */
@Component
public class User {

    private String email;
    private String name;
    private String password;
    private String nationality;
    private String capableLang1;
    private String capableLang2;
    private String capableLang3;
    private String birthDate;
    private String profilePicture;
    private String tourInitinerary;
    private String selfIntroduction;
    private boolean chatStatus;
    private boolean isKorean;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setCapableLang1(String capableLang1) {
        this.capableLang1 = capableLang1;
    }

    public String getCapableLang2() {
        return capableLang2;
    }

    public void setCapableLang2(String capableLang2) {
        this.capableLang2 = capableLang2;
    }

    public String getCapableLang3() {
        return capableLang3;
    }

    public void setCapableLang3(String capableLang3) {
        this.capableLang3 = capableLang3;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getTourInitinerary() {
        return tourInitinerary;
    }

    public void setTourInitinerary(String tourInitinerary) {
        this.tourInitinerary = tourInitinerary;
    }

    public String getSelfIntroduction() {
        return selfIntroduction;
    }

    public void setSelfIntroduction(String selfIntroduction) {
        this.selfIntroduction = selfIntroduction;
    }

    public boolean isChatStatus() {
        return chatStatus;
    }

    public void setChatStatus(boolean chatStatus) {
        this.chatStatus = chatStatus;
    }

    public boolean isKorean() {
        return isKorean;
    }

    public void setIsKorean(boolean isKorean) {
        this.isKorean = isKorean;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", nationality='" + nationality + '\'' +
                ", capableLang1='" + capableLang1 + '\'' +
                ", capableLang2='" + capableLang2 + '\'' +
                ", capableLang3='" + capableLang3 + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", profilePicture='" + profilePicture + '\'' +
                ", tourInitinerary='" + tourInitinerary + '\'' +
                ", selfIntroduction='" + selfIntroduction + '\'' +
                ", chatStatus=" + chatStatus +
                ", isKorean=" + isKorean +
                '}';
    }
}
