package com.example.budget_fixer;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;

public class Utils {

    private static Utils instance;
    private ArrayList<Budget> allBudgets;
    private ArrayList<Member> hytteturMember;
    private ArrayList<Member> sydenturMember;


    private Utils(){
        allBudgets = new ArrayList<>();
        hytteturMember = new ArrayList<>();
        sydenturMember = new ArrayList<>();

        initData();
    }


    private void initData() {
        ArrayList<String> hytteturNames = new ArrayList<>();
        ArrayList<String> sydenturNames = new ArrayList<>();

        hytteturMember.add(new Member("Aksel"));
        hytteturMember.add(new Member("Ivelin"));
        hytteturMember.add(new Member("Fredrik"));

        for(Member m: hytteturMember){
            hytteturNames.add(m.getName());
        }

        sydenturMember.add(new Member("Aksel"));
        sydenturMember.add(new Member("Tollef"));
        sydenturMember.add(new Member("Mamma"));
        sydenturMember.add(new Member("Rolf"));

        for(Member m: sydenturMember){
            sydenturNames.add(m.getName());
        }

        allBudgets.add(new Budget("Hyttetur", "Aksel", hytteturMember));
        allBudgets.add(new Budget("Sydentur", "Rolf", sydenturMember));

    }

    public static Utils getInstance() {
        if(null != instance){
            return instance;
        }
        else{
            instance = new Utils();
            return instance;
        }
    }

    public ArrayList<Budget> getAllBudgets() {
        return allBudgets;
    }

    private void AddBudgetMembers(ArrayList<String>names){

    }


    public boolean addBudget(Budget budget){
        return allBudgets.add(budget);
    }

    public boolean removeBudget(Budget budget) {
        return allBudgets.remove(budget);
    }


/*    public boolean removeBudget(Budget budget){
        return allBudgets.add(budget);
    }

    public boolean removeBudget(Budget budget) {
        return allBudgets.remove(budget);
    }*/


}
