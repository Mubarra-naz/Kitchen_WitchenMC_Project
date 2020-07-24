package pk.edu.pucit.kitchen_witchenmc_project;

import android.app.DownloadManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

import pk.edu.pucit.kitchen_witchenmc_project.model.cartItem;
import pk.edu.pucit.kitchen_witchenmc_project.viewAdapter.cartRViewAdapter;

public class cartView extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawer;
    TextView total,total_price;
    String userNAme;
    ConstraintLayout ll;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Cart");
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawer,toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState == null) {
            navigationView.setCheckedItem(R.id.nav_menu);
        }

        //user name setting
        Intent resultIntent=getIntent();
        View headerView=navigationView.getHeaderView(0);
        TextView user_name = (TextView) headerView.findViewById(R.id.user_name);
        userNAme = resultIntent.getStringExtra("username");
        user_name.setText(userNAme);

        RecyclerView cart_recycler=(RecyclerView)findViewById(R.id.menu_RView);
        RecyclerView.LayoutManager llmanager= new LinearLayoutManager(this);
        cart_recycler.setLayoutManager(llmanager);

        cartItem cItem;
        int sumPrice=0;
        ArrayList<cartItem> cart_list=new ArrayList<>();
        for (int i=0;i<5;i++){
            sumPrice+=((i+2)*(4+i));//price*qty
            cItem=new cartItem( "img"+i,"name"+i,4+i, i+2);
            cart_list.add(cItem);
        }
        cartRViewAdapter cartLayout=new cartRViewAdapter(cartView.this, cart_list);
        cart_recycler.setAdapter(cartLayout);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setVisibility(View.INVISIBLE);

        ll=findViewById(R.id.total_bottom);
        ll.setVisibility(View.VISIBLE);
        total=findViewById(R.id.total_bill);
        total.setText("Grand total: PKR ");
        total_price=findViewById(R.id.bill_value);
        total_price.setText(Integer.toString(sumPrice));
        Button btn=(Button)findViewById(R.id.place_order_btn);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Toast.makeText(cartView.this,"Order booked",Toast.LENGTH_SHORT);
            }
        });
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
                Intent homeIntent= new Intent(cartView.this, home.class);
                homeIntent.putExtra("username",userNAme);
                startActivity(homeIntent);
                break;
            case R.id.nav_cart:
                break;
            case R.id.nav_order:
                Intent viewOrderIntent=new Intent(cartView.this,orderView.class);
                viewOrderIntent.putExtra("username",userNAme);
                startActivity(viewOrderIntent);
                break;
            case R.id.nav_out:
                Intent signIn= new Intent(cartView.this, MainActivity.class);
                signIn.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(signIn);
                break;
            case R.id.nav_contact:
                Intent viewContacttIntent=new Intent(cartView.this,contact.class);
                viewContacttIntent.putExtra("username",userNAme);
                startActivity(viewContacttIntent);
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}