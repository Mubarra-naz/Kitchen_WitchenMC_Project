package pk.edu.pucit.kitchen_witchenmc_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
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
import pk.edu.pucit.kitchen_witchenmc_project.viewAdapter.DishRViewAdapter;

public class dishListView extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawer;
    String userNAme;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent getCategoryIntent=getIntent();
        String categoryName=getCategoryIntent.getStringExtra("categoryName");
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(categoryName);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent viewCartIntent=new Intent(dishListView.this,cartView.class);
                startActivity(viewCartIntent);
            }
        });
        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawer,toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(dishListView.this);

        if (savedInstanceState == null) {
            navigationView.setCheckedItem(R.id.nav_menu);
        }
        //user name setting
        Intent resultIntent=getIntent();
        View headerView=navigationView.getHeaderView(0);
        TextView user_name = (TextView) headerView.findViewById(R.id.user_name);
        userNAme=resultIntent.getStringExtra("username");
        user_name.setText(userNAme);


        RecyclerView dish_recycler=(RecyclerView)findViewById(R.id.menu_RView);
        RecyclerView.LayoutManager llm= new LinearLayoutManager(this);
        dish_recycler.setLayoutManager(llm);

        dish item;
        ArrayList<dish> dish_list=new ArrayList<>();
        for (int i=0;i<10;i++){
            item=new dish("name"+i, "img"+i, 57);
            dish_list.add(item);
        }
        DishRViewAdapter dishLayout=new DishRViewAdapter(dishListView.this, dish_list);
        dish_recycler.setAdapter(dishLayout);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
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
            case R.id.nav_menu:
                Intent homeIntent= new Intent(dishListView.this, home.class);
                homeIntent.putExtra("username",userNAme);
                startActivity(homeIntent);
                break;
            case R.id.nav_cart:
                Intent viewCartIntent=new Intent(dishListView.this,cartView.class);
                viewCartIntent.putExtra("username",userNAme);
                startActivity(viewCartIntent);
                break;
            case R.id.nav_order:
                Intent viewOrderIntent=new Intent(dishListView.this,orderView.class);
                viewOrderIntent.putExtra("username",userNAme);
                startActivity(viewOrderIntent);
                break;
            case R.id.nav_out:
                Intent signIn= new Intent(dishListView.this, MainActivity.class);
                signIn.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(signIn);
                break;
            case R.id.nav_contact:
                Intent viewContacttIntent=new Intent(dishListView.this,contact.class);
                viewContacttIntent.putExtra("username",userNAme);
                startActivity(viewContacttIntent);
                break;
            }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
