package com.example.shelf;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class BookAdapter extends RecyclerView.Adapter<BookAdapter.Viewholder> {

    private List<Model> mymodel;

    public BookAdapter(List<Model> mymodel) {
        this.mymodel = mymodel;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.book_request,viewGroup,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
       String text1=mymodel.get(position).getUsername();
       String text2=mymodel.get(position).getBookname();
       //Button acceptBTN=mymodel.get(position).getButton();
        holder.setData(text1,text2);

    }

    @Override
    public int getItemCount() {
        return mymodel.size();
    }

    class Viewholder extends RecyclerView.ViewHolder{

        private TextView text1;
        private TextView text2;
        //private Button acceptBTN;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            text1 = itemView.findViewById(R.id.booktitle);
            text2= itemView.findViewById(R.id.username);
            //acceptBTN=itemView.findViewById(R.id.buttonOK);

        }
        private void setData(String booktext1, String booktext2){
            text1.setText(booktext1);
            text2.setText(booktext2);
            //acceptBTN.setText(button);
        }
    }
}
