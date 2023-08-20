package com.mmt.model.expense;

import com.mmt.model.User;
import com.mmt.model.expense.Expense;
import com.mmt.model.split.ExactSplitwise;
import com.mmt.model.split.PercentSplitwise;
import com.mmt.model.split.Splitwise;

import java.util.List;

public class PercentExpense extends Expense {
    public PercentExpense(int amount, User paidBy, List<Splitwise> split) {
        super(amount, paidBy, split);
    }

    @Override
    public boolean checkExpenseType() {

            int totalPercentage = 0;
            for (Splitwise split : getSplit()) {
                if (!(split instanceof PercentSplitwise)) {
                    return false;
                }
                PercentSplitwise percentSplitwise = (PercentSplitwise) split;
                totalPercentage += percentSplitwise.getPercent();
            }

            return totalPercentage == 100;

    }
}
