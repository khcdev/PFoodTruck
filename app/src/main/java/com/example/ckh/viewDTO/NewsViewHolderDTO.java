package com.example.ckh.viewDTO;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by kalin on 2017-05-28.
 */

public class NewsViewHolderDTO {

    private ImageView eventImage;
    private TextView eventName;
    private TextView eventDate;
    private TextView eventPlace;

    public NewsViewHolderDTO() {
    }

    public NewsViewHolderDTO(ImageView eventImage, TextView eventName, TextView eventDate, TextView eventPlace) {
        this.eventImage = eventImage;
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.eventPlace = eventPlace;
    }

    public ImageView getEventImage() {
        return eventImage;
    }

    public void setEventImage(ImageView eventImage) {
        this.eventImage = eventImage;
    }

    public TextView getEventName() {
        return eventName;
    }

    public void setEventName(TextView eventName) {
        this.eventName = eventName;
    }

    public TextView getEventDate() {
        return eventDate;
    }

    public void setEventDate(TextView eventDate) {
        this.eventDate = eventDate;
    }

    public TextView getEventPlace() {
        return eventPlace;
    }

    public void setEventPlace(TextView eventPlace) {
        this.eventPlace = eventPlace;
    }

}
