package com.example.administrator.xmlparser;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    private String xml;
    private List<Goods> goodsList = new ArrayList<Goods>();
    private AssetManager manager;
    private InputStreamReader is;
    private ListView listview;
    private Button PullParser;
    private Button SAXParser;
    private Button DOMParser;
    private ListViewAdapter adapter;
    private String result;
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result = "";
        listview = (ListView) findViewById(R.id.listviewContent);
        PullParser = (Button) findViewById(R.id.pullParser);
        SAXParser = (Button) findViewById(R.id.SAXParser);
        DOMParser = (Button) findViewById(R.id.DOMParser);
        PullParser.setOnClickListener(this);
        SAXParser.setOnClickListener(this);
        DOMParser.setOnClickListener(this);
        try
        {
            manager = getResources().getAssets();
            is = new InputStreamReader(manager.open("data.xml"));
            BufferedReader reader = new BufferedReader(is);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null)
            {
                sb.append(line + "\n");
            }
            xml = sb.toString();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                is.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.pullParser:
                goodsList = ParserXMLWithPull.getXmlContentForPull(xml);
                adapter = new ListViewAdapter(MainActivity.this, goodsList);
                listview.setAdapter(adapter);
                break;
            case R.id.SAXParser:
                goodsList = ParserXMLWithSAX.getXmlContentForSAX(xml);
                adapter = new ListViewAdapter(MainActivity.this, goodsList);
                listview.setAdapter(adapter);
                break;
            case R.id.DOMParser:
                goodsList = ParserXMLWithDOM.getXmlContentForDOM(xml);
                adapter = new ListViewAdapter(MainActivity.this, goodsList);
                listview.setAdapter(adapter);
                break;
        }
        if(v.getId() == R.id.pullParser)
        {
            result = "PULL解析数据成功！";
        }
        else if(v.getId() == R.id.SAXParser)
        {
            result = "SAX解析数据成功！";
        }
        else if(v.getId() == R.id.DOMParser)
        {
            result = "DOM解析数据成功！";
        }
        listview.addOnLayoutChangeListener(new View.OnLayoutChangeListener()
        {
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom)
            {
                Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
