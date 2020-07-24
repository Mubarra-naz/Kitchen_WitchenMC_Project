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

import pk.edu.pucit.kitchen_witchenmc_project.R;
import pk.edu.pucit.kitchen_witchenmc_project.model.category;

public class adminRViewAdapter extends RecyclerView.Adapter<adminRViewAdapter.ViewHolder> {
    Context context;
    private ArrayList<category> cat_items;
    String selected="";

    public adminRViewAdapter(Context context, ArrayList<String> name, ArrayList<String> urls){
        this.context=context;
        for(int i=0;i<name.size();i++){
            this.cat_items.get(i).setName(name.get(i));
            this.cat_items.get(i).setImg(urls.get(i));
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row= LayoutInflater.from(context).inflate(R.layout.cat_menu, parent, false);
        ViewHolder holder=new ViewHolder(row);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.tv_name.setText(cat_items.get(position).getName());
        Picasso.get().load(cat_items.get(position).getImg()).into(holder.tv_icon);
        holder.tv_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected=cat_items.get(position).getName();
                category currentCat= new category(selected);
                //get category data from db and load in next view
            }
        });
    }

    @Override
    public int getItemCount() {
        return cat_items.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_name;
        public ImageView tv_icon;
        public Button tv_btn;
        public ViewHolder(@NonNull View items){
            super(items);
            tv_icon=items.findViewById(R.id.category_image);
            tv_name=items.findViewById(R.id.category_name);
            tv_btn=items.findViewById(R.id.btnLoad);
        }
    }
}

