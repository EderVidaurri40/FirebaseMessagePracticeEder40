package com.example.mensaje;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    // Paso 1
    private static final String PATH_START = "start";
    private static final String PATH_MESSAGE = "message";

    // Paso 6

    private EditText etmessege;
    private Button btnsend;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Paso 2
        final TextView tvMessage = findViewById(R.id.tvMessage);

        // Paso 7

        etmessege = (EditText)findViewById(R.id.etMessage);
        btnsend = (Button) findViewById(R.id.btnSend);

        // Paso 3
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://mensaje-70763-default-rtdb.firebaseio.com");

        // Paso 4
        final DatabaseReference reference = database.getReference(PATH_START).child(PATH_MESSAGE);

        // Paso 5
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                tvMessage.setText(snapshot.getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });

        btnsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase database = FirebaseDatabase.getInstance("https://mensaje-70763-default-rtdb.firebaseio.com");
                final DatabaseReference reference = database.getReference(PATH_START).child(PATH_MESSAGE);

                reference.setValue(etmessege.getText().toString().trim());
                etmessege.setText("");
            }
        });

    }
}