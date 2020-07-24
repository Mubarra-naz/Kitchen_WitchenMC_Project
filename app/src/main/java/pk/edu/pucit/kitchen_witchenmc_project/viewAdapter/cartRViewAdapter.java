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

import pk.edu.pucit.kitchen_witchenmc_project.DBHelper;
import pk.edu.pucit.kitchen_witchenmc_project.R;
import pk.edu.pucit.kitchen_witchenmc_project.model.cartItem;

public class cartRViewAdapter extends RecyclerView.Adapter<cartRViewAdapter.ViewHolder> {
    Context context;
    DBHelper db;
    private ArrayList<cartItem> cart_items;
    String selected="";

    public cartRViewAdapter(Context context, ArrayList<cartItem> cart_list) {
        this.context=context;
        cart_items=cart_list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row= LayoutInflater.from(context).inflate(R.layout.cart, parent, false);
        cartRViewAdapter.ViewHolder holder=new cartRViewAdapter.ViewHolder(row);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.tv_item_name.setText(cart_items.get(position).getName());
        holder.tv_item_price.setText(Long.toString(cart_items.get(position).getPrice()));
        holder.tv_item_qty.setText(Long.toString(cart_items.get(position).getQuantity()));
        Picasso.get().load(cart_items.get(position).getImg()).into(holder.tv_item_icon);
        holder.tv_cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected=cart_items.get(position).getName();
                Toast.makeText(context,"Removing "+selected+" from cart",Toast.LENGTH_SHORT).show();
                db.cancelItem(cart_items.get(position));
                }
        });

    }

    @Override
    public int getItemCount() {
        return cart_items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_item_name, tv_item_price, tv_item_qty, tv_price_lbl;
        public ImageView tv_item_icon;
        public Button tv_cancel_btn;
        public ViewHolder(@NonNull View items) {
            super(items);
            tv_item_icon = items.findViewById(R.id.item_image);
            tv_item_name = items.findViewById(R.id.item_name);
            tv_price_lbl=items.findViewById(R.id.item_price_lbl);
            tv_item_price = items.findViewById(R.id.item_price);
            tv_cancel_btn = items.findViewById(R.id.cancelBtn);
            tv_item_qty = items.findViewById(R.id.item_quantity);
        }
    }
}
