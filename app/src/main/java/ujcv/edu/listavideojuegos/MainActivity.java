package ujcv.edu.listavideojuegos;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    private RequestQueue queue;

    private TextView nombreJuego;
    private ImageView fotoprincipal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        obtenerDatosVolley();
    }
    private void obtenerDatosVolley() {
        //nombreJuego = findViewById(R.id.juegoprincipal);
        //fotoprincipal = findViewById(R.id.imagenprincipal);
        queue = Volley.newRequestQueue(this);

        String url = "https://rawg-video-games-database.p.rapidapi.com/games";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray mJsonArray = response.getJSONArray("results");

                    //for (int i = 0 ; i<mJsonArray.length() ; i++){

                        JSONObject mJsonObj = mJsonArray.getJSONObject(6);

                        String name = mJsonObj.getString("name");
                       String foto = mJsonObj.getString("background_image");

                        //nombreJuego.setText(name);

                        //Picasso.get()
                         //   .load(foto)
                         //       .resize(50, 50)
                          //  .into(fotoprincipal);

                    //}
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        })
        {

          public Map getHeaders() throws AuthFailureError{
              HashMap headers = new HashMap();
              headers.put("x-rapidapi-host", "rawg-video-games-database.p.rapidapi.com");
              headers.put("x-rapidapi-key", "67036e1220msh05a699a10c679c1p151158jsn4d3d4d2bb094");
              return headers;

        }

        };

        queue.add(request);
    }



}