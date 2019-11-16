package com.example.shelf;

import android.content.Intent;
import android.graphics.Color;
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
        viewholder.author.setText(modelClassList.get(position).getAuthor());
        viewholder.edition.setText(modelClassList.get(position).getEdition());
        final String edition=modelClassList.get(position).getEdition();
        final String author=modelClassList.get(position).getAuthor();
        final String title=modelClassList.get(position).getTitle();
        final int imageView=modelClassList.get(position).getImageResource();
        viewholder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(view.getContext(), BookDetails.class);
                in.putExtra("imageView", imageView);
                in.putExtra("title",title);
                in.putExtra("author", author);
                in.putExtra("edition", edition);
                view.getContext().startActivity(in);
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelClassList.size();
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
            author = itemView.findViewById(R.id.authorTV);
            edition = itemView.findViewById(R.id.editionTV);
            itemView.setBackgroundColor(Color.WHITE);
        }

        private void setData(int resource, String titleText, String authorText, String editionText){
            imageView.setImageResource(resource);
            title.setText(titleText);
            author.setText(authorText);
            edition.setText(editionText);
        }
    }

}