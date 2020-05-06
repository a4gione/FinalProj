package com.example.mymobileapplogin;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;
import org.json.JSONException;
import org.json.JSONObject;
import java.net.URI;
import java.net.URISyntaxException;

public class Search extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.mymobileapplogin";
    Button searchXP;
    EditText textXP;
    RequestQueue queue;
    Button favoritesXP;

    //API
    private final String api_key = "1671dd55";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchXP = findViewById(R.id.button);
        textXP = findViewById(R.id.editText);
        favoritesXP = findViewById(R.id.button2);

        //Volley Request
        queue = queue = Volley.newRequestQueue(this);

        favoritesXP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Search.class);
                // validate user
//                if (ed1.getText().toString().equals("admin") &&
//                        ed2.getText().toString().equals("admin"))
                Toast.makeText(getApplicationContext(),
                        "ADDED TO FAVORITES", Toast.LENGTH_LONG).show();
                startActivity(intent);
            }
        });

    }

    public void searchClicked(View v) {
        String query = textXP.getText().toString(); //Get the title of the movie.
        String url = String.format("https://www.omdbapi.com/?t=%s&apikey=%s", query, api_key);
        System.out.println(url);

        //String Request
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject data = new JSONObject(response);
                            DisplayMovie(data);
                        } catch (JSONException e) {
                            System.out.println("ERROR PARSING RESPONSE");
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("ERROR WITH REQUEST");
            }
        });
        getRequestQueue().add(stringRequest);
    }


    //Request Queue
    public RequestQueue getRequestQueue() {
        if (queue == null) {
            queue = Volley.newRequestQueue(this);
        }
        return this.queue;

    }

    public void DisplayMovie(JSONObject data) {
        final TextView textViews = findViewById(R.id.textView);
        final ImageView imageView = findViewById(R.id.imageView);

        String name;
        URI poster;
        try {
            System.out.println(data.getString("Title"));
            name = data.getString("Title");
            System.out.println(data.getString("Genre"));
            //poster = data.get("Poster");
            poster = new URI(data.getString("Poster"));
            //imageView.setImageURI(https://m.media-amazon.com/images/M/MV5BMTYwNjAyODIyMF5BMl5BanBnXkFtZTYwNDMwMDk2._V1_SX300.jpg);
            textViews.setText(name);

            Picasso.get().load(String.valueOf(poster)).into(imageView);

        } catch (JSONException | URISyntaxException e) {
            System.out.println("ERROR DISPLAYING RESPONSE");
        }
    }
}



