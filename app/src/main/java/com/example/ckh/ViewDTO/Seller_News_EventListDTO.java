package com.example.ckh.ViewDTO;

/**
 * Created by HOME on 2016-10-24.
 */
public class Seller_News_EventListDTO {
    private String eventImage;
    private String eventName;
    private String eventDate;
    private String eventPlace;

    public String getEventImage() {
        return eventImage;
    }

    public void setEventImage(String eventImage) {
        this.eventImage = eventImage;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventPlace() {
        return eventPlace;
    }

    public void setEventPlace(String eventPlace) {
        this.eventPlace = eventPlace;
    }

    public Seller_News_EventListDTO(String eventImage, String eventName, String eventDate, String eventPlace) {
        this.eventImage = eventImage;
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.eventPlace = eventPlace;
    }

    public Seller_News_EventListDTO() {

    }

}
