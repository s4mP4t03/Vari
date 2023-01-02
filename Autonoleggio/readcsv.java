/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Autonoleggio;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author samue
 */
public class readcsv {
    
    List list1;
    
    public readcsv(String file)
    {
        list1 = create_list(file);
    }
    
    private List create_list (String file)
    {
        String delimiter = ", ";
        String line = null;
        List lines = new ArrayList();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) 
        {
            while((line = br.readLine()) != null)
            {
                List values = Arrays.asList(line.split(delimiter));
                lines.add( values);
            }
        } 
        catch (Exception e)
        {
            System.out.println(e); 
        }

        return lines;
    } 
}
