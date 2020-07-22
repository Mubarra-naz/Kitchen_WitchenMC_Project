package pk.edu.pucit.kitchen_witchenmc_project;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import pk.edu.pucit.kitchen_witchenmc_project.model.dish;
import pk.edu.pucit.kitchen_witchenmc_project.viewAdapter.DishRViewAdapter;

public class dishListView extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Intent getCategoryIntent=getIntent();
        String categoryName=getCategoryIntent.getStringExtra("categoryName");
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(categoryName);
        setSupportActionBar(toolbar);

        RecyclerView dish_recycler=(RecyclerView)findViewById(R.id.menu_RView);
        RecyclerView.LayoutManager llm= new LinearLayoutManager(this);
        dish_recycler.setLayoutManager(llm);

        dish item;
        ArrayList<dish> dish_list=new ArrayList<>();
        for (int i=0;i<10;i++){
            item=new dish("name"+i, "img"+i, i+5);
            dish_list.add(item);
        }
        DishRViewAdapter dishLayout=new DishRViewAdapter(dishListView.this, dish_list);
        dish_recycler.setAdapter(dishLayout);
    }


}
