package com.springserver.api.service;

import com.springserver.api.model.GarmentImage;
import com.springserver.api.model.User;
import com.springserver.api.repository.GarmentImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class GarmentImageService {

    @Autowired
    private GarmentImageRepository garmentImageRepository;

    public GarmentImage createGarmentImage(String imageUrl, String createdBy){
        GarmentImage g = new GarmentImage();

        g.setImageUrl(imageUrl);
        g.setCreatedBy(createdBy);

        return garmentImageRepository.save(g);
    }

    public GarmentImage update(GarmentImage garmentImage, GarmentImage garmentImageRequest, String updatedBy){
        garmentImage.setImageUrl(garmentImageRequest.getImageUrl());
        garmentImage.setUpdatedBy(updatedBy);
        garmentImage.setUpdateTime(Instant.now());

        return garmentImageRepository.save(garmentImage);
    }

    public void delete(GarmentImage garmentImage, String deletedBy){
        garmentImage.setDeletedBy(deletedBy);
        garmentImage.setDeleteTime(Instant.now());

        garmentImageRepository.save(garmentImage);
    }
}
