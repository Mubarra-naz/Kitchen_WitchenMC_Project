package pk.edu.pucit.kitchen_witchenmc_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


public class contact extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Contact us");
        setSupportActionBar(toolbar);

        TextView contact, location;
        contact=findViewById(R.id.con_txt);
        contact.setText("+92303003300");
        location=findViewById(R.id.loc_txt);
        String locate="Street No: x, Colony: y, xyz road, Lahore";
        location.setText(locate);
        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}