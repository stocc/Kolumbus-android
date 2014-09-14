package com.kolumbus.jugendhackt.kolumbus;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.DataSetObserver;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import org.droidparts.net.http.HTTPException;
import org.droidparts.net.http.RESTClient2;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


public class Suggestions extends Activity {

    List<String> lunch;
    List<String> dinner;
    List<String> sights;
    List<String> museum;
    List<String> cafe;



    public String Url;
    ImageView img;
    Bitmap bitmap;
    ProgressDialog pDialog;

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestions);


        Button btn_sugnext = (Button)findViewById(R.id.sug_continue);
        btn_sugnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Suggestions.this, timetable.class);
                startActivity(intent);
            }
        });


        // GET Online DATA
         Url = "http://secure-mountain-7532.herokuapp.com/v1/suggestions?accomodation_lat=" +
                "53.00000&accomodation_lng=" +
                "13.00000&starts_at=" +
                MyMind.startDate + "&ends_at=" +
                MyMind.endDate + "&visited_count=" +
                String.valueOf(MyMind.earlierVisits) + "&budget_class=" +
                String.valueOf(MyMind.budget);

         expListView = (ExpandableListView) findViewById(R.id.listView);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
        expListView.setAdapter(listAdapter);



        // Listview on child click listener
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                Toast.makeText(
                        getApplicationContext(),
                        listDataHeader.get(groupPosition)
                                + " : "
                                + listDataChild.get(
                                listDataHeader.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT)
                        .show();
                return false;
            }
        });


    }


    private class LoadImage extends AsyncTask<String, String, Bitmap> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Suggestions.this);
            pDialog.setMessage("Loading Image ....");
            pDialog.show();
        }
        protected Bitmap doInBackground(String... args) {
            try {
                bitmap = BitmapFactory.decodeStream((InputStream) new URL(args[0]).getContent());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }
        protected void onPostExecute(Bitmap image) {
            if(image != null){
                img.setImageBitmap(image);
                pDialog.dismiss();
            }else{
                pDialog.dismiss();
                Toast.makeText(Suggestions.this, "ERROR Loading Image", Toast.LENGTH_SHORT).show();
            }
        }
    }


    public void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        // Adding child data
        listDataHeader.add("Abendessen");
        listDataHeader.add("Mittagessen");
        listDataHeader.add("Sehenswürdigkeiten");
        listDataHeader.add("Museum");
        listDataHeader.add("Cafe");

        //-----------------------------------------

        dinner = new ArrayList<String>();

        RESTClient2 client =new RESTClient2(this);
        try {
            JSONObject mJsonObject = client.getJSONObject(Url);
            JSONArray mJsonArray = new JSONArray(mJsonObject.get("dinner").toString());
            System.out.println("After entrance to Dinner");

            for (int i=0; i < mJsonArray.length();i++) {
                JSONObject temp = mJsonArray.getJSONObject(i);

                String name = temp.getString("name");
                System.out.print(name);

                dinner.add(name);
            }
        }catch (JSONException e){
            e.printStackTrace();
        }catch (HTTPException e){
            e.printStackTrace();
        }

        //-----------------------------------------

        lunch = new ArrayList<String>();

        RESTClient2 client2 =new RESTClient2(this);
        try {
            JSONObject mJsonObject = client2.getJSONObject(Url);
            JSONArray mJsonArray = new JSONArray(mJsonObject.get("lunch").toString());
            System.out.println("After entrance to lunch");

            for (int i=0; i < mJsonArray.length();i++) {
                JSONObject temp = mJsonArray.getJSONObject(i);

                String name = temp.getString("name");
                System.out.print(name);

                lunch.add(name);
            }
        }catch (JSONException e){
            e.printStackTrace();
        }catch (HTTPException e){
            e.printStackTrace();
        }

        //-----------------------------------------

        sights = new ArrayList<String>();

        RESTClient2 client3 =new RESTClient2(this);
        try {
            JSONObject mJsonObject = client3.getJSONObject(Url);
            JSONArray mJsonArray = new JSONArray(mJsonObject.get("sights to see").toString());
            System.out.println("After entrance to lunch");

            for (int i=0; i < mJsonArray.length();i++) {
                JSONObject temp = mJsonArray.getJSONObject(i);

                String name = temp.getString("name");
                System.out.print(name);

                sights.add(name);
            }
        }catch (JSONException e){
            e.printStackTrace();
        }catch (HTTPException e){
            e.printStackTrace();
        }

        //-----------------------------------------

        museum = new ArrayList<String>();

        RESTClient2 client4 =new RESTClient2(this);
        try {
            JSONObject mJsonObject = client4.getJSONObject(Url);
            JSONArray mJsonArray = new JSONArray(mJsonObject.get("museum").toString());
            System.out.println("After entrance to lunch");

            for (int i=0; i < mJsonArray.length();i++) {
                JSONObject temp = mJsonArray.getJSONObject(i);

                String name = temp.getString("name");
                System.out.print(name);

                museum.add(name);
            }
        }catch (JSONException e){
            e.printStackTrace();
        }catch (HTTPException e){
            e.printStackTrace();
        }

        //------------------------------

        cafe = new ArrayList<String>();

        RESTClient2 client5 =new RESTClient2(this);
        try {
            JSONObject mJsonObject = client5.getJSONObject(Url);
            JSONArray mJsonArray = new JSONArray(mJsonObject.get("cafe").toString());
            System.out.println("After entrance to lunch");

            for (int i=0; i < mJsonArray.length();i++) {
                JSONObject temp = mJsonArray.getJSONObject(i);

                String name = temp.getString("name");
                System.out.print(name);

                cafe.add("2 Guns");
            }
        }catch (JSONException e){
            e.printStackTrace();
        }catch (HTTPException e){
            e.printStackTrace();
        }

        cafe = new ArrayList<String>();
        cafe.add("2 Guns");


        listDataChild.put(listDataHeader.get(0), dinner); // Header, Child data
        listDataChild.put(listDataHeader.get(1), lunch);
        listDataChild.put(listDataHeader.get(2), sights);
        listDataChild.put(listDataHeader.get(3), museum);
        listDataChild.put(listDataHeader.get(4), cafe);

        ProgressBar loader = (ProgressBar)findViewById(R.id.loading);
        loader.setVisibility(View.GONE);



    }






}





































/*//Got the dat to insert them to SUG[]
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
        };*/

//final ListView SugList = (ListView)findViewById(R.id.listView);

        /*View header = (View)getLayoutInflater().inflate(R.layout.suggestions_list_header, null);
        SugList.addHeaderView(header);*/

        /*SugAdapter adapter = new SugAdapter(this,R.layout.suggestions_list_layout,sug_data);
        SugList.setAdapter(adapter);*/