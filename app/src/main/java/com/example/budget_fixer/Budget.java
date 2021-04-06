package com.example.budget_fixer;

import java.util.ArrayList;

public class Budget {

    private static int idIncrement = 0;
    private int budgetId;

    private String budgetName;
    private String budgetHost;
    private ArrayList<Member> budgetMembers;
    private ArrayList<Payment> paymentList;




    public Budget(String budgetName, String budgetHost, ArrayList<Member> budgetMembers) {
        this.budgetId = idIncrement;
        idIncrement++;
        this.budgetName = budgetName;
        this.budgetHost = budgetHost;
        this.budgetMembers = budgetMembers;
    }

    public void addMember(String member){

    }

    public void removeMember(String member){

    }


    public int getbudgetId() {
        return budgetId;
    }

/*    public void setId(int id) {
        this.id = id;
    }*/


    public String getBudgetName() {
        return budgetName;
    }

    public void setBudgetName(String budgetName) {
        this.budgetName = budgetName;
    }


    public String getBudgetHost() {
        return budgetHost;
    }

    public void setBudgetHost(String budgetHost) {
        this.budgetHost = budgetHost;
    }



    public ArrayList<Member> getbudgetMembers() {
        return budgetMembers;
    }

    public void setMembers(ArrayList<Member> budgetMembers) {
        this.budgetMembers = budgetMembers;
    }

    public static int getIdIncrement() {
        return idIncrement;
    }



    public ArrayList<Payment> getPaymentList() {
        return paymentList;
    }

    public boolean addPayment(Payment payment) {
        return paymentList.add(payment);
    }
    public boolean removePayment(Payment payment) {
        return paymentList.remove(payment);
    }

}
