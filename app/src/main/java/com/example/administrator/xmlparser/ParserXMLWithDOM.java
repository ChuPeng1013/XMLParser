package com.example.administrator.xmlparser;

import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by ChuPeng on 2016/9/12.
 */
public class ParserXMLWithDOM
{
    public static List<Goods> getXmlContentForDOM(String xml)
    {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        InputStream is = null;
        Document doc = null;
        NodeList goodsinfoProperties = null;
        String resultMessage = null;
        List<Goods> goodsList = new ArrayList<Goods>();
        try
        {
            builder = factory.newDocumentBuilder();
            is = new ByteArrayInputStream(xml.getBytes("UTF-8"));
            doc = builder.parse(is);
            Element rootElement = doc.getDocumentElement();
            NodeList properties = rootElement.getChildNodes();
            int res = 0;
            for (int j = 0; j < properties.getLength(); j++)
            {
                Node property = properties.item(j);
                String nodeName = property.getNodeName();
                if (nodeName.equals("rc"))
                {
                    res = Integer.parseInt(property.getFirstChild().getNodeValue());
                    Log.d("DOM", nodeName + "   " + res);
                }
                else if (nodeName.equals("rm"))
                {
                    resultMessage = property.getFirstChild().getNodeValue();
                    Log.d("DOM", nodeName + "   " + resultMessage);
                }
                else if(nodeName.equals("tasks"))
                {
                    goodsinfoProperties = property.getChildNodes();
                    Log.d("DOM", nodeName);
                }
            }
            if(res == 0 && goodsinfoProperties != null)
            {

                for(int i = 0; i < goodsinfoProperties.getLength(); i++ )
                {
                    Element personElement = (Element) goodsinfoProperties.item(i);
                    NodeList childNodes = personElement.getChildNodes();
                    Goods goods = new Goods();
                    for(int j = 0; j < childNodes.getLength(); j++)
                    {
                        Node property = childNodes.item(j);
                        String nodeName = property.getNodeName();
                        if(nodeName.equals("name"))
                        {
                            if(property.getFirstChild() == null)
                            {
                                goods.setName("");
                                Log.d("DOM", nodeName + "   " + goods.getName());
                            }
                            else
                            {
                                goods.setName(property.getFirstChild().getNodeValue());
                                Log.d("DOM", nodeName + "   " + goods.getName());
                            }
                        }
                        else if(nodeName.equals("refercode"))
                        {
                            if(property.getFirstChild() == null)
                            {
                                goods.setRefercode("");
                                Log.d("DOM", nodeName + "   " + goods.getRefercode());
                            }
                            else
                            {
                                goods.setRefercode(property.getFirstChild().getNodeValue());
                                Log.d("DOM", nodeName + "   " + goods.getRefercode());
                            }
                        }
                        else if(nodeName.equals("status"))
                        {
                            if(property.getFirstChild() == null)
                            {
                                goods.setStatus("");
                                Log.d("DOM", nodeName + "   " + goods.getStatus());
                            }
                            else
                            {
                                goods.setStatus(property.getFirstChild().getNodeValue());
                                Log.d("DOM", nodeName + "   " + goods.getStatus());
                            }
                        }
                    }
                    goodsList.add(goods);
                }
            }
        }
        catch (ParserConfigurationException e)
        {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        catch (SAXException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return goodsList;
    }
}
