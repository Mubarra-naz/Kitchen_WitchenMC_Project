package pk.edu.pucit.kitchen_witchenmc_project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import pk.edu.pucit.kitchen_witchenmc_project.model.User;

public class MainActivity extends AppCompatActivity {

    EditText mailTxt,passTxt;
    Button signIn;
    TextView signUpLnk;
    String email, passw;
    DBHelper sqldb;
    ProgressDialog sDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mailTxt=findViewById(R.id.email);
        passTxt=findViewById(R.id.psw);
        signIn=findViewById(R.id.strt);
        signUpLnk=findViewById(R.id.btnSignUp);

        signUpLnk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signUp=new Intent(MainActivity.this,signUp.class);
                startActivity(signUp);
            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email=mailTxt.getText().toString();
                passw=passTxt.getText().toString();
                if(email.isEmpty() || passw.isEmpty()){
                    Toast.makeText(MainActivity.this, "Empty fields remaining", Toast.LENGTH_SHORT).show();
                }
                else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    Toast.makeText(MainActivity.this, "Please enter a valid Email Address", Toast.LENGTH_SHORT).show();
                }
                else{
                    sDialog=new ProgressDialog(MainActivity.this);
                    sDialog.setMessage("Signing In, Please Wait!");
                    sDialog.show();
                    User user= new User(email, passw);
                    if(user.getMail()==getString(R.string.admin_mail) && user.getPassword()==getString(R.string.admin_pass))
                    {
                        Intent admin_panel=new Intent(MainActivity.this,admin_home.class);
                        startActivity(admin_panel);
                    }
                    boolean login=signInUsr(user);
                    sDialog.dismiss();
                    clearForm();
                    if (login) {
                        user.setName(sqldb.getUserName(user.getMail()));
                        //go to home
                    }
                }


            }

            private boolean signInUsr(User user) {
                try{
                    sqldb=new DBHelper(MainActivity.this);
                    if (sqldb.signIn(user)){
                        Toast.makeText(MainActivity.this,"Signed In Successfully!",Toast.LENGTH_SHORT).show();
                        return true;
                    }
                    else {
                        Toast.makeText(MainActivity.this,"Email or password incorrect",Toast.LENGTH_SHORT).show();

                    }
                }catch (Exception e){
                    Toast.makeText(MainActivity.this,"Error Occurred Signing in, retry!",Toast.LENGTH_SHORT).show();
                }
                return false;
            }

            private void clearForm() {
                mailTxt.getText().clear();
                passTxt.getText().clear();
            }
        });
    }
}
