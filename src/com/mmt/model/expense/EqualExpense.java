package com.mmt.model.expense;

import com.mmt.model.User;
import com.mmt.model.expense.Expense;
import com.mmt.model.split.EqualsSplitwise;
import com.mmt.model.split.Splitwise;

import java.util.List;

public class EqualExpense extends Expense {


    public EqualExpense(int amount, User paidBy, List<Splitwise> split) {
        super(amount, paidBy, split);
    }

    @Override
    public boolean checkExpenseType() {
        for (Splitwise split : getSplit()) {
            return split instanceof EqualsSplitwise;
        }
        return true;
    }
}
