package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class SignUp extends AppCompatActivity implements View.OnClickListener{

    private Button btnsave;
    private EditText edtname,edtpunchspeed,edtpunchpower,edtkickspeed,edtkickpower;
    private TextView txtgetdata;
    private Button btngetalldata;
    private String allKickBoxers;


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
        txtgetdata=findViewById(R.id.txtgetdata);
        btngetalldata=findViewById(R.id.btngetalldata);

        txtgetdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseQuery<ParseObject> parsequery=ParseQuery.getQuery("KickBoxer");
                parsequery.getInBackground("HQbXenDXqm", new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject object, ParseException e) {
                        if(object!=null&& e==null){
                            txtgetdata.setText(object.get("name")+"-"+"Punch Power:"+object.get("punchpower"));
                        }

                    }
                });
            }
        });

        btngetalldata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allKickBoxers="";
                ParseQuery<ParseObject> queryall=ParseQuery.getQuery("KickBoxer");
                queryall.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {
                        if(e==null){
                            if(objects.size()>0){

                                for(ParseObject KickBoxer:objects){
                                    allKickBoxers=allKickBoxers+KickBoxer.get("name")+"\n";
                                }

                                FancyToast.makeText(SignUp.this,allKickBoxers,FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
                            }
                            else
                            {
                                FancyToast.makeText(SignUp.this, e.getMessage(), FancyToast.LENGTH_LONG).show();
                            }
                        }
                    }
                });
            }
        });
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
