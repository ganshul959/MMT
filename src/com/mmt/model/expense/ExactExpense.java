package com.mmt.model.expense;

import com.mmt.model.User;
import com.mmt.model.expense.Expense;
import com.mmt.model.split.EqualsSplitwise;
import com.mmt.model.split.ExactSplitwise;
import com.mmt.model.split.Splitwise;

import java.util.List;

public class ExactExpense extends Expense {
    public ExactExpense(int amount, User paidBy, List<Splitwise> split) {
        super(amount, paidBy, split);
    }


    @Override
    public boolean checkExpenseType() {
        int checkTotalAmount = 0;
        for (Splitwise split : getSplit()) {
            if (!(split instanceof ExactSplitwise)) {
                return false;
            }
            checkTotalAmount += split.getAmount();
        }

        return getAmount() == checkTotalAmount;
    }
}
