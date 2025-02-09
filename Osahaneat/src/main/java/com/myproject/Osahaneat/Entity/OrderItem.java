package com.myproject.Osahaneat.Entity;

import com.myproject.Osahaneat.Entity.Keys.KeyOrderItem;
import jakarta.persistence.*;

import java.util.Date;

@Entity(name = "OrderItem")
public class OrderItem {
    @EmbeddedId
    private KeyOrderItem keys;

    @ManyToOne
    @JoinColumn(name = "order_id", insertable = false, updatable = false)
    private Orders orders;

    @ManyToOne
    @JoinColumn(name = "food_id", insertable = false, updatable = false)
    private Food food;

    @Column(name = "create_date")
    private Date createDate;

    public KeyOrderItem getKeys() {
        return keys;
    }

    public void setKeys(KeyOrderItem keys) {
        this.keys = keys;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
