package com.mmt.model.split;

import com.mmt.model.User;

public class PercentSplitwise extends Splitwise{
    int percent;
    public PercentSplitwise(User user, int percent) {
        super(user);
        this.percent = percent;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }
}
