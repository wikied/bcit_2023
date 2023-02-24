package com.springserver.api.model;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.Instant;

@Entity
@Table(name = "sub_categories", indexes = {
        @Index(name = "fk_sub_categories_genders1_idx", columnList = "genders_gender_id"),
        @Index(name = "fk_sub_categories_styles1_idx", columnList = "styles_style_id"),
        @Index(name = "fk_sub_categories_colours1_idx", columnList = "colours_colour_id"),
        @Index(name = "fk_sub_categories_size1_idx", columnList = "size_size_id")
})
public class SubCategory {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name="uuid", strategy = "uuid")
    @Column(name = "sub_cat_id", nullable = false, length = 32)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "size_size_id", nullable = false)
    private Size sizeSize;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "colours_colour_id", nullable = false)
    private Colour coloursColour;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "styles_style_id", nullable = false)
    private Style stylesStyle;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "genders_gender_id", nullable = false)
    private Gender gendersGender;

    @Column(name = "name", nullable = false)
    private String name;

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

    public Size getSizeSize() {
        return sizeSize;
    }

    public void setSizeSize(Size sizeSize) {
        this.sizeSize = sizeSize;
    }

    public Colour getColoursColour() {
        return coloursColour;
    }

    public void setColoursColour(Colour coloursColour) {
        this.coloursColour = coloursColour;
    }

    public Style getStylesStyle() {
        return stylesStyle;
    }

    public void setStylesStyle(Style stylesStyle) {
        this.stylesStyle = stylesStyle;
    }

    public Gender getGendersGender() {
        return gendersGender;
    }

    public void setGendersGender(Gender gendersGender) {
        this.gendersGender = gendersGender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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