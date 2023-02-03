package com.springserver.model;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "printing_labels", indexes = {
        @Index(name = "printing_labels_ibjk_1_idx", columnList = "garment_id")
})
public class PrintingLabel {
    @Id
    @Column(name = "label_id", nullable = false, length = 32)
    private String id;

    @Column(name = "print_date")
    private Instant printDate;

    @Column(name = "aisle_number")
    private Integer aisleNumber;

    @Column(name = "item_barcode")
    private String itemBarcode;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "garment_id", nullable = false)
    private Garment garment;

    @Column(name = "create_time")
    private Instant createTime;

    @Column(name = "update_time")
    private Instant updateTime;

    @Column(name = "`delete_time'`")
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

    public Instant getPrintDate() {
        return printDate;
    }

    public void setPrintDate(Instant printDate) {
        this.printDate = printDate;
    }

    public Integer getAisleNumber() {
        return aisleNumber;
    }

    public void setAisleNumber(Integer aisleNumber) {
        this.aisleNumber = aisleNumber;
    }

    public String getItemBarcode() {
        return itemBarcode;
    }

    public void setItemBarcode(String itemBarcode) {
        this.itemBarcode = itemBarcode;
    }

    public Garment getGarment() {
        return garment;
    }

    public void setGarment(Garment garment) {
        this.garment = garment;
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