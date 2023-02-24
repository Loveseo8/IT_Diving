package com.add.it_diving;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactsViewHolder> {

    private ArrayList<Contact> contacts = new ArrayList<>();
    private Context context;

    public ContactsAdapter(ArrayList<Contact> contacts, Context context) {
        this.contacts = contacts;
        this.context = context;
    }

    @NonNull
    @Override
    public ContactsAdapter.ContactsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_row, parent, false);

        return new ContactsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactsAdapter.ContactsViewHolder holder, int position) {

        holder.name.setText(contacts.get(position).getName());
        holder.phone.setText(contacts.get(position).getPHone());
        Bitmap bitmap = null;
        if (contacts.get(position).getImageUri() != null) {
            try {
                bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(),
                        Uri.parse(contacts.get(position).getImageUri()));
            } catch (FileNotFoundException e) {
                e.printStackTrace();

            } catch (IOException e) {
                e.printStackTrace();
            }
            holder.image.setImageBitmap(bitmap);
        } else {
            bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.user_icon_1);
            holder.image.setImageBitmap(bitmap);
        }


    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public static class ContactsViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView phone;
        private ImageView image;

        public ContactsViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.contact_name);
            phone = itemView.findViewById(R.id.contact_phone);
            image = itemView.findViewById(R.id.contact_image);
        }
    }
}

