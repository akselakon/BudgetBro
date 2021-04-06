package com.example.budget_fixer;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class BugdetRecViewAdapter extends RecyclerView.Adapter<BugdetRecViewAdapter.ViewHolder>{
    public static final String BUDGET_ID_KEY = "budgetId";

    private static final String TAG = "BudgetRecViewAdapter";
    private ArrayList<Budget> budgets = new ArrayList<>();
    private Context mContext;

    public BugdetRecViewAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_budget, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: Called");

        holder.txtBudgetName.setText(budgets.get(position).getBudgetName());
        holder.txtHost.setText(budgets.get(position).getBudgetHost());
        holder.txtId.setText(String.valueOf(budgets.get(position).getbudgetId()));

        String nameList = "";

        for(Member m: budgets.get(position).getbudgetMembers()){
            nameList += m.getName() +", ";
        }
        int nameLength = nameList.length()-2;
        holder.txtMembers.setText(nameList.substring(0, nameLength));


        holder.btnBudgetDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ActivityBudgetDetails.class);
                intent.putExtra(BUDGET_ID_KEY, budgets.get(position).getbudgetId());
                mContext.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return budgets.size();
    }

    public void setBudgets(ArrayList<Budget> budgets){
        this.budgets = budgets;
        notifyDataSetChanged();
    }



    public class ViewHolder extends RecyclerView.ViewHolder{
        private CardView parent;
        private TextView bugetNameTxt, txtBudgetName, hostTxt, txtHost, membersTxt, txtMembers, btnAddMember, btnDeleteMember, btnBudgetDetails, btnDeleteBudget, budgetOverviewTxt, idTxt, txtId;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            parent = itemView.findViewById(R.id.parent);
            bugetNameTxt = itemView.findViewById(R.id.budgetDescriptionTxt);
            txtBudgetName = itemView.findViewById(R.id.txtPaymentDescription);
            hostTxt = itemView.findViewById(R.id.payerTxt);
            txtHost = itemView.findViewById(R.id.txtPayer);
            membersTxt = itemView.findViewById(R.id.membersTxt);
            txtMembers = itemView.findViewById(R.id.txtMembers);

            btnAddMember = itemView.findViewById(R.id.btnAddMember);
            btnDeleteMember = itemView.findViewById(R.id.btnDeleteMember);
            btnBudgetDetails = itemView.findViewById(R.id.btnBudgetDetails);
            btnDeleteBudget = itemView.findViewById(R.id.btnDeletePayment);

            txtId = itemView.findViewById(R.id.txtId);


            btnAddMember.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Intent intent = new Intent()
                }
            });

            btnDeleteMember.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });



            btnDeleteBudget.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Budget budget = budgets.get(getAdapterPosition());
                    String budgetName = budget.getBudgetName();
                    AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                    builder.setMessage("Are you sure you want to delete "+budgetName);

                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Utils.getInstance().removeBudget(budgets.get(getAdapterPosition()));
                            Toast.makeText(mContext, "Budget removed", Toast.LENGTH_SHORT).show();
                            notifyDataSetChanged();
                        }
                    });

                    builder.create().show();

                }
            });
        }
    }

}
