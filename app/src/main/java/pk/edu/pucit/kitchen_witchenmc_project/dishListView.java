package pk.edu.pucit.kitchen_witchenmc_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

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

        RecyclerView dish_recycler=(RecyclerView)findViewById(R.id.menu_RView);
        RecyclerView.LayoutManager llm= new LinearLayoutManager(this);
        dish_recycler.setLayoutManager(llm);

        dish item;
        ArrayList<dish> dish_list=new ArrayList<>();
        for (int i=0;i<10;i++){
            item=new dish("name"+i, "img"+i, "pkr 5"+i);
            dish_list.add(item);
        }
        DishRViewAdapter dishLayout=new DishRViewAdapter(dishListView.this, dish_list);
        dish_recycler.setAdapter(dishLayout);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_menu:
                break;
            case R.id.nav_cart:

                break;
            case R.id.nav_out:

                break;
            case R.id.nav_contact:

                break;
            case R.id.nav_loc:

                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
