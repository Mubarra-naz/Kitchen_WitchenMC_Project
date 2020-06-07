package pk.edu.pucit.kitchen_witchenmc_project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class signIn extends AppCompatActivity {

    EditText mailTxt,passTxt;
    Button signIn;
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
                ProgressDialog mDialog=new ProgressDialog(signIn.this);
                mDialog.setMessage("Signing In, Please Wait!");
                mDialog.show();

                //verify user from database

                mDialog.dismiss();
                Toast.makeText(signIn.this,"Signed In Successfully!",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
