package com.mmt;

import com.mmt.model.User;
import com.mmt.model.split.EqualsSplitwise;
import com.mmt.model.split.ExactSplitwise;
import com.mmt.model.split.PercentSplitwise;
import com.mmt.model.split.Splitwise;
import com.mmt.service.SplitManager;
import com.mmt.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
            SplitManager SplitManager = new SplitManager();

            SplitManager.addUser(new User("u1", "User1", 21, "1123454322"));
            SplitManager.addUser(new User("u2", "User2", 23, "1123454322"));
            SplitManager.addUser(new User("u3", "User3", 26, "1123454322"));
            SplitManager.addUser(new User("u4", "User4", 23,  "1123454322"));

            Scanner scanner = new Scanner(System.in);
            while (true) {
                String command = scanner.nextLine();
                String[] commands = command.split(" ");
                String commandType = commands[0];

                switch (commandType) {
                    case "SHOW":
                        if (commands.length == 1) {
                            SplitManager.showAllBalances();
                        } else {
                            SplitManager.showBalance(commands[1]);
                        }
                        break;
                    case "EXPENSE":
                        String paidBy = commands[1];
                        int amount = Integer.parseInt(commands[2]);
                        int noOfUsers = Integer.parseInt(commands[3]);
                        String SplitType = commands[4 + noOfUsers];
                        List<Splitwise> splits = new ArrayList<>();
                        switch (SplitType) {
                            case "EQUAL":
                                for (int i = 0; i < noOfUsers; i++) {
                                    splits.add(new EqualsSplitwise(SplitManager.userMap.get(commands[4 + i])));
                                }
                                SplitManager.addExpense(com.mmt.model.SplitType.EQUALS, amount, paidBy, splits);
                                break;
                            case "EXACT":
                                for (int i = 0; i < noOfUsers; i++) {
                                    splits.add(new ExactSplitwise(SplitManager.userMap.get(commands[4 + i]), Integer.parseInt(commands[5 + noOfUsers + i])));
                                }
                                SplitManager.addExpense(com.mmt.model.SplitType.EXACT, amount, paidBy, splits);
                                break;
                            case "PERCENT":
                                for (int i = 0; i < noOfUsers; i++) {
                                    splits.add(new PercentSplitwise(SplitManager.userMap.get(commands[4 + i]), Integer.parseInt(commands[5 + noOfUsers + i])));
                                }
                                SplitManager.addExpense(com.mmt.model.SplitType.PERCENTAGE, amount, paidBy, splits);
                                break;
                        }
                        break;
                }
            }
        }
}