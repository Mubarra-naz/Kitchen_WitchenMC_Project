package pk.edu.pucit.kitchen_witchenmc_project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import pk.edu.pucit.kitchen_witchenmc_project.model.User;

public class signUp extends AppCompatActivity {

    EditText txtName, txtMail, txtPass;
    String usr,mail, pass;
    Button signUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        txtName=findViewById(R.id.name);
        txtMail=findViewById(R.id.mail);
        txtPass=findViewById(R.id.pass);
        signUp=findViewById(R.id.start);
        usr=txtName.getText().toString();
        pass=txtPass.getText().toString();
        mail=txtMail.getText().toString();

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressDialog mDialog=new ProgressDialog(signUp.this);
                mDialog.setMessage("Signing Up, Please Wait!");
                mDialog.show();
                User user= new User(usr, mail, pass);
        //verify and add data to database

                mDialog.dismiss();
                Toast.makeText(signUp.this,"Signed Up Successfully!",Toast.LENGTH_SHORT).show();
                //else for user existence
            }
        });
    }
}
