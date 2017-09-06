package com.tripayweb.validation;

import com.tripayweb.app.model.request.GCMError;
import com.tripayweb.app.model.request.GCMRequest;
import org.springframework.web.multipart.MultipartFile;

public class GCMValidation {
    public GCMError checkError(GCMRequest dto){
        boolean valid = true;
        GCMError error = new GCMError();
        if(CommonValidation.isNull(dto.getTitle())){
            valid = false;
            error.setTitle("Enter title of message");
        }
        if(CommonValidation.isNull(dto.getMessage())){
            valid = false;
            error.setMessage("Enter Message to sent");
        }
        try {
            MultipartFile image = dto.getGcmImage();
             if(!(image.getContentType().contains("image"))){
                valid = false;
                error.setGcmImage("Not a valid image format");
            }else if(image.getSize() <= 0 && image.getSize() > 2*1024) {
                valid = false;
                error.setGcmImage("Please select image (<= 2 MB)");
            }
        }catch(NullPointerException ex){
            valid = false;
            error.setGcmImage("Please choose image");
        }
        error.setValid(valid);
        return error;

    }
}
