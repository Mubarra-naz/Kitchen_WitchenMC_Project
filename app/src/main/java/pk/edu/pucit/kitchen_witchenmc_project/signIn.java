package pk.edu.pucit.kitchen_witchenmc_project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import pk.edu.pucit.kitchen_witchenmc_project.common.common;
import pk.edu.pucit.kitchen_witchenmc_project.model.User;

public class signIn extends AppCompatActivity {

    EditText mailTxt,passTxt;
    Button signIn;
    String mail,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        mailTxt=findViewById(R.id.mail);
        passTxt=findViewById(R.id.pass);
        signIn=findViewById(R.id.start);


        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mail=mailTxt.getText().toString();
                pass=passTxt.getText().toString();
                if(mail.isEmpty()==false && passTxt.getText().toString().isEmpty()==false){
                    ProgressDialog mDialog=new ProgressDialog(signIn.this);
                    mDialog.setMessage("Signing In, Please Wait!");
                    mDialog.show();
                    User user= new User(mail, pass);
                    //verify user from database
                    //1st for user if it exist or not?
                    //if else for password.....
                    mDialog.dismiss();
                    Toast.makeText(signIn.this,"Signed In Successfully!",Toast.LENGTH_SHORT).show();
                    //get username from db
                    Intent homeIntent= new Intent(signIn.this, home.class);
                    common.current=user;
                    startActivity(homeIntent);
                    finish();
                    //else for user existence
                }


            }
        });
    }
}
