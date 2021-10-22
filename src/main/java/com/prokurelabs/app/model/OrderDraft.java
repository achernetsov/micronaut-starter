package com.prokurelabs.app.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "order_draft")
public class OrderDraft {
    private OrderDraft() {
    }

    public OrderDraft(@NotNull String ownerId, String description) {
        this.ownerId = ownerId;
        this.description = description;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "owner_id", nullable = false, length = 50)
    private String ownerId;

    @Column
    private String description;

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
