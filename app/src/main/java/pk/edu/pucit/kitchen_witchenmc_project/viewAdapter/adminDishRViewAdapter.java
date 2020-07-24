package pk.edu.pucit.kitchen_witchenmc_project.viewAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import pk.edu.pucit.kitchen_witchenmc_project.DBHelper;
import pk.edu.pucit.kitchen_witchenmc_project.R;
import pk.edu.pucit.kitchen_witchenmc_project.admin_dish;
import pk.edu.pucit.kitchen_witchenmc_project.model.dish;

public class adminDishRViewAdapter extends RecyclerView.Adapter<adminDishRViewAdapter.ViewHolder> {
    Context context;
    DBHelper db;
    private ArrayList<dish> dish_items;
    String selected="";
    public adminDishRViewAdapter(admin_dish admin_dish, ArrayList<dish> dish_list) {
        this.context=context;
        this.dish_items=dish_list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row= LayoutInflater.from(context).inflate(R.layout.dish_menu, parent, false);
        adminDishRViewAdapter.ViewHolder holder=new adminDishRViewAdapter.ViewHolder(row);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.tv_dname.setText(dish_items.get(position).getName());
        holder.tv_price.setText(Long.toString(dish_items.get(position).getPrice()));
        holder.tv_ingr.setText(dish_items.get(position).getIngredients());
        holder.tv_dcat.setText(dish_items.get(position).getCategoryName());
        Picasso.get().load(dish_items.get(position).getImg()).into(holder.tv_dicon);
        holder.tv_dbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected=dish_items.get(position).getName();
                db=new DBHelper(context);
                db.removeDish(dish_items.get(position).getName());
            }
        });
    }


    @Override
    public int getItemCount() {
        return dish_items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView tv_dname, tv_price, tv_ingr, tv_dcat;
        public ImageView tv_dicon;
        public Button tv_dbtn;
        public ViewHolder(@NonNull View items){
            super(items);
            tv_dicon=items.findViewById(R.id.dish_image);
            tv_dname=items.findViewById(R.id.dish_name);
            tv_price=items.findViewById(R.id.dish_price);
            tv_dbtn=items.findViewById(R.id.btnViewDish);
            tv_ingr=items.findViewById(R.id.dish_ingredients);
            tv_dcat=items.findViewById(R.id.dish_cat);
        }
    }
}
