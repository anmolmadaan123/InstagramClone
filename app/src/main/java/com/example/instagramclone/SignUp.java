package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUp extends AppCompatActivity implements View.OnClickListener{

    private Button btnsave;
    private EditText edtname,edtpunchspeed,edtpunchpower,edtkickspeed,edtkickpower;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnsave=findViewById(R.id.btnsave);
        btnsave.setOnClickListener(SignUp.this);

        edtname=findViewById(R.id.edtname);                                                                                                                                                                                                                                                                                                                                                     
        edtpunchspeed=findViewById(R.id.edtpunchspeed);
        edtpunchpower=findViewById(R.id.edtpunchpower);
        edtkickspeed=findViewById(R.id.edtKickSpeed);
        edtkickpower=findViewById(R.id.edtKickPower);

    }


    @Override
    public void onClick(View v) {

        final ParseObject kickboxer=new ParseObject("KickBoxer");
        kickboxer.put("name",edtname.getText().toString());
        kickboxer.put("punchSpeed",edtpunchspeed.getText().toString());
        kickboxer.put("punchpower",edtpunchpower.getText().toString());
        kickboxer.put("KickSpeed",edtkickspeed.getText().toString());
        kickboxer.put("kickpower",edtkickpower.getText().toString());
        kickboxer.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e==null){
                FancyToast.makeText(SignUp.this,kickboxer.get("name")+"is saved to server", FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
            }

            else {
                    FancyToast.makeText(SignUp.this, e.getMessage(), FancyToast.LENGTH_LONG).show();
                }            }
        });
    }
}
