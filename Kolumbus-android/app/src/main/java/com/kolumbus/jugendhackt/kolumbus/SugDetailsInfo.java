package com.kolumbus.jugendhackt.kolumbus;

import android.app.Activity;
import android.media.Image;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import org.droidparts.net.http.HTTPException;
import org.droidparts.net.http.RESTClient2;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class SugDetailsInfo extends Activity {

    String CAT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sug_details_info);

        TextView tV_Name = (TextView)findViewById(R.id.tV_Name);
        RatingBar ratingbar = (RatingBar)findViewById(R.id.detail_ratingBar);
        ImageView iV_Detail = (ImageView)findViewById(R.id.iV_Detail);

        tV_Name.setText(übergabe.Name);

        /*String Url = "http://secure-mountain-7532.herokuapp.com/v1/suggestions?accomodation_lat=" +
                "53.00000&accomodation_lng=" +
                "13.00000&starts_at=" +
                MyMind.startDate + "&ends_at=" +
                MyMind.endDate + "&visited_count=" +
                String.valueOf(MyMind.earlierVisits) + "&budget_class=" +
                String.valueOf(MyMind.budget);



        if (übergabe.Categorie=="Abendessen")CAT="dinner";
        if (übergabe.Categorie=="Mittagessen")CAT="lunch";
        if (übergabe.Categorie=="Cafe")CAT="cafe";
        if (übergabe.Categorie=="Sehenswürdigkeiten")CAT="sights to see";
        if (übergabe.Categorie=="Museum")CAT="museum";


        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }


        RESTClient2 client =new RESTClient2(this);
        try {
            JSONObject mJsonObject = client.getJSONObject(Url);
            JSONArray mJsonArray = new JSONArray(mJsonObject.get(CAT).toString());


            for (int i=0; i < mJsonArray.length();i++) {
                JSONObject temp = mJsonArray.getJSONObject(i);

                String name = temp.getString("name");



            }
        }catch (JSONException e){
            e.printStackTrace();
        }catch (HTTPException e){
            e.printStackTrace();
        }*/
    }

}
