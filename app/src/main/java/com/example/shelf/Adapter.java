package com.example.shelf;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.Viewholder> {

    private List<ModelClass> modelClassList;

    public Adapter(List<ModelClass> modelClassList){

        this.modelClassList = modelClassList;
    }



    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_layout,viewGroup,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder viewholder, int position) {
        viewholder.imageView.setImageResource(modelClassList.get(position).getImageResource());
        viewholder.title.setText(modelClassList.get(position).getTitle());
        viewholder.body.setText(modelClassList.get(position).getBody());
        viewholder.body2.setText(modelClassList.get(position).getBody2());
    }

    @Override
    public int getItemCount() {
        return modelClassList.size();
    }


    class Viewholder extends RecyclerView.ViewHolder{

        private ImageView imageView;
        private TextView title;
        private TextView body;
        private TextView body2;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            title = itemView.findViewById(R.id.title);
            body = itemView.findViewById(R.id.body);
            body2 = itemView.findViewById(R.id.body1);
        }

        private void setData(int resource, String titleText, String bodyText, String bodyText1){
            imageView.setImageResource(resource);
            title.setText(titleText);
            body.setText(bodyText);
            body2.setText(bodyText1);
        }
    }

}