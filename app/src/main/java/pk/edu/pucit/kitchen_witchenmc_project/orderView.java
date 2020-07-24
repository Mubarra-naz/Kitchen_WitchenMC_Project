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

import pk.edu.pucit.kitchen_witchenmc_project.model.order;
import pk.edu.pucit.kitchen_witchenmc_project.viewAdapter.orderRViewAdapter;

import static pk.edu.pucit.kitchen_witchenmc_project.common.common.currentUser;

public class orderView extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawer;
    DBHelper db;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Orders");
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawer,toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(orderView.this);

        if (savedInstanceState == null) {
            navigationView.setCheckedItem(R.id.nav_menu);
        }
        //user name setting
        View headerView=navigationView.getHeaderView(0);
        TextView user_name = (TextView) headerView.findViewById(R.id.user_name);
        user_name.setText(currentUser.getName());

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setVisibility(View.INVISIBLE);

        RecyclerView order_recycler=(RecyclerView)findViewById(R.id.menu_RView);
        RecyclerView.LayoutManager llm= new LinearLayoutManager(this);
        order_recycler.setLayoutManager(llm);

        ArrayList<order> order_list=db.viewOrders();
        orderRViewAdapter orderLayout=new orderRViewAdapter(orderView.this, order_list);
        order_recycler.setAdapter(orderLayout);
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
                Intent homeIntent= new Intent(orderView.this, home.class);
                startActivity(homeIntent);
                break;
            case R.id.nav_cart:
                Intent viewCartIntent=new Intent(orderView.this,cartView.class);
                startActivity(viewCartIntent);
                break;
            case R.id.nav_order:
                break;
            case R.id.nav_out:
                Intent signIn= new Intent(orderView.this, MainActivity.class);
                signIn.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(signIn);
                break;
            case R.id.nav_contact:
                Intent viewContacttIntent=new Intent(orderView.this,contact.class);
                startActivity(viewContacttIntent);
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

