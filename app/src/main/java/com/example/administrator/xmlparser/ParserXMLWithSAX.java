package com.example.administrator.xmlparser;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by ChuPeng on 2016/9/12.
 */
public class ParserXMLWithSAX
{
    private List<Goods> goodsList;
    public static List<Goods> getXmlContentForSAX(String xml)
    {
        try
        {
            //创建一个解析XML的工厂对象
            SAXParserFactory factory = SAXParserFactory.newInstance();
            //创建一个解析XML的对象
            XMLReader xmlReader = factory.newSAXParser().getXMLReader();
            //创建一个解析助手类
            ParserXMLWithSAXHandler handler = new ParserXMLWithSAXHandler();
            //将ParserXMLWithSAXHandler的实例设置到XMLReader中
            xmlReader.setContentHandler(handler);
            //开始解析
            xmlReader.parse(new InputSource(new StringReader(xml)));
            return handler.getList();
        } catch (SAXException e)
        {
            e.printStackTrace();
        }
        catch (ParserConfigurationException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {

        }
        return null;
    }
}
