package pk.edu.pucit.kitchen_witchenmc_project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

import pk.edu.pucit.kitchen_witchenmc_project.model.User;

public class signUp extends AppCompatActivity {

    EditText txtName, txtMail, txtPass;
    String usr,mail, pass;
    Button signUp;
    DBHelper db;
    ProgressDialog mDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        txtName=findViewById(R.id.name);
        txtMail=findViewById(R.id.mail);
        txtPass=findViewById(R.id.pass);
        signUp=findViewById(R.id.start);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usr=txtName.getText().toString();
                pass=txtPass.getText().toString();
                mail=txtMail.getText().toString();
                if(mail.isEmpty() || pass.isEmpty() || usr.isEmpty()){
                    Toast.makeText(signUp.this, "Empty fields remaining", Toast.LENGTH_SHORT).show();
                }
                else if(!Patterns.EMAIL_ADDRESS.matcher(mail).matches()){
                    Toast.makeText(signUp.this, "Please enter a valid Email Address", Toast.LENGTH_SHORT).show();
                }
                else{
                    mDialog=new ProgressDialog(signUp.this);
                    mDialog.setMessage("Signing Up, Please Wait!");
                    mDialog.show();
                    User user= new User(usr, mail, pass);
                    //verify and add data to database
                    signUpUsr(user);
                    mDialog.dismiss();
                    clearForm();
                }
            }

            private void clearForm() {
                txtName.getText().clear();
                txtMail.getText().clear();
                txtPass.getText().clear();
            }

            private void signUpUsr(User usr){
                try {
                    db=new DBHelper(signUp.this);
                    if (db.signUp(usr)){
                        Toast.makeText(signUp.this,"Signed Up Successfully!",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(signUp.this,"Email Already Exists!",Toast.LENGTH_SHORT).show();

                    }
                }catch (Exception e){
                    Toast.makeText(signUp.this,"Error Occurred Signing Up, retry!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
