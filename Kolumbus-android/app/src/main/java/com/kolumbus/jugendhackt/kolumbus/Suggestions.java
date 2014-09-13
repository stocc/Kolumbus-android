package com.kolumbus.jugendhackt.kolumbus;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.applidium.headerlistview.HeaderListView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.droidparts.net.http.HTTPException;
import org.droidparts.net.http.RESTClient2;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;


public class Suggestions extends Activity {

    public String Url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestions);

        //GET Online DATA

         Url = "http://niklas-mbp.local:3000/v1/suggestions?accomodation_lat=" +
                "53.00000&accomodation_lng=" +
                "13.00000&starts_at=" +
                MyMind.startDate + "&ends_at=" +
                MyMind.endDate + "&visited_count=" +
                MyMind.earlierVisits + "&budget_class=" +
                MyMind.budget;


        //Got the dat to insert them to SUG[]
        Sug sug_data[] = new Sug[]{
               new Sug(R.drawable.plane15,"Flughafen","faszinierend an zu sehen"),
                new Sug(R.drawable.ic_launcher,"Test","Untertitel"),
                new Sug(R.drawable.ic_launcher,"Test","Untertitel"),
                new Sug(R.drawable.ic_launcher,"Test","Untertitel"),
                new Sug(R.drawable.ic_launcher,"Test","Untertitel"),
                new Sug(R.drawable.ic_launcher,"Test","Untertitel"),
                new Sug(R.drawable.ic_launcher,"Test","Untertitel"),
                new Sug(R.drawable.ic_launcher,"Test","Untertitel"),
                new Sug(R.drawable.ic_launcher,"Test","Untertitel"),
                new Sug(R.drawable.ic_launcher,"Test","Untertitel")
        };

        final ListView SugList = (ListView)findViewById(R.id.listView);

        /*View header = (View)getLayoutInflater().inflate(R.layout.suggestions_list_header, null);
        SugList.addHeaderView(header);*/


        SugAdapter adapter = new SugAdapter(this,R.layout.suggestions_list_layout,sug_data);
        SugList.setAdapter(adapter);



    }

    public void loadSuggestion(){
        RESTClient2 client =new RESTClient2(this);

        try {
            JSONObject mJsonObject = client.getJSONObject(Url);

            for (Iterator<String> iter = mJsonObject.keys(); iter.hasNext(); ) {
                String key = iter.next();
                JSONArray mJasonArray = mJsonObject.getJSONArray(key);

                for (int i =0;i<mJasonArray.length();i++){
                    String id = mJsonObject.getString("id");
                    Toast.makeText(getApplicationContext(),id,Toast.LENGTH_LONG).show();
                }

            }



        }catch (JSONException e){
            e.printStackTrace();
        }catch (HTTPException e){
            e.printStackTrace();
        }
    }



}
