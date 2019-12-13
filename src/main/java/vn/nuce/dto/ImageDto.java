package vn.nuce.dto;

import java.io.Serializable;

public class ImageDto implements Serializable {
    private Long imageId;

    private String imageUrl;

    private TourDto tourEntity;

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public TourDto getTourEntity() {
        return tourEntity;
    }

    public void setTourEntity(TourDto tourEntity) {
        this.tourEntity = tourEntity;
    }
}
