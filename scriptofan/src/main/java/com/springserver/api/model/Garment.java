package com.springserver.api.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "garments", indexes = {
        @Index(name = "seller", columnList = "seller_id"),
        @Index(name = "fk_garments_garment_images_1_idx", columnList = "garment_image_id"),
        @Index(name = "fk_garments_garment_statuses1_idx", columnList = "garment_status_id"),
        @Index(name = "category", columnList = "category_id")
})
public class Garment {
    @Id
    @Column(name = "garment_id", nullable = false, length = 32)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "seller_id", nullable = false)
    private Seller seller;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "garment_image_id", nullable = false)
    private GarmentImage garmentImage;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "garment_status_id", nullable = false)
    private GarmentStatus garmentStatus;

    @Column(name = "description")
    private String description;

    @Column(name = "material")
    private String material;

    @Column(name = "defects")
    private String defects;

    @Column(name = "price")
    private Integer price;

    @Column(name = "co2_saved", precision = 10, scale = 2)
    private BigDecimal co2Saved;

    @Column(name = "garment_rating")
    private Integer garmentRating;

    @Column(name = "details")
    private String details;

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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public GarmentImage getGarmentImage() {
        return garmentImage;
    }

    public void setGarmentImage(GarmentImage garmentImage) {
        this.garmentImage = garmentImage;
    }

    public GarmentStatus getGarmentStatus() {
        return garmentStatus;
    }

    public void setGarmentStatus(GarmentStatus garmentStatus) {
        this.garmentStatus = garmentStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getDefects() {
        return defects;
    }

    public void setDefects(String defects) {
        this.defects = defects;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public BigDecimal getCo2Saved() {
        return co2Saved;
    }

    public void setCo2Saved(BigDecimal co2Saved) {
        this.co2Saved = co2Saved;
    }

    public Integer getGarmentRating() {
        return garmentRating;
    }

    public void setGarmentRating(Integer garmentRating) {
        this.garmentRating = garmentRating;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
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