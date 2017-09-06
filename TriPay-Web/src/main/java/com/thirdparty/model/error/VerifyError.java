package com.thirdparty.model.error;


public class VerifyError {
    private String contactNo;
    private String otp;
    private boolean valid;

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    @Override
    public String toString() {
        return "VerifyError{" +
                "contactNo='" + contactNo + '\'' +
                ", otp='" + otp + '\'' +
                ", valid=" + valid +
                '}';
    }
}
