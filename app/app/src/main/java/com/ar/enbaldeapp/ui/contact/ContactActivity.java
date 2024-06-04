package com.ar.enbaldeapp.ui.contact;

import static com.ar.enbaldeapp.ui.IntentConstants.CONTACT_MESSAGE_FOR_HOME;
import static com.ar.enbaldeapp.ui.IntentConstants.DETAIL_MESSAGE_FOR_CATALOGUE;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.ar.enbaldeapp.R;
import com.ar.enbaldeapp.models.User;
import com.ar.enbaldeapp.models.utilities.SharedPreferencesManager;
import com.ar.enbaldeapp.services.ApiServices;
import com.ar.enbaldeapp.services.IApiServices;
import com.ar.enbaldeapp.ui.Utilities;
import com.google.android.material.snackbar.Snackbar;

public class ContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        Utilities.changeToolbarTitleToContact(this);

        EditText nameEditText = findViewById(R.id.contactNameEditText);
        EditText emailEditText = findViewById(R.id.contactEmailEditText);
        EditText titleEditText = findViewById(R.id.contactTitleEditText);
        EditText messageEditText = findViewById(R.id.contactMessageEditView);
        ImageView logoImageView = findViewById(R.id.contactLogoImageView);

        Utilities.insertLogoImageInto(getApplicationContext(), logoImageView);

        SharedPreferencesManager sharedPreferencesManager = new SharedPreferencesManager(getApplicationContext());
        User user = sharedPreferencesManager.loadCurrentUser();

        if (user != null) {
            nameEditText.setText(user.getFirstName() + " " + user.getLastName());
            emailEditText.setText(user.getEmail());
        }

        Button button = findViewById(R.id.contactSendButton);
        button.setOnClickListener(v -> {
            Intent result = new Intent();
            IApiServices apiServices = new ApiServices();
            apiServices.contact(nameEditText.getText().toString(), emailEditText.getText().toString(),
                    titleEditText.getText().toString(), messageEditText.getText().toString(),
                    () -> {
                        result.putExtra(CONTACT_MESSAGE_FOR_HOME, "El mensaje ha sido enviado con éxito");
                        setResult(Activity.RESULT_OK, result);
                        finish();
                    },
                    e -> {
                        Toast.makeText(this, "Error enviando el mensaje: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        setResult(Activity.RESULT_CANCELED);
                    });
        });
    }
}