package pk.edu.pucit.kitchen_witchenmc_project;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import pk.edu.pucit.kitchen_witchenmc_project.model.category;
import pk.edu.pucit.kitchen_witchenmc_project.viewAdapter.catRViewAdapter;

import static pk.edu.pucit.kitchen_witchenmc_project.common.common.currentUser;

public class admin_home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    TextView user_name;
    DrawerLayout drawer;
    RecyclerView menu_recycler;
    RecyclerView.LayoutManager layoutManager;
    DBHelper db;
    //initialize db

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Categories");
        setSupportActionBar(toolbar);
        //initialize db
        drawer = findViewById(R.id.admin_drawer_layout);
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawer,toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.admin_nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState == null) {
            navigationView.setCheckedItem(R.id.nav_menu);
        }


        //user name setting
        View headerView=navigationView.getHeaderView(0);
        user_name=(TextView)headerView.findViewById(R.id.user_name);
        user_name.setText("Admin");

        final category citem=new category();
        FloatingActionButton fab = findViewById(R.id.admin_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // create an alert builder
                AlertDialog.Builder builder = new AlertDialog.Builder(admin_home.this);
                builder.setTitle("Add new category");
                // set the custom layout

                final View customLayout = getLayoutInflater().inflate(R.layout.add_category, null);
                builder.setView(customLayout);
                // add a button
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // send data from the AlertDialog to the Activity
                        EditText nameCat = findViewById(R.id.add_cat_name);
                        EditText imgCat=findViewById(R.id.add_cat_image);
                        citem.setName(nameCat.getText().toString());
                        citem.setImg(imgCat.getText().toString());
                        db.addCategory(citem);
                    }
                });
                // create and show the alert dialog
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        //menu
        menu_recycler=(RecyclerView)findViewById(R.id.admin_RView);
        layoutManager= new LinearLayoutManager(this);
        menu_recycler.setLayoutManager(layoutManager);
        loadCat();
    }
    private void loadCat() {
        ArrayList<category> cat_list=new ArrayList<>();
        cat_list= db.loadCategories();
        //take category data from db and send to menuRViewAdapter defined below
        catRViewAdapter adapter=new catRViewAdapter(this, cat_list);
        menu_recycler.setAdapter(adapter);
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
                break;
            case R.id.nav_edt_dishes:
                Intent dishIntent=new Intent(admin_home.this,admin_dish.class);
                startActivity(dishIntent);
                break;
            case R.id.nav_out:
                Intent signIn= new Intent(admin_home.this, MainActivity.class);
                signIn.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(signIn);
                break;

        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
