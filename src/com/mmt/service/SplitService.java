package com.mmt.service;

import com.mmt.model.*;
import com.mmt.model.expense.EqualExpense;
import com.mmt.model.expense.Expense;
import com.mmt.model.expense.PercentExpense;
import com.mmt.model.split.EqualsSplitwise;
import com.mmt.model.split.PercentSplitwise;
import com.mmt.model.split.Splitwise;

import java.util.List;

public class SplitService {


    public static Expense addExpense(SplitType splitType, int amount, User whoPaid, List<Splitwise> splitList){
        switch(splitType){
            case EQUALS:
                int size = splitList.size();
                int splitAmount = amount/size ;

                for(Splitwise sp : splitList){
                    sp.setAmount(splitAmount);
                }
                return new EqualExpense(amount, whoPaid, splitList);


            case EXACT:
                return new EqualExpense(amount,whoPaid, splitList);


            case PERCENTAGE:
                for (Splitwise split : splitList) {
                    PercentSplitwise percentSplit = (PercentSplitwise) split;
                    split.setAmount((amount*percentSplit.getPercent())/100);
                }
                return new PercentExpense(amount, whoPaid, splitList);

            default:
               return null;
        }

    }
}
