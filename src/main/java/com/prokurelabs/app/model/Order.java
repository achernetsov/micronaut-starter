package com.prokurelabs.app.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "order")
public class Order {
    private Order() {
    }

    public Order(@NotNull String ownerId) {
        this.ownerId = ownerId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @NotNull
    @Column(name = "name", nullable = false, unique = true)
    String ownerId;

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

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", ownerId='" + ownerId + '\'' +
                '}';
    }
}
