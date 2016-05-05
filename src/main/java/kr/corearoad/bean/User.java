package kr.corearoad.bean;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Naver on 2016-04-09.
 */
@Component
public class User {

    private String email;
    private String name;
    private String password;
    private String gender;
    private String nationality;
    private String capableLang1;
    private String capableLang2;
    private String capableLang3;
    private String birthDate;
    private String profilePicture;
    private String tourInitinerary;
    private String selfIntroduction;
    private boolean chatStatus = false;
    private boolean isKorean = false;
    private boolean forFakeUser = false;

    public User(String email, String name, String password, String gender, String nationality, String capableLang1, String capableLang2, String capableLang3, String birthDate, String profilePicture, String tourInitinerary, String selfIntroduction, boolean chatStatus, boolean isKorean) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.gender = gender;
        this.nationality = nationality;
        this.capableLang1 = capableLang1;
        this.capableLang2 = capableLang2;
        this.capableLang3 = capableLang3;
        this.birthDate = birthDate;
        this.profilePicture = profilePicture;
        this.tourInitinerary = tourInitinerary;
        this.selfIntroduction = selfIntroduction;
        this.chatStatus = chatStatus;
        this.isKorean = isKorean;
    }

    public User(boolean bfakeUser){
        this.forFakeUser = bfakeUser;
    }

    public User() {}

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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public String getCapableLang1() {
        return capableLang1;
    }

    public void setKorean(boolean korean) {
        isKorean = korean;
    }

    public boolean isForFakeUser() {
        return forFakeUser;
    }

    public void setForFakeUser(boolean forFakeUser) {
        this.forFakeUser = forFakeUser;
    }

    public String getCapableLangJsonString(String lang, String level) {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("level", level);
        map.put("lang", lang);

        Gson gson = new GsonBuilder().create();
        ;

        return gson.toJson(map);
    }



    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
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
