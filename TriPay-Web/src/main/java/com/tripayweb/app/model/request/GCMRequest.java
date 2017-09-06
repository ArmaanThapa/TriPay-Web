package com.tripayweb.app.model.request;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

public class GCMRequest {
    private boolean genderM;
    private boolean genderF;
    private String regTo;
    private String regFrom;
    private String title;
    private MultipartFile gcmImage;
    private String message;

    public boolean isGenderM() {
        return genderM;
    }

    public void setGenderM(boolean genderM) {
        this.genderM = genderM;
    }

    public boolean isGenderF() {
        return genderF;
    }

    public void setGenderF(boolean genderF) {
        this.genderF = genderF;
    }

    public String getRegTo() {
        return regTo;
    }

    public void setRegTo(String regTo) {
        this.regTo = regTo;
    }

    public String getRegFrom() {
        return regFrom;
    }

    public void setRegFrom(String regFrom) {
        this.regFrom = regFrom;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public MultipartFile getGcmImage() {
        return gcmImage;
    }

    public void setGcmImage(MultipartFile gcmImage) {
        this.gcmImage = gcmImage;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "GCMRequest{" +
                "genderM=" + genderM +
                ", genderF=" + genderF +
                ", regTo='" + regTo + '\'' +
                ", regFrom='" + regFrom + '\'' +
                ", title='" + title + '\'' +
                ", gcmImage=" + gcmImage +
                ", message='" + message + '\'' +
                '}';
    }
}
