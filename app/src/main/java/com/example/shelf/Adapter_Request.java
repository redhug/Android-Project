package com.example.shelf;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

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
        /*viewholder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), UserActivity.class);
                intent.putExtra("email", useremail);
                intent.putExtra("title", title);
                view.getContext().startActivity(intent);
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return modelClassList_Request.size();
    }


    class Viewholder extends RecyclerView.ViewHolder{

        private TextView title;
        private TextView useremail;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titleTV);
            useremail = itemView.findViewById(R.id.useremail);
        }

        private void setData(String titleText, String useremailText){
            title.setText(titleText);
            useremail.setText(useremailText);
        }
    }

}