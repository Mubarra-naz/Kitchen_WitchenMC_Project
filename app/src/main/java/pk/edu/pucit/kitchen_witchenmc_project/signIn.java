package pk.edu.pucit.kitchen_witchenmc_project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import pk.edu.pucit.kitchen_witchenmc_project.model.User;

public class signIn extends AppCompatActivity {

    EditText mailTxt,passTxt;
    Button signIn;
    String mail, pass;
    DBHelper sqldb;
    ProgressDialog sDialog;
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
                if(mail.isEmpty() || pass.isEmpty()){
                    Toast.makeText(signIn.this, "Empty fields remaining", Toast.LENGTH_SHORT).show();
                }
                else if(!Patterns.EMAIL_ADDRESS.matcher(mail).matches()){
                    Toast.makeText(signIn.this, "Please enter a valid Email Address", Toast.LENGTH_SHORT).show();
                }
                else{
                    sDialog=new ProgressDialog(signIn.this);
                    sDialog.setMessage("Signing In, Please Wait!");
                    sDialog.show();
                    User user= new User(mail, pass);
                    if (signInUsr(user)){
                        //go to home
                    }
                    clearForm();
                    }


            }

            private boolean signInUsr(User user) {
                try{
                    sqldb=new DBHelper(signIn.this);
                    if (sqldb.signIn(user)){
                        Toast.makeText(signIn.this,"Signed In Successfully!",Toast.LENGTH_SHORT).show();
                        return true;
                    }
                    else {
                        Toast.makeText(signIn.this,"Email or password incorrect",Toast.LENGTH_SHORT).show();

                    }
                    sDialog.dismiss();
                }catch (Exception e){
                    Toast.makeText(signIn.this,"Error Occurred Signing in, retry!",Toast.LENGTH_SHORT).show();
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
