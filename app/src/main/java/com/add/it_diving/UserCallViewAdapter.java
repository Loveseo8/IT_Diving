package com.add.it_diving;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.palette.graphics.Palette;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserCallViewAdapter extends RecyclerView.Adapter<UserCallViewAdapter.UserCallViewViewHolder> {

    private ArrayList<User> users = new ArrayList<>();
    private Context context;

    public UserCallViewAdapter(ArrayList<User> users, Context context) {
        this.users = users;
        this.context = context;
    }

    @NonNull
    @Override
    public UserCallViewAdapter.UserCallViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.call_row, parent, false);

        return new UserCallViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserCallViewAdapter.UserCallViewViewHolder holder, int position) {

        holder.name.setText(users.get(position).getName());
        Bitmap icon = BitmapFactory.decodeResource(context.getResources(), users.get(position).getIcon());
        Palette palette = Palette.from(icon).generate();
        int color1 = palette.getDominantColor(0);
        int color2 = palette.getDarkMutedColor(0);
        int[] colors = {color1, color2};

        GradientDrawable gd = new GradientDrawable(
                GradientDrawable.Orientation.LEFT_RIGHT, colors);

        gd.setCornerRadius(0f);
        holder.itemView.setBackground(gd);
        holder.image.setImageDrawable(AppCompatResources.getDrawable(context, users.get(position).getIcon()));

    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public static class UserCallViewViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private ImageView image;

        public UserCallViewViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.text_row);
            image = itemView.findViewById(R.id.image_row);
        }
    }
}


