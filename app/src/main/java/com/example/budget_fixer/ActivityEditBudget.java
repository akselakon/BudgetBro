package com.example.budget_fixer;

import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.function.ToDoubleBiFunction;

public class ActivityEditBudget extends AppCompatActivity {

    private RecyclerView recyclerView;
    private BugdetRecViewAdapter adapter;
    private int desiredindex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_budget);


        //recyclerView = findViewById(R.id.booksRecView);
        adapter = new BugdetRecViewAdapter(this);

        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,true);
        layoutManager.scrollToPositionWithOffset(Budget.getIdIncrement()-1, -30);
        recyclerView.setLayoutManager(layoutManager);
        adapter.setBudgets(Utils.getInstance().getAllBudgets());

        //TODO: Fiks livet

    }


}