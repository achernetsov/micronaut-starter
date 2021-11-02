package com.prokurelabs.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class OrderDraft {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String ownerId;

    private String description;

    private OrderDraft() {
    }

    public OrderDraft(@NotNull String ownerId, String description) {
        this.ownerId = ownerId;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "OrderDraft{" +
                "id=" + id +
                ", ownerId='" + ownerId + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
