package com.example.administrator.xmlparser;

import android.util.Log;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ChuPeng on 2016/9/12.
 */
public class ParserXMLWithPull
{
    private static List<Goods> goodsList = null;
    private static Goods goods = null;
    private static InputStream is;
    public static List<Goods> getXmlContentForPull(String xml)
    {
        try
        {
            is = new ByteArrayInputStream(xml.getBytes("UTF-8"));
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser = factory.newPullParser();
            xmlPullParser.setInput(is, "UTF-8");
            int eventType = xmlPullParser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT)
            {
                String nodeName = xmlPullParser.getName();
                switch(eventType)
                {
                    case XmlPullParser.START_DOCUMENT:
                    {
                        //开始解析时的初始化
                        goodsList = new ArrayList<Goods>();
                        Log.d("PULL", "开始解析");
                    }
                    break;
                    case XmlPullParser.START_TAG:
                    {
                        if("task".equals(nodeName))
                        {
                            goods = new Goods();
                        }
                        else if("name".equals(nodeName))
                        {
                            goods.setName(xmlPullParser.nextText());
                            Log.d("PULL", "--START_TAG()--" + "name");
                        }
                        else if("refercode".equals(nodeName))
                        {
                            goods.setRefercode(xmlPullParser.nextText());
                            Log.d("PULL", "--START_TAG()--" + "refercode");
                        }
                        else if("status".equals(nodeName))
                        {
                            goods.setStatus(xmlPullParser.nextText());
                            Log.d("PULL", "--START_TAG()--" + "status");
                        }
                        else
                        {
                            Log.d("PULL", "--START_TAG()");
                        }
                    }
                    break;
                    case XmlPullParser.END_TAG:
                    {
                        if("task".equals(nodeName))
                        {
                            goodsList.add(goods);
                            Log.d("PULL", "--END_TAG()--" + "status");
                        }
                        else
                        {
                            Log.d("PULL", "--END_TAG()");
                        }
                    }
                    break;
                    case XmlPullParser.END_DOCUMENT:
                    {
                        Log.d("PULL", "解析完成");
                    }
                    break;
                    default:
                        break;
                }
                eventType = xmlPullParser.next();
            }
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
            Log.d("PULL", "UnsupportedEncodingException");
        }
        catch (XmlPullParserException e)
        {
            e.printStackTrace();
            Log.d("PULL", "XmlPullParserException");
        }
        catch (IOException e)
        {
            e.printStackTrace();
            Log.d("PULL", "IOException");
        }
        return goodsList;
    }
}
