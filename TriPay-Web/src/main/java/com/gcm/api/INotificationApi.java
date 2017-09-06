package com.gcm.api;


import com.gcm.model.NotificationDTO;
import com.thirdparty.model.ResponseDTO;

public interface INotificationApi {
    public ResponseDTO sendNotification(NotificationDTO dto);
}
