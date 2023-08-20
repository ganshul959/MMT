package com.mmt.model.expense;

import com.mmt.model.User;
import com.mmt.model.split.Splitwise;

import java.util.List;

public abstract class  Expense {

    String expenseId;
    int amount;
    User paidBy;
    List<Splitwise> splitList;

    public Expense(int amount, User paidBy, List<Splitwise>  splitList) {
        this.amount = amount;
        this.paidBy = paidBy;
        this.splitList = splitList;
    }

    public String getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(String expenseId) {
        this.expenseId = expenseId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public User getPaidBy() {
        return paidBy;
    }

    public void setPaidBy(User paidBy) {
        this.paidBy = paidBy;
    }

    public List<Splitwise> getSplit() {
        return splitList;
    }

    public void setSplit(List<Splitwise> splitList) {
        this.splitList = splitList;
    }

    public abstract boolean checkExpenseType();
}
