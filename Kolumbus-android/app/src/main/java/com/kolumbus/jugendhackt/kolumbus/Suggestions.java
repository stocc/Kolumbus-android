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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.androidquery.util.Progress;
import com.applidium.headerlistview.HeaderListView;
import com.applidium.headerlistview.SectionAdapter;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
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
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

@EActivity
public class Suggestions extends Activity {

    public String Url;
    ImageView img;
    Bitmap bitmap;
    ProgressDialog pDialog;
    Sug sug_data[];

    @ViewById(R.id.loading)
    ProgressBar loading;

    @ViewById(R.id.listView)
    ListView SugList;


    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestions);

        //GET Online DATA
         Url = "http://secure-mountain-7532.herokuapp.com/v1/suggestions?accomodation_lat=" +
                "53.00000&accomodation_lng=" +
                "13.00000&starts_at=" +
                MyMind.startDate + "&ends_at=" +
                MyMind.endDate + "&visited_count=" +
                String.valueOf(MyMind.earlierVisits) + "&budget_class=" +
                String.valueOf(MyMind.budget);

        loadSuggestion();

        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.listView);

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);



        // setting list adapter
        expListView.setAdapter(listAdapter);

    }

    public void loadSuggestion(){
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        loadData();

    }

    @UiThread
    public void loadData() {
        RESTClient2 client =new RESTClient2(this);
        System.out.print("ich bin noch da gleich mach ich die url auf");
        Log.d("Hallo", "Help me");


        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Abendessen");
        listDataHeader.add("Mittagessen");
        listDataHeader.add("Sehenswürdigkeiten");
        listDataHeader.add("Museum");
        listDataHeader.add("Cafe");

        try {
            JSONObject mJsonObject = client.getJSONObject(Url);
            JSONArray mJsonArray = new JSONArray(mJsonObject.get("dinner").toString());
            System.out.println("After entrance to Dinner");

            for (int i=0; i < mJsonArray.length();i++) {
                JSONObject temp = mJsonArray.getJSONObject(i);

                String name = temp.getString("name");

                List<String> dinner = new ArrayList<String>();
                dinner.add(name);

                listDataChild.put(listDataHeader.get(0), dinner); // Header, Child data

                System.out.println(name);
            }


           // listDataChild.put(listDataHeader.get(1), nowShowing);
            //listDataChild.put(listDataHeader.get(2), comingSoon);

        }catch (JSONException e){
            e.printStackTrace();
        }catch (HTTPException e){
            e.printStackTrace();
        }
    }

    @UiThread
    public void updateUI() {
        SugAdapter adapter = new SugAdapter(this,R.layout.suggestions_list_layout,sug_data);
        System.out.print(sug_data);
        SugList.setAdapter(adapter);

        loading.setVisibility(View.GONE);
    }

    //Image Laden
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