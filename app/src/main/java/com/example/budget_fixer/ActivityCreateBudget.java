package com.example.budget_fixer;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class ActivityCreateBudget extends AppCompatActivity {
    private TextView createBudgetTxt, seeMemberTxt, txtMemberList;
    private EditText editMemberName, editBugdetName, editHostName;
    private Button btnSubmitBudget, btnSubmitNewMember;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_budget);
        final ArrayList<String> memberNames = new ArrayList<>();

        final String hostName;

        initGui();

/*        ArrayAdapter<String> namesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, names);
        spinnerSelectHost.setAdapter(namesAdapter);

        spinnerSelectHost.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ActivityCreateBudget.this, "selected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });*/


        btnSubmitNewMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!editMemberName.getText().toString().equals("")){
                    //Toast.makeText(ActivityCreateBudget.this, editMemberName.getText().toString(), Toast.LENGTH_SHORT).show();
                    if(txtMemberList.getText().toString().equals("")){
                        txtMemberList.append(editMemberName.getText().toString());
                        memberNames.add(editMemberName.getText().toString());
                    }
                    else{
                        txtMemberList.append(", "+editMemberName.getText().toString());
                        memberNames.add(editMemberName.getText().toString());
                    }
                    Toast.makeText(ActivityCreateBudget.this, editMemberName.getText().toString(), Toast.LENGTH_SHORT).show();
                    editMemberName.setText("");

                }
            }
        });

        btnSubmitBudget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!editHostName.getText().toString().equals("")&&memberNames.size()>0&&!editBugdetName.getText().toString().equals("")){
                    Toast.makeText(ActivityCreateBudget.this, editBugdetName.getText().toString()+ " is created", Toast.LENGTH_SHORT).show();

                    ArrayList<Member> members = new ArrayList<>();

                    for(String name: memberNames){
                        members.add(new Member(name));
                    }

                    Budget budget = new Budget(editBugdetName.getText().toString(), editHostName.getText().toString(), members);
                    Utils.getInstance().addBudget(budget);
                    Intent intent = new Intent(ActivityCreateBudget.this, ActivityEditBudget.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(ActivityCreateBudget.this, "Fill all text entries or add members", Toast.LENGTH_SHORT).show();
                }



            }
        });



    }

    private void initGui(){
        createBudgetTxt = findViewById(R.id.createBudgetTxt);
        seeMemberTxt = findViewById(R.id.seeMemberTxt);
        txtMemberList = findViewById(R.id.txtMemberList);
        editMemberName = findViewById(R.id.editMemberName);
        editBugdetName = findViewById(R.id.editBugdetName);
        btnSubmitBudget = findViewById(R.id.btnSubmitBudget);
        btnSubmitNewMember = findViewById(R.id.btnSubmitNewMember);
        editHostName = findViewById(R.id.editHostName);


    }
}