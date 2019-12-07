package com.example.shelf;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class Adapter_Request extends RecyclerView.Adapter<Adapter_Request.Viewholder> {

    private List<ModelClass_Request> modelClassList_Request;

    public Adapter_Request(List<ModelClass_Request> modelClassList_Request) {
        this.modelClassList_Request = modelClassList_Request;
    }

    @NonNull
    @Override
    public Adapter_Request.Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_layout_request, viewGroup, false);
        return new Adapter_Request.Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Adapter_Request.Viewholder viewholder, final int position) {
        viewholder.title.setText(modelClassList_Request.get(position).getTitle());
        viewholder.useremail.setText(modelClassList_Request.get(position).getUseremail());
        final String useremail = modelClassList_Request.get(position).getUseremail();
        final String title = modelClassList_Request.get(position).getTitle();
        final String obj = modelClassList_Request.get(position).getObjectid();
        viewholder.title.setText(modelClassList_Request.get(position).getTitle());
        viewholder.useremail.setText(modelClassList_Request.get(position).getUseremail());

        viewholder.acceptButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String to[] = {useremail};
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/html");
                intent.putExtra(Intent.EXTRA_EMAIL, to);
                intent.putExtra(Intent.EXTRA_SUBJECT, "Subject " + title);
                intent.putExtra(Intent.EXTRA_TEXT, "your book request is accepted.");
                v.getContext().startActivity(Intent.createChooser(intent, "Send Email"));
                deleterequest(obj, position);
            }
        });
        viewholder.rejectButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String to[] = {useremail};
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/html");
                intent.putExtra(Intent.EXTRA_EMAIL, to);
                intent.putExtra(Intent.EXTRA_SUBJECT, "Subject" + title);
                intent.putExtra(Intent.EXTRA_TEXT, "your book request is rejected.");
                v.getContext().startActivity(Intent.createChooser(intent, "Send Email"));
                deleterequest(obj, position);
            }
        });
    }

    private void deleterequest(String obj, int position) {
        final boolean deleteAttributesOnly = true;
        ParseQuery<ParseObject> query = ParseQuery.getQuery("request");
        query.getInBackground(obj, new GetCallback<ParseObject>() {
            public void done(ParseObject object, ParseException e) {
                if (e == null) {
                    if (deleteAttributesOnly) {
                        // If you want to undefine a specific field, do this:
                        object.remove("senderemail");
                        object.remove("title");
                        object.remove("recepientemial");
                        object.remove("requesttype");
                        object.remove("status");
                        object.saveInBackground();
                    } else {
                        object.deleteInBackground();
                    }
                }
            }
        });
        modelClassList_Request.remove(position);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return modelClassList_Request.size();
    }


    class Viewholder extends RecyclerView.ViewHolder {

        private TextView title;
        private TextView useremail;
        private Button acceptButton, rejectButton;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titleTV);
            useremail = itemView.findViewById(R.id.useremail);
            acceptButton = itemView.findViewById(R.id.acceptBTN);
            rejectButton = itemView.findViewById(R.id.rejectBTN);

        }

    }

}