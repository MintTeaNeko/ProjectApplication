package com.example.projectapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        queue = Volley.newRequestQueue(this);

        TextView email_input = findViewById(R.id.email_input);
        TextView pass_input = findViewById(R.id.password_input);
        
        findViewById(R.id.submit_button).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                SendLoginDetails(email_input.getText(), pass_input.getText());
            }

        });
    }

    public void SendLoginDetails(CharSequence user_email, CharSequence password)
    {
        String login_url = String.format("http://10.0.2.2:5000/login?e=%s&p=%s", user_email.toString(), password.toString());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, login_url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Toast toast_notification = Toast.makeText(getApplicationContext(), "", Toast.LENGTH_LONG);
                    if (response.getBoolean("success")) {
                        toast_notification.setText("was sucessfully logged in");
                    }
                    else {
                        toast_notification.setText(response.getString("reason"));
                    }
                    toast_notification.show();

                } catch (JSONException e) {
                    e.printStackTrace();

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast toast_notification = Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG);
                toast_notification.show();
            }
        });

        queue.add(jsonObjectRequest);
    }
}
