package com.mmt.model.split;

import com.mmt.model.User;
import com.mmt.model.split.Splitwise;

public class ExactSplitwise extends Splitwise {

    public ExactSplitwise(User user, int amount) {
        super(user);
        this.amount = amount;
    }


}
