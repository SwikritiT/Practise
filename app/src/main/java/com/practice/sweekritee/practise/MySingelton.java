package com.practice.sweekritee.practise;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by SWEEKRITEE on 7/24/2018.
 */

public class MySingelton {
    private  static MySingelton mInstance;
    private RequestQueue requestQueue;
    private static Context mCtx;
    private MySingelton(Context context)
    {
        mCtx= context;
        requestQueue=getRequestQueue();
    }
    public RequestQueue getRequestQueue(){
        if(requestQueue==null){
            requestQueue= Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return requestQueue;
    }
    public static synchronized MySingelton getInstance(Context context){
        if(mInstance==null){
            mInstance=new MySingelton(context);
        }
        return mInstance;
    }
    public <T>void addToRequestQueue(Request<T>request){
        requestQueue.add(request);
    }
}
