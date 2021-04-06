package com.example.budget_fixer;

public class Member {

    private String name;
    private int memberBalance;
    private int memberId;
    private static int idIncrement = 0;

    public Member(String name) {
        idIncrement++;
        memberId = idIncrement;
        this.name = name;
        this.memberBalance = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBalance() {
        return memberBalance;
    }

    public void changeBalance(int balance) {
        this.memberBalance += balance;
    }
}
