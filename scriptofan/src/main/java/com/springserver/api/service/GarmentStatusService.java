package com.springserver.api.service;


import com.springserver.api.model.GarmentStatus;
import com.springserver.api.repository.GarmentStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class GarmentStatusService {

    @Autowired
    private GarmentStatusRepository garmentStatusRepository;

    public GarmentStatus createGarmentStatus(Short posted,
                                             Boolean inStore,
                                             Boolean pendingPurchase,
                                             Boolean purchased,
                                             String createdBy){
        GarmentStatus g = new GarmentStatus();

        g.setPosted(posted);
        g.setInStore(inStore);
        g.setPendingPurchase(pendingPurchase);
        g.setPurchased(purchased);
        g.setCreatedBy(createdBy);

        return garmentStatusRepository.save(g);
    }

    public GarmentStatus update(GarmentStatus garmentStatus,
                                GarmentStatus garmentStatusRequest,
                                String updatedBy){

        garmentStatus.setPosted(garmentStatusRequest.getPosted());
        garmentStatus.setInStore(garmentStatusRequest.getInStore());
        garmentStatus.setPendingPurchase(garmentStatusRequest.getPendingPurchase());
        garmentStatus.setPurchased(garmentStatusRequest.getPurchased());
        garmentStatus.setUpdatedBy(updatedBy);
        garmentStatus.setUpdateTime(Instant.now());

        return garmentStatusRepository.save(garmentStatus);
    }
    public void delete(GarmentStatus garmentStatus, String deletedBy){
        garmentStatus.setDeletedBy(deletedBy);
        garmentStatus.setDeleteTime(Instant.now());

        garmentStatusRepository.save(garmentStatus);
    }

}
