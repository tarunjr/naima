package nalanda.com.doctor.network;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
 * Created by ps1 on 9/11/16.
 */
public class VolleyUtil {
    private static VolleyUtil volleyUtil;

    public static VolleyUtil getInstance() {
        if(volleyUtil == null) {
            volleyUtil = new VolleyUtil();
        }
        return volleyUtil;
    }

    private VolleyUtil() {
    }

    public void doGet(final Context context, String url, Response.Listener<String> successListener) {
        StringRequest stringRequest = new StringRequest(url, successListener,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context,error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    public void doPost(final Context context, String url, final String body, final String contentType, Response.Listener<String> successListener) {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, successListener,
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(context,error.getMessage(),Toast.LENGTH_LONG).show();
                        }
                    }){

                @Override
                public byte[] getBody() throws AuthFailureError {
                    if(!TextUtils.isEmpty(body)) {
                        return body.getBytes();
                    } else {
                        return super.getBody();
                    }
                }

                @Override
                public String getBodyContentType() {
                    if(!TextUtils.isEmpty(contentType)) {
                        return contentType;
                    } else {
                        return super.getBodyContentType();
                    }
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(context);
            requestQueue.add(stringRequest);
    }
}
