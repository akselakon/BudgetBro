package com.example.budget_fixer;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.android.material.button.MaterialButton;

public class ActivityBudgetDetails extends AppCompatActivity {

    private TextView txtBudgetDetails, txtBudgetId, txtMemberName, txtMemberBalance;
    private MaterialButton btnNewPayment;

    public static final String BUDGET_ID_KEY = "budgetId";

/*    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ActivityBudgetDetails.this, BugdetRecViewAdapter.class);
        startActivity(intent);
        //super.onBackPressed();
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_details);

        initGui();

        Intent intent = getIntent();
        final int budgetId = intent.getIntExtra(BUDGET_ID_KEY, -1);
        if (budgetId!=-1){
            txtBudgetId.setText(String.valueOf(budgetId));
        }
        else{
            txtBudgetId.setText("Nå skjedde det noe rart her");
        }

        for(Budget b: Utils.getInstance().getAllBudgets()){
            if(b.getbudgetId()==budgetId){
                txtBudgetDetails.setText("Budget Details for "+b.getBudgetName());
                String nameList = "\n";
                String balanceList = "\n";
                for(Member m: b.getbudgetMembers()){
                    nameList+= m.getName()+"\n\n";
                    balanceList+= m.getBalance()+"Kr \n\n";
                }
                txtMemberName.setText(nameList);
                txtMemberBalance.setText(balanceList);
            }
        }


        btnNewPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityBudgetDetails.this, ActivityAddPayment.class);
                intent.putExtra(BUDGET_ID_KEY, budgetId);
                startActivity(intent);
            }
        });



        //txtBudgetDetails.setText(Utils.);

    }

    private void initGui(){
        txtBudgetDetails = findViewById(R.id.txtBudgetDetails);
        txtBudgetId = findViewById(R.id.txtBudgetId);
        txtMemberName = findViewById(R.id.txtMemberName);
        txtMemberBalance = findViewById(R.id.txtMemberBalance);
        btnNewPayment = findViewById(R.id.btnNewPayment);
    }
}