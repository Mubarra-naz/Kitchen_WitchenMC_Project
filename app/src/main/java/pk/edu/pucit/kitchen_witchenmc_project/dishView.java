package pk.edu.pucit.kitchen_witchenmc_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import pk.edu.pucit.kitchen_witchenmc_project.model.cartItem;
import pk.edu.pucit.kitchen_witchenmc_project.model.dish;

import static pk.edu.pucit.kitchen_witchenmc_project.common.common.currentUser;

public class dishView extends AppCompatActivity {
    int counter=1;
    ImageView itemImg;
    Button inc, dec, addCart;
    DBHelper db;
    TextView itemName, itemDetial, itemPrice, qty, itemPriceLbl;
    String dishName;
    cartItem currItem;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dish);

        itemImg=findViewById(R.id.dish_item_image);
        itemName=findViewById(R.id.dish_item_name);
        itemDetial=findViewById(R.id.dish_item_detail);
        itemPriceLbl=findViewById(R.id.price_lbl);
        itemPrice=findViewById(R.id.dish_item_price);
        qty=findViewById(R.id.dish_item_quantity);
        inc=findViewById(R.id.btnInc);
        dec=findViewById(R.id.btnDec);
        addCart=findViewById(R.id.btnAddToCart);

        Intent getDishIntent=getIntent();
        dishName=getDishIntent.getStringExtra("dishName");

        //get dish details from db
        final dish currDish=db.getDishDetail(dishName);
        itemName.setText(dishName);
        itemDetial.setText(currDish.getIngredients());
        itemPrice.setText(Long.toString(currDish.getPrice()));
        Picasso.get().load(currDish.getImg()).into(itemImg);
        qty.setText(counter+"");

        db=new DBHelper(dishView.this);

        inc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter++;
                qty.setText(counter+"");
            }
        });
        dec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(counter>0){
                    counter--;
                    qty.setText(counter+"");
                }
            }
        });
        addCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currItem=new cartItem(currDish.getImg(),currDish.getName(),currDish.getPrice(),counter, 0, currentUser.getId());
                db.insertCartItem(currItem);
                Toast.makeText(dishView.this,"Added to cart",Toast.LENGTH_LONG).show();
            }
        });
    }
}
