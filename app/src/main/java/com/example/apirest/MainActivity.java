package com.example.apirest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
 //Create API connect
 //Tieu Ha Anh Khoi - CE140252
public class MainActivity extends AppCompatActivity {
    private TextView textView;//declare variable textView
    private RequestQueue requestQueue;//declare variable requestQueue
    private Button buttonParse;//declare variable buttonParse
    int i=0;//declare the variable i to count the number of times the user presses the button
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.text_view_result);//look for button text_view_result on layout activity_main
        buttonParse = findViewById(R.id.button_parse);//look for button button_parse on layout activity_main
        requestQueue= Volley.newRequestQueue(this);
        //Event catch on click from user
        buttonParse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(i<1) { //display the return data from json 1 time
                    jsonParse();// call function to generate json data
                    i++;//increase the variable i
                }
            }
        });
    }

    /**
     * Method to generate json data into text view
     */
    private void jsonParse(){
        String url="https://www.myjsons.com/v/542291";//create link connect json
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray=response.getJSONArray("employess");//title in json file
                            for(int i=0;i<jsonArray.length();i++){//loop iterates through the elements in employees to display in text view
                                JSONObject employee=jsonArray.getJSONObject(i);
                                String name=employee.getString("name");//get data from name key
                                int age=employee.getInt("age");//get data from age key
                                String mail=employee.getString("email");//get data from email key
                                textView.append(name+", "+String.valueOf(age)+", "+mail+"\n\n");//display the data retrieved from the json file file
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(request);//add data
    }
}