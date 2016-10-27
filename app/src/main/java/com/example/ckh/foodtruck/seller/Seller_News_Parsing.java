package com.example.ckh.foodtruck.seller;

import com.example.ckh.cstview.Seller_News_EventFull;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by HOME on 2016-10-24.
 */
public class Seller_News_Parsing {
    ArrayList<Seller_News_EventFull> parseFest = new ArrayList<Seller_News_EventFull>();
    URL parseURL;
    final private String APPKEY = "5355487069676867313039576c765745";
    String url = "http://openAPI.seoul.go.kr:8088/" + APPKEY + "/xml/SearchConcertDetailService/1/50";
    String temp= "";




    ArrayList<Seller_News_EventFull> connectFest() {        // 실질적인 파싱을 하는 부분
        InputStream is = null;
        try {
            URL targetUrl = new URL(url);
            is = targetUrl.openStream();

            //XML Parser

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();
            parser.setInput(is, "utf-8");
            String tag;
            Seller_News_EventFull full = null;

            int parserEvent = parser.getEventType();
            while (parserEvent != XmlPullParser.END_DOCUMENT) {
                switch (parserEvent) {
                    case XmlPullParser.END_TAG:
                        tag = parser.getName();
                        if (tag.compareTo("row") == 0) {
                            parseFest.add(full);
                        }
                        break;
                    case XmlPullParser.START_TAG:
                        tag = parser.getName();
                        if (tag.compareTo("row") == 0) {
                            full = new Seller_News_EventFull();
                        } else if (full != null && tag.compareTo("TITLE")==0) {
                            parser.next();
                            temp=parser.getText();
                            if(temp==null){
                                full.setTitle("문의 바랍니다.");
                            }else{
                                full.setTitle(temp);}
                        } else if (full != null && tag.compareTo("STRTDATE")==0){
                            parser.next();
                            temp=parser.getText();
                            if(temp==null){
                                full.setStartDate("문의 바랍니다.");
                            }else{
                                full.setStartDate(temp+" ~ ");}
                        } else if (full != null && tag.compareTo("END_DATE")==0) {
                            parser.next();
                            temp=parser.getText();
                            if(temp==null){
                                full.setEndDate("");
                            }else{
                                full.setEndDate(temp);}
                        }  else if(full!= null & tag.compareTo("PLACE")==0){
                            parser.next();
                            temp=parser.getText();
                            if(temp==null){
                                full.setPlace("문의 바랍니다.");
                            }else{
                                full.setPlace(temp);}
                        }

                        else if ( full != null && tag.compareTo("MAIN_IMG")==0){
                            parser.next();
                            temp=parser.getText();
                            if(temp==null){
                                full.setMainIMGURL("Please Default Image (E)");
                            }else{
                                full.setMainIMGURL(temp);}

                        }
                        else{
                            parser.next();
                        }
                        break;



                }
                parserEvent=parser.next();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();

        } catch (XmlPullParserException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return parseFest;
    }
}
