package com.tripayweb.app.model.response;

import com.tripayweb.app.model.ServiceTypeDTO;
import com.tripayweb.app.model.ServicesDTO;

import java.util.List;

public class ServiceTypeResponse {

    private List<ServiceTypeDTO> serviceDTOs;
    private String code;
    private boolean success;
    private List<ServicesDTO> servicesDTOs;

    public List<ServicesDTO> getServicesDTOs() {
        return servicesDTOs;
    }

    public void setServicesDTOs(List<ServicesDTO> servicesDTOs) {
        this.servicesDTOs = servicesDTOs;
    }

    public List<ServiceTypeDTO> getServiceDTOs() {
        return serviceDTOs;
    }

    public void setServiceDTOs(List<ServiceTypeDTO> serviceDTOs) {
        this.serviceDTOs = serviceDTOs;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
