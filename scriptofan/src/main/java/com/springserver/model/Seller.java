package com.springserver.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "sellers", indexes = {
        @Index(name = "user_id", columnList = "user_id")
})
public class Seller {
    @Id
    @Column(name = "seller_id", nullable = false, length = 32)
    private String id;

    @Column(name = "seller_rating")
    private Integer sellerRating;

    @Column(name = "revenue", precision = 10, scale = 2)
    private BigDecimal revenue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

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

    public Integer getSellerRating() {
        return sellerRating;
    }

    public void setSellerRating(Integer sellerRating) {
        this.sellerRating = sellerRating;
    }

    public BigDecimal getRevenue() {
        return revenue;
    }

    public void setRevenue(BigDecimal revenue) {
        this.revenue = revenue;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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