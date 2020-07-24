package pk.edu.pucit.kitchen_witchenmc_project.viewAdapter;

import android.content.Context;
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
import pk.edu.pucit.kitchen_witchenmc_project.model.order;
import pk.edu.pucit.kitchen_witchenmc_project.orderView;

public class orderRViewAdapter extends RecyclerView.Adapter<orderRViewAdapter.ViewHolder> {
    Context context;
    private ArrayList<order> order_items;
    String selected="";

    public orderRViewAdapter(Context context, ArrayList<order> order_list) {
        this.context=context;
        order_items=order_list;
    }

    @NonNull
    @Override
    public orderRViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row= LayoutInflater.from(context).inflate(R.layout.orders, parent, false);
        orderRViewAdapter.ViewHolder holder=new orderRViewAdapter.ViewHolder(row);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull orderRViewAdapter.ViewHolder holder, final int position) {
        holder.tv_order_status.setText(order_items.get(position).getOrderStatus());
        holder.tv_order_price.setText(Integer.toString(order_items.get(position).getOrderPrice()));
        holder.tv_order_no.setText(Integer.toString(order_items.get(position).getOrderId()));
    }

    @Override
    public int getItemCount() {
        return order_items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_order_no, tv_order_price, tv_order_status, tv_order_lbl;
        public ViewHolder(@NonNull View items) {
            super(items);
            tv_order_no = items.findViewById(R.id.order_no);
            tv_order_price=items.findViewById(R.id.oder_price);
            tv_order_lbl = items.findViewById(R.id.order_price_lbl);
            tv_order_status = items.findViewById(R.id.order_status);
        }
    }
}