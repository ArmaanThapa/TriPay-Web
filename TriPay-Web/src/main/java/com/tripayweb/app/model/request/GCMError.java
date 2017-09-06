package com.tripayweb.app.model.request;

import org.springframework.web.multipart.MultipartFile;

public class GCMError {
    private boolean valid;
    private String title;
    private String gcmImage;
    private String message;

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGcmImage() {
        return gcmImage;
    }

    public void setGcmImage(String gcmImage) {
        this.gcmImage = gcmImage;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
