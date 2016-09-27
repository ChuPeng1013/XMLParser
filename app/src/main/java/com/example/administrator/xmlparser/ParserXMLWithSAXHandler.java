package com.example.administrator.xmlparser;

import android.util.Log;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ChuPeng on 2016/9/19.
 */
public class ParserXMLWithSAXHandler extends DefaultHandler
{
    //储存所有解析的元素
    private List<Goods> goodsList;
    //储存正在解析的元素数据
    private Goods goods;
    private String nodeName;

    public List<Goods> getList()
    {
        return goodsList;
    }

    public void startDocument() throws SAXException
    {
        //初始化
        goodsList = new ArrayList<Goods>();
        Log.d("SAX", "--startDocument()--");
    }

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
    {
        nodeName = localName;
        if("task".equals(nodeName))
        {
            goods = new Goods();
        }
        Log.d("SAX", "--startElement()--"+qName);
    }

    public void characters(char[] ch, int start, int length) throws SAXException
    {

        if("name".equals(nodeName))
        {
            goods.setName(String.copyValueOf(ch, start, length));
            Log.d("SAX", "--characters()--" + nodeName + "      " + goods.getName());
        }
        else if("refercode".equals(nodeName))
        {
            goods.setRefercode(String.copyValueOf(ch, start, length));
            Log.d("SAX", "--characters()--" + nodeName + "      " + goods.getRefercode());
        }
        else if("status".equals(nodeName))
        {
            goods.setStatus(String.copyValueOf(ch, start, length));
            Log.d("SAX", "--characters()--" + nodeName + "      " + goods.getStatus());
        }
    }

    public void endElement(String uri, String localName, String qName) throws SAXException
    {
        if("task".equals(localName))
        {
            goodsList.add(goods);
            Log.d("SAX", "--endElement()--" + localName +"将数据存储到集合中");
        }
        else
        {
            Log.d("SAX", "--endElement()--" + localName);
        }

    }

    public void endDocument() throws SAXException
    {
        Log.d("SAX", "--endDocument()--");
    }


}
