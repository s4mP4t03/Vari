/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Autonoleggio;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author samue
 */
public class Data {
    //"Wed Mar 23 19:09:39 CET 2022"
    long diff_data;
    String data;
    public Data(String data) throws ParseException
    {
        diff_data = diff_data(data);
    }
    public Data()
    {
        data = getData();
    }
    private long diff_data (String data) throws ParseException 
    {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM d HH:mm:ss Z yyyy");
        Calendar c1 = Calendar.getInstance();
        Date dateOne = c1.getTime();

        Date d2 = sdf.parse(data);
        long diff = dateOne.getTime() - d2.getTime();
        long diff_data = TimeUnit.MILLISECONDS.toDays(diff);
        return diff_data;
    }
    
    private String getData()
    {
        Calendar c1 = Calendar.getInstance();
        Date dateOne = c1.getTime();
        data = dateOne.toString();
        return data;
    }
}
