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

public class dishView extends AppCompatActivity {
    int counter=1;
    ImageView itemImg;
    Button inc, dec, addCart;
    TextView itemName, itemDetial, itemPrice, qty, itemPriceLbl;
    dish currDish;
    String dishName;
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
        //currDish=new dish();
        //currDish=getDishDetails(dishName);
        itemName.setText(dishName);
        itemDetial.setText("dhjdkdkldldd");//currDish.getIngredients());
        itemPrice.setText("100");//currDish.getPrice().toString());
        Picasso.get().load("sssdfff").into(itemImg);//currDish.getImg()).into(itemImg);
        qty.setText(counter+"");

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
                cartItem currItem=new cartItem();
                currItem.setName(currDish.getName());
                currItem.setPrice(currDish.getPrice());
                currItem.setPrice(counter);
                currItem.setImg(currDish.getImg());
                addToCart(currItem);
            }
        });

    }
    private void addToCart(cartItem food){

        Toast.makeText(dishView.this,"Added to cart",Toast.LENGTH_LONG).show();
    }

    private dish getDishDetails(String DishName) {
        //get dish data from db and return
        return new dish(DishName,"xyz","sss",20);
    }
}
