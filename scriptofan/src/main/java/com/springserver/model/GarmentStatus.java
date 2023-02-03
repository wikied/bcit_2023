package com.springserver.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;

@Entity
@Table(name = "garment_statuses")
public class GarmentStatus {
    @Id
    @Column(name = "garment_status_id", nullable = false, length = 32)
    private String id;

    @Column(name = "posted", columnDefinition = "TINYINT UNSIGNED not null")
    private Short posted;

    @Column(name = "in_store")
    private Boolean inStore;

    @Column(name = "pending_purchase")
    private Boolean pendingPurchase;

    @Column(name = "purchased")
    private Boolean purchased;

    @Column(name = "create_time")
    private Instant createTime;

    @Column(name = "update_time")
    private Instant updateTime;

    @Column(name = "delete_time")
    private Instant deleteTime;

    @Column(name = "created_by", length = 50)
    private String createdBy;

    @Column(name = "updated_by", length = 50)
    private String updatedBy;

    @Column(name = "deleted_by", length = 50)
    private String deletedBy;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Short getPosted() {
        return posted;
    }

    public void setPosted(Short posted) {
        this.posted = posted;
    }

    public Boolean getInStore() {
        return inStore;
    }

    public void setInStore(Boolean inStore) {
        this.inStore = inStore;
    }

    public Boolean getPendingPurchase() {
        return pendingPurchase;
    }

    public void setPendingPurchase(Boolean pendingPurchase) {
        this.pendingPurchase = pendingPurchase;
    }

    public Boolean getPurchased() {
        return purchased;
    }

    public void setPurchased(Boolean purchased) {
        this.purchased = purchased;
    }

    public Instant getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public Instant getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
    }

    public Instant getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Instant deleteTime) {
        this.deleteTime = deleteTime;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(String deletedBy) {
        this.deletedBy = deletedBy;
    }

}