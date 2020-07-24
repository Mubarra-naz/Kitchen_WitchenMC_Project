package pk.edu.pucit.kitchen_witchenmc_project;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

import pk.edu.pucit.kitchen_witchenmc_project.model.dish;
import pk.edu.pucit.kitchen_witchenmc_project.viewAdapter.adminDishRViewAdapter;

public class admin_dish extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

        DrawerLayout drawer;
        DBHelper db;
@Override
protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        Toolbar toolbar = findViewById(R.id.admin_toolbar);
        toolbar.setTitle("Dishes");
        setSupportActionBar(toolbar);
        final dish ditem=new dish();
        FloatingActionButton fab = findViewById(R.id.admin_fab);
        fab.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View view) {
    // create an alert builder
    AlertDialog.Builder builder = new AlertDialog.Builder(admin_dish.this);
    builder.setTitle("Add new dish");
    // set the custom layout

    final View customLayout = getLayoutInflater().inflate(R.layout.add_dish, null);
    builder.setView(customLayout);
    // add a button
    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            // send data from the AlertDialog to the Activity
            EditText nameDish = findViewById(R.id.add_dish_name);
            EditText imgDish=findViewById(R.id.add_dish_image);
            EditText ingrDish = findViewById(R.id.add_dish_ingr);
            EditText priceDish = findViewById(R.id.add_dish_price);
            EditText catDish=findViewById(R.id.add_dish_cat);
            ditem.setName(nameDish.getText().toString());
            ditem.setImg(imgDish.getText().toString());
            ditem.setIngredients(ingrDish.getText().toString());
            ditem.setCategoryName(catDish.getText().toString());
            ditem.setPrice(Long.getLong(priceDish.getText().toString()) );
            db.addDish(ditem);
        }
    });
    // create and show the alert dialog
    AlertDialog dialog = builder.create();
    dialog.show();
}
        });
        drawer = findViewById(R.id.admin_drawer_layout);
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawer,toolbar,
        R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.admin_nav_view);
        navigationView.setNavigationItemSelectedListener(admin_dish.this);

        if (savedInstanceState == null) {
        navigationView.setCheckedItem(R.id.nav_menu);
        }
    View headerView=navigationView.getHeaderView(0);
    TextView user_name = (TextView) headerView.findViewById(R.id.user_name);
    user_name.setText("Admin");


        RecyclerView dish_recycler=(RecyclerView)findViewById(R.id.menu_RView);
        RecyclerView.LayoutManager llm= new LinearLayoutManager(this);
        dish_recycler.setLayoutManager(llm);

        db=new DBHelper(admin_dish.this);

        dish item;
        ArrayList<dish> dish_list=db.loadDishes();
        adminDishRViewAdapter dishLayout=new adminDishRViewAdapter(admin_dish.this, dish_list);
        dish_recycler.setAdapter(dishLayout);
        }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.admin_home, menu);
        return true;
    }
    @Override
    public void onBackPressed(){
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_edt_cat:
                Intent catIntent=new Intent(admin_dish.this,admin_home.class);
                startActivity(catIntent);
                break;
            case R.id.nav_edt_dishes:
                break;
            case R.id.nav_out:
                Intent signIn= new Intent(admin_dish.this, MainActivity.class);
                signIn.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(signIn);
                break;

        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

