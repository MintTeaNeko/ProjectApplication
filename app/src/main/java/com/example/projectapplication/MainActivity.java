package com.example.projectapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {
    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        queue = Volley.newRequestQueue(this);

        findViewById(R.id.submit_button).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Toast toast_notification = Toast.makeText(getApplicationContext(), "Hello world", Toast.LENGTH_LONG);
                toast_notification.show();
            }

        });
    }

    public boolean SendLoginDetails(String user_email, String password)
    {
        final boolean[] sucessfullyLogged = {false};
        String login_url = String.format("http://127.0.0.1/login?e=%s&p=%s", user_email, password);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(login_url, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {
                    sucessfullyLogged[0] = response.getBoolean("state");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }, null);

        queue.add(jsonObjectRequest);
        return sucessfullyLogged[0];
    }

}
