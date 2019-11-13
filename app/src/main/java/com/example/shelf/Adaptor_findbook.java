package com.example.shelf;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adaptor_findbook extends RecyclerView.Adapter<Adaptor_findbook.Viewholder>  {

    private List<Modelclass_findbook> modelClassList_findbook;

    public Adaptor_findbook(List<Modelclass_findbook> modelClassList_findbook){
        this.modelClassList_findbook = modelClassList_findbook;
    }



    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_layout_find_book,viewGroup,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder viewholder, int position) {
        viewholder.imageView.setImageResource(modelClassList_findbook.get(position).getImageResource());
        viewholder.title.setText(modelClassList_findbook.get(position).getTitle());
        viewholder.author.setText(modelClassList_findbook.get(position).getAuthor());
        viewholder.edition.setText(modelClassList_findbook.get(position).getEdition());
    }

    @Override
    public int getItemCount() {
        return modelClassList_findbook.size();
    }


    class Viewholder extends RecyclerView.ViewHolder{

        private ImageView imageView;
        private TextView title;
        private TextView author;
        private TextView edition;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            title = itemView.findViewById(R.id.titleTV);
            author = itemView.findViewById(R.id.author);
            edition = itemView.findViewById(R.id.edition);
        }

        private void setData(int resource, String titleText, String authorText, String editionText){
            imageView.setImageResource(resource);
            title.setText(titleText);
            author.setText(authorText);
            edition.setText(editionText);
        }
    }

}

