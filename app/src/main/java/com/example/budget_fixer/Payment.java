package com.example.budget_fixer;

import android.icu.math.BigDecimal;

import java.util.ArrayList;

public class Payment{

    private String description;
    private String paymentHost;
    private ArrayList<String> paymentMembers;


    private int sum;


    public Payment(String description, String paymentHost, ArrayList<String> paymentMembers, Integer sum, final Integer budgetId) {
        this.description = description;
        this.paymentHost = paymentHost;
        this.paymentMembers = paymentMembers;

        for(Budget b: Utils.getInstance().getAllBudgets()){
            if (b.getbudgetId()==budgetId){

                for(Member m: b.getbudgetMembers()){

                    if(paymentMembers.contains("All")){
                        m.changeBalance(sum/b.getbudgetMembers().size());
                    }
                    else if(paymentMembers.contains(m.getName())){
                        m.changeBalance(sum/paymentMembers.size());
                    }

                    if(m.getName().equals(paymentHost)){
                        m.changeBalance(-sum);
                    }
                }


            }
        }
        this.sum = sum;


    }


}
