package com.mmt.model.split;

import com.mmt.model.User;

public abstract class Splitwise {
    User user;
    int amount;

    public Splitwise(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
