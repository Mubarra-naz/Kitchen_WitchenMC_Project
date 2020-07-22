package pk.edu.pucit.kitchen_witchenmc_project.viewAdapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import pk.edu.pucit.kitchen_witchenmc_project.R;
import pk.edu.pucit.kitchen_witchenmc_project.cartView;
import pk.edu.pucit.kitchen_witchenmc_project.dishView;
import pk.edu.pucit.kitchen_witchenmc_project.model.dish;

public class DishRViewAdapter extends RecyclerView.Adapter<DishRViewAdapter.ViewHolder> {
    Context context;
    private ArrayList<dish> dish_items;
    String selected="";
    public DishRViewAdapter(Context context, ArrayList<dish> dish_list) {
        this.context=context;
        this.dish_items=dish_list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row= LayoutInflater.from(context).inflate(R.layout.main_menu, parent, false);
        DishRViewAdapter.ViewHolder holder=new DishRViewAdapter.ViewHolder(row);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.tv_dname.setText(dish_items.get(position).getName());
        holder.tv_price.setText(dish_items.get(position).getName());
        Picasso.get().load(dish_items.get(position).getImg()).into(holder.tv_dicon);
        holder.tv_dbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected=dish_items.get(position).getName();
                Toast.makeText(context,"Adding "+selected+" to cart",Toast.LENGTH_SHORT).show();
                Intent dishIntent=new Intent(context, dishView.class);
                dishIntent.putExtra("dishName", selected);
                context.startActivity(dishIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dish_items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_dname, tv_price;
        public ImageView tv_dicon;
        public Button tv_dbtn;
        public ViewHolder(@NonNull View items){
            super(items);
            tv_dicon=items.findViewById(R.id.dish_image);
            tv_dname=items.findViewById(R.id.dish_name);
            tv_price=items.findViewById(R.id.dish_price);
            tv_dbtn=items.findViewById(R.id.btnViewDish);
    }
}
}
