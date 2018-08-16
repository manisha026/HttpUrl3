package com.example.thakur.httpurl3;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ParserClass parserClass=new ParserClass();
    ArrayList<ModelClass>data=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        parserClass.execute();



    }

    public class ParserClass extends AsyncTask{

        JSONObject jsonObject;
        ArrayList<ModelClass>job=new ArrayList<>();
        String uid,name,author,publishedAt,urlToImage;

        @Override
        protected Object doInBackground(Object[] objects) {
            URL url=null;

            try {
                url = new URL("https://newsapi.org/v2/top-headlines?sources=techcrunch&apiKey=ca56f4dcf86a4487aa26437f54dc941d");

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                InputStream is = httpURLConnection.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);

                BufferedReader br = new BufferedReader(isr);
                StringBuffer sb = new StringBuffer();

                String line;
                while (!((line = br.readLine()) == null)) {
                    sb.append(line);
                }

                String json = sb.toString();
                jsonObject = new JSONObject(json);

                JSONArray jsonArray = jsonObject.getJSONArray("articles");

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject childObj = jsonArray.getJSONObject(i);

                    JSONObject childObj2 = childObj.getJSONObject("source");

                    uid = childObj2.getString("id");
                    name = childObj2.getString("name");
                    author = childObj.getString("author");
                    publishedAt = childObj.getString("publishedAt");
                    if (childObj.has("urlToImage")) {
                        urlToImage = childObj.getString("urlToImage");
                    }

                    ModelClass modelClass = new ModelClass();

                    modelClass.setId(uid);
                    modelClass.setName(name);
                    modelClass.setAuthor(author);
                    modelClass.setPublishedAt(publishedAt);
                    modelClass.setUrlToImage(urlToImage);
                    data.add(modelClass);
                }
                DemoAdapter demoAdapter=new DemoAdapter(MainActivity.this,data);
                recyclerView.setAdapter(demoAdapter);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            return data;
        }
    }
}
