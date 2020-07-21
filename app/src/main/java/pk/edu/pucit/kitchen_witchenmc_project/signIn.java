package pk.edu.pucit.kitchen_witchenmc_project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import pk.edu.pucit.kitchen_witchenmc_project.model.User;

public class signIn extends AppCompatActivity {

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

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mail.isEmpty()==false && pass.isEmpty()==false){
                    ProgressDialog mDialog=new ProgressDialog(signIn.this);
                    mDialog.setMessage("Signing In, Please Wait!");
                    mDialog.show();
                    User user= new User(mail, pass);
                    //if user exist
                        Toast.makeText(signIn.this,"Signed In Successfully!",Toast.LENGTH_SHORT).show();
                    //else for user existence
                        Toast.makeText(signIn.this,"Email Does not Exists!",Toast.LENGTH_SHORT).show();
                     mDialog.dismiss();
                    //Toast.makeText(signIn.this,"Signed In Successfully!",Toast.LENGTH_SHORT).show();

                }


            }
        });
    }
}
