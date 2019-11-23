package com.example.shelf;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import static android.content.Intent.FLAG_GRANT_WRITE_URI_PERMISSION;

public class Adapter_Request extends RecyclerView.Adapter<Adapter_Request.Viewholder>  {

    private List<ModelClass_Request> modelClassList_Request;

    public Adapter_Request(List<ModelClass_Request> modelClassList_Request){
        this.modelClassList_Request = modelClassList_Request;

    }

    @NonNull
    @Override
    public Adapter_Request.Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_layout_request,viewGroup,false);
        return new Adapter_Request.Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_Request.Viewholder viewholder, int position) {
        viewholder.title.setText(modelClassList_Request.get(position).getTitle());
        viewholder.useremail.setText(modelClassList_Request.get(position).getUseremail());
        final String useremail=modelClassList_Request.get(position).getUseremail();
        final String title=modelClassList_Request.get(position).getTitle();
        viewholder.title.setText(modelClassList_Request.get(position).getTitle());
        viewholder.useremail.setText(modelClassList_Request.get(position).getUseremail());

        viewholder.acceptButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String to[] = {useremail};
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/html");
                intent.putExtra(Intent.EXTRA_EMAIL, to);
                intent.putExtra(Intent.EXTRA_SUBJECT, "Subject "+title);
                intent.putExtra(Intent.EXTRA_TEXT, "your book request is accepted.");
                v.getContext().startActivity(Intent.createChooser(intent, "Send Email"));

            }
        });
        viewholder.rejectButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String to[] = {useremail};
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/html");
                intent.putExtra(Intent.EXTRA_EMAIL, to);
                intent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
                intent.putExtra(Intent.EXTRA_TEXT, "your book request is rejected.");
                v.getContext().startActivity(Intent.createChooser(intent, "Send Email"));

            }
        });
    }

    @Override
    public int getItemCount() {
        return modelClassList_Request.size();
    }


    class Viewholder extends RecyclerView.ViewHolder{

        private TextView title;
        private TextView useremail;
        private Button acceptButton,rejectButton;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titleTV);
            useremail = itemView.findViewById(R.id.useremail);
            acceptButton = itemView.findViewById(R.id.acceptBTN);
            rejectButton = itemView.findViewById(R.id.rejectBTN);

        }

        private void setData(String titleText, String useremailText){
            title.setText(titleText);
            useremail.setText(useremailText);
        }
    }

}