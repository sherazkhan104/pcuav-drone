package com.example.apitest;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    TextView tv1;
    Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tv1 = findViewById(R.id.tv1);
        btn1 = findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            // ...

                // Instantiate the RequestQueue.
                String url = "https://pcuav.pythonanywhere.com/api/plant/";
                JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        String cityID="";
                        try {
                            JSONObject cityinfo = response.getJSONObject(0);
                            cityID=cityinfo.getString("created_on");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Toast.makeText(MainActivity.this,"city Id= "+cityID,Toast.LENGTH_LONG).show();
                    }
                },new Response.ErrorListener(){
                    public void onErrorResponse(VolleyError error){
                        Toast.makeText(MainActivity.this,"something Wrong",Toast.LENGTH_LONG).show();}
                    });
                MySingleton.getInstance(MainActivity.this).addToRequestQueue(jsonArrayRequest);
            }
        });
    }
}