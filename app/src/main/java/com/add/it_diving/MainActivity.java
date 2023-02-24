package com.add.it_diving;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.add.it_diving.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private UserCallViewAdapter userCallViewAdapter;
    private ArrayList<User> users = new ArrayList<>();
    private boolean micrifone_turned_off = true;
    private boolean videocam_turned_off = true;
    private boolean list_not_changed = true;


    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        users.add(new User("You", R.drawable.user_icon_1));
        users.add(new User("Alice", R.drawable.user_icon_2));

        binding.recyclerview.setHasFixedSize(false);
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        userCallViewAdapter = new UserCallViewAdapter(users, getApplication());
        binding.recyclerview.setAdapter(userCallViewAdapter);
        userCallViewAdapter.notifyDataSetChanged();

        binding.micrifone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (micrifone_turned_off) {

                    binding.micrifone.setIconTint(getResources().getColorStateList(R.color.gray_100, getTheme()));
                    binding.micrifone.setBackgroundTintList(getResources().getColorStateList(R.color.gray_600, getTheme()));
                    binding.micrifone.setIcon(getResources().getDrawable(R.drawable.icon_micrifone_24, getTheme()));
                    micrifone_turned_off = false;

                } else {

                    binding.micrifone.setIconTint(getResources().getColorStateList(R.color.gray_1000, getTheme()));
                    binding.micrifone.setBackgroundTintList(getResources().getColorStateList(R.color.gray_100, getTheme()));
                    binding.micrifone.setIcon(getResources().getDrawable(R.drawable.icon_micrifone_slash_24, getTheme()));
                    micrifone_turned_off = true;

                }

            }
        });

        binding.videocam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (videocam_turned_off) {

                    binding.videocam.setIconTint(getResources().getColorStateList(R.color.gray_100, getTheme()));
                    binding.videocam.setBackgroundTintList(getResources().getColorStateList(R.color.gray_600, getTheme()));
                    binding.videocam.setIcon(getResources().getDrawable(R.drawable.icon_videocam, getTheme()));
                    videocam_turned_off = false;

                } else {

                    binding.videocam.setIconTint(getResources().getColorStateList(R.color.gray_1000, getTheme()));
                    binding.videocam.setBackgroundTintList(getResources().getColorStateList(R.color.gray_100, getTheme()));
                    binding.videocam.setIcon(getResources().getDrawable(R.drawable.icon_videocam_slash, getTheme()));
                    videocam_turned_off = true;

                }

            }
        });

        binding.phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(MainActivity.this, "Call Was Ended", Toast.LENGTH_LONG).show();
                finish();
                System.exit(0);

            }
        });

        binding.hand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showHelloDialog();

            }
        });

        binding.messages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.setType("vnd.android-dir/mms-sms");
                startActivity(intent);

            }
        });

        binding.grid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (list_not_changed) {

                    users.clear();
                    users.add(new User("Alice", R.drawable.user_icon_2));
                    users.add(new User("You", R.drawable.user_icon_1));
                    userCallViewAdapter = new UserCallViewAdapter(users, getApplication());
                    binding.recyclerview.setAdapter(userCallViewAdapter);
                    userCallViewAdapter.notifyDataSetChanged();

                    list_not_changed = false;

                } else {

                    users.clear();
                    users.add(new User("You", R.drawable.user_icon_1));
                    users.add(new User("Alice", R.drawable.user_icon_2));
                    userCallViewAdapter = new UserCallViewAdapter(users, getApplication());
                    binding.recyclerview.setAdapter(userCallViewAdapter);
                    userCallViewAdapter.notifyDataSetChanged();

                    list_not_changed = true;

                }

            }
        });

        binding.contacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(MainActivity.this, ContactsActivity.class);
                startActivity(i);

            }
        });
    }

    private void showHelloDialog() {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivity.this);
        LayoutInflater layoutInflater = getLayoutInflater();
        final View dialogView = layoutInflater.inflate(R.layout.hello_dialog, null);
        dialogBuilder.setView(dialogView);

        final Button close = dialogView.findViewById(R.id.close_dialog);

        final AlertDialog b = dialogBuilder.create();
        b.setCancelable(false);
        b.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        b.show();

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                b.dismiss();

            }
        });

    }
}