package com.add.it_diving;

import android.annotation.SuppressLint;
import android.content.ContentUris;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.add.it_diving.databinding.ActivityContactsBinding;

import java.util.ArrayList;

public class ContactsActivity extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_READ_CONTACTS = 100;
    Cursor c;
    ArrayList<Contact> contacts;
    private ActivityContactsBinding binding;
    private ContactsAdapter contactsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityContactsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        int permissionCheck = ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_CONTACTS);
        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            showContacts();
        } else {

            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.READ_CONTACTS}, PERMISSION_REQUEST_READ_CONTACTS);

        }

        binding.contactsList.setHasFixedSize(false);
        binding.contactsList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        contactsAdapter = new ContactsAdapter(contacts, getApplication());
        binding.contactsList.setAdapter(contactsAdapter);
        contactsAdapter.notifyDataSetChanged();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_READ_CONTACTS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                showContacts();
            }
        }
    }

    private void showContacts() {
        c = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, ContactsContract.Contacts.DISPLAY_NAME + " ASC ");
        contacts = new ArrayList<Contact>();
        while (c.moveToNext()) {
            @SuppressLint("Range") String name = c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            @SuppressLint("Range") String phone = c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            @SuppressLint("Range") Long contactId = c.getLong(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID));
            Uri contactUri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, contactId);
            @SuppressLint("Range") String imageUri = c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_URI));
            System.out.println(imageUri);
            contacts.add(new Contact(name, phone, imageUri));
        }

        c.close();
    }
}