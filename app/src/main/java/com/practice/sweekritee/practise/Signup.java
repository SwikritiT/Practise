package com.practice.sweekritee.practise;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Signup extends AppCompatActivity {
    private EditText uname,pass;
    private Button signup;
    String reg_url="http:// 192.168.1.104/practice/signup.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        uname=(EditText) findViewById(R.id.username);
        pass=(EditText) findViewById(R.id.password);
        signup=(Button) findViewById(R.id.signup);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String name=uname.getText().toString().trim();

                final String pword=pass.getText().toString().trim();
                if(name.equals("")|| pword.equals("")){
                    Toast.makeText(Signup.this,"Fill up th field properly",Toast.LENGTH_SHORT).show();

                }
                else{
                    StringRequest stringRequest= new StringRequest(Request.Method.POST, reg_url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try {
                                        JSONArray jsonArray=new JSONArray(response);
                                        JSONObject jsonResponse=jsonArray.getJSONObject(0);
                                       // JSONObject jsonResponse = new JSONObject(response);
                                        Boolean success = jsonResponse.getBoolean("flag");

                                        Log.i("success", success + "");

                                        if (success) {

                                            Intent in = new Intent(getApplicationContext(), homepage.class);

                                            startActivity(in);
                                        }
                                        else {
                                            Toast.makeText(getApplicationContext(),"Username and pw not valid",Toast.LENGTH_SHORT).show();


                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }







                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String,String> params= new HashMap<String, String>();
                            params.put("username",name);
                            params.put("password",pword);
                            return params;
                        }
                    };
                    MySingelton.getInstance(Signup.this).addToRequestQueue(stringRequest);

                }





                    }
//                Intent intent= new Intent(Signup.this,homepage.class);
//                startActivity(intent);

            });


    }
//    public void register(){
//        String name=uname.getText().toString().trim();
//
//        String pword=pass.getText().toString().trim();
//        String method="signup";
//        Backgroundtask backgroundtask=new Backgroundtask(this);
//        backgroundtask.execute(method,name,pword);
//        finish();
//    }
}
