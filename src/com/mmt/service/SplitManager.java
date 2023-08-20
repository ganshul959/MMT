package com.mmt.service;

import com.mmt.model.SplitType;
import com.mmt.model.User;
import com.mmt.model.expense.Expense;
import com.mmt.model.split.Splitwise;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SplitManager {
    List<Expense> expenses;
    public Map<String, User> userMap;
    Map<String, Map<String, Integer> > balanceSheet;

    public SplitManager() {
        expenses = new ArrayList<Expense>();
        userMap = new HashMap<String, User>();
        balanceSheet = new HashMap<String, Map<String, Integer>>();

    }

    public void addUser(User user) {
        userMap.put(user.getUserId(), user);
        balanceSheet.put(user.getUserId(), new HashMap<String, Integer>());
    }

    public void addExpense(SplitType splitType, int amount, String paidBy, List<Splitwise> splits ) {
        Expense expense = SplitService.addExpense(splitType, amount, userMap.get(paidBy), splits);
        expenses.add(expense);

        for (Splitwise split : expense.getSplit()) {
            String paidTo = split.getUser().getUserId();
            Map<String, Integer> balances = balanceSheet.get(paidBy);
            if (!balances.containsKey(paidTo)) {
                balances.put(paidTo, 0);
            }
            balances.put(paidTo, balances.get(paidTo) + split.getAmount());

            balances = balanceSheet.get(paidTo);
            if (!balances.containsKey(paidBy)) {
                balances.put(paidBy, 0);
            }
            balances.put(paidBy, balances.get(paidBy) - split.getAmount());
        }
    }

    public void showBalance(String userId) {
        boolean isEmpty = true;
        for (Map.Entry<String, Integer> userBalance : balanceSheet.get(userId).entrySet()) {
            if (userBalance.getValue() != 0) {
                isEmpty = false;
                printBalance(userId, userBalance.getKey(), userBalance.getValue());
            }
        }

        if (isEmpty) {
            System.out.println("No balances");
        }
    }

    public void showAllBalances() {
        boolean isEmpty = true;
        for (Map.Entry<String, Map<String, Integer>> allBalances : balanceSheet.entrySet()) {
            for (Map.Entry<String, Integer> userBalance : allBalances.getValue().entrySet()) {
                if (userBalance.getValue() > 0) {
                    isEmpty = false;
                    printBalance(allBalances.getKey(), userBalance.getKey(), userBalance.getValue());
                }
            }
        }

        if (isEmpty) {
            System.out.println("No balances");
        }
    }

    private void printBalance(String user1, String user2, int amount) {
        String user1Name = userMap.get(user1).getName();
        String user2Name = userMap.get(user2).getName();
        if (amount < 0) {
            System.out.println(user1Name + " owes " + user2Name + ": " + amount);
        } else if (amount > 0) {
            System.out.println(user2Name + " owes " + user1Name + ": " + amount);
        }
    }

}
