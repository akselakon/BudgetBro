package com.example.budget_fixer;

import android.content.Intent;
import android.icu.math.BigDecimal;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class ActivityAddPayment extends AppCompatActivity {

    private EditText editPaymentDescription, editPaymentSum;
    private Spinner spinnerSelectPayer, spinnerSelectPaymentMembers;
    private TextView txtPaymentMembers;
    private MaterialButton btnSubmitPayment;
    private boolean isSpinnerInitalPayer = true;
    private boolean isSpinnerInitalMember = true;

    public static final String BUDGET_ID_KEY = "budgetId";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_payment);

        initGui();
        Intent intent = getIntent();
        final int budgetId = intent.getIntExtra(BUDGET_ID_KEY, -1);




        ArrayList<Member> allPaymentMembers = new ArrayList<>();
        final ArrayList<String> allPaymentMembersName = new ArrayList<>();
        final ArrayList<String> allPaymentMembersPayer = new ArrayList<>();

        ArrayList<Member> allMembers = new ArrayList<>();
        for(Budget b: Utils.getInstance().getAllBudgets()){
            if (b.getbudgetId()==budgetId){
                allMembers = b.getbudgetMembers();
            }
        }
        allPaymentMembersName.add("");
        allPaymentMembersPayer.add("");
        allPaymentMembersName.add("All");
        for(Member m: allMembers){
            allPaymentMembersName.add(m.getName());
            allPaymentMembersPayer.add(m.getName());

        }


        ArrayAdapter<String> allMembersAdapterPayer = new ArrayAdapter<>(ActivityAddPayment.this, android.R.layout.simple_list_item_1, allPaymentMembersPayer);
        ArrayAdapter<String> allMembersAdapterMembers = new ArrayAdapter<>(ActivityAddPayment.this, android.R.layout.simple_list_item_1, allPaymentMembersName);

        spinnerSelectPayer.setAdapter(allMembersAdapterPayer);
        spinnerSelectPaymentMembers.setAdapter(allMembersAdapterMembers);


        final ArrayList<String> currentPaymentMembersStr = new ArrayList<>();



        spinnerSelectPayer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(isSpinnerInitalPayer){
                    isSpinnerInitalPayer = false;
                }
                else{
/*                    if(!allPaymentMembersPayer.get(position).equals("")){
                        Toast.makeText(ActivityAddPayment.this, allPaymentMembersPayer.get(position)+" added", Toast.LENGTH_SHORT).show();
                    }*/
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerSelectPaymentMembers.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(isSpinnerInitalMember){
                    isSpinnerInitalMember = false;
                }
                else{
                    if(!allPaymentMembersName.get(position).equals("")){
                        //Toast.makeText(ActivityAddPayment.this, allPaymentMembersName.get(position)+" added", Toast.LENGTH_SHORT).show();
                        currentPaymentMembersStr.add(allPaymentMembersName.get(position));
                        txtPaymentMembers.setText(currentPaymentMembersStr.toString());
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


            btnSubmitPayment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!editPaymentSum.getText().toString().equals("")&&!editPaymentDescription.getText().toString().equals("")&&!spinnerSelectPayer.getSelectedItem().toString().equals("")&&!txtPaymentMembers.getText().toString().equals("")){

                        String description = editPaymentDescription.getText().toString();
                        int paymentSum = Integer.valueOf(editPaymentSum.getText().toString());

                        Payment payment = new Payment(description, spinnerSelectPayer.getSelectedItem().toString(), currentPaymentMembersStr, paymentSum, budgetId);
                        for(Budget b: Utils.getInstance().getAllBudgets()){
                            if (b.getbudgetId()==budgetId){
                                b.addPayment(payment);
                            }
                        }

                        Toast.makeText(ActivityAddPayment.this, currentPaymentMembersStr.toString(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ActivityAddPayment.this, ActivityBudgetDetails.class);
                        intent.putExtra(BUDGET_ID_KEY, budgetId);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(ActivityAddPayment.this, "Errorr", Toast.LENGTH_SHORT).show();
                    }
                }
            });



    }

    private void initGui(){
        editPaymentDescription=findViewById(R.id.editPaymentDescription);
        editPaymentSum=findViewById(R.id.editPaymentSum);
        spinnerSelectPayer=findViewById(R.id.spinnerSelectPayer);
        spinnerSelectPaymentMembers=findViewById(R.id.spinnerSelectPaymentMembers);
        txtPaymentMembers=findViewById(R.id.txtPaymentMembers);
        btnSubmitPayment=findViewById(R.id.btnSubmitPayment);
    }


}