package pk.edu.pucit.kitchen_witchenmc_project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import pk.edu.pucit.kitchen_witchenmc_project.model.DBHelper;
import pk.edu.pucit.kitchen_witchenmc_project.model.User;
import android.database.sqlite.SQLiteDatabase;

public class signIn extends AppCompatActivity {
   // private  SQLiteDatabase db;
    //private SharedPreferences spUser;
    DBHelper db;
    EditText mailTxt,passTxt;
    Button signIn;
    String mail, pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        mailTxt=findViewById(R.id.mail);
        passTxt=findViewById(R.id.pass);
        signIn=findViewById(R.id.start);
        mail=mailTxt.getText().toString();
        pass=passTxt.getText().toString();
        //spUser=getSharedPreferences("userSp",MODE_PRIVATE);
       // mailTxt.setText(spUser.getString((getResources().getString(R.string.email))));

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mail.isEmpty()==false && pass.isEmpty()==false){
                    ProgressDialog mDialog=new ProgressDialog(signIn.this);
                    mDialog.setMessage("Signing In, Please Wait!");
                    mDialog.show();
                    User user= new User(mail, pass);
                    Boolean email=db.signIn(mail,pass);
                    if(email==true){
                        Toast.makeText(signIn.this,"Signed In Successfully!",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(signIn.this,"Email Already Exists!",Toast.LENGTH_SHORT).show();
                    }
                    //verify user from database
                    //1st if statement for user if it exist or not?
                    //if else for password.....
                    //db= openOrCreateDatabase("users",MODE_PRIVATE,null);
                    //db.execSQL("CREATE TABLE IF NOT EXISTS user" +"(userID INTEGER PRIMARY KEY AUTOINCREMENT, userMail TEXT, userPass TEXT);" );
                    mDialog.dismiss();
                    //Toast.makeText(signIn.this,"Signed In Successfully!",Toast.LENGTH_SHORT).show();

                    //else for user existence
                }


            }
        });
    }
}
