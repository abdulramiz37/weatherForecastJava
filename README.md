# weatherForecastJava
 
 
 Installed  thiss two depennciesssss
   <dependencies>

        <!-- https://mvnrepository.com/artifact/org.apache.httpcomponents/httpclient -->  /// for client
        <dependency>
            <groupId>org.apache.httpcomponents</groupId>
            <artifactId>httpclient</artifactId>
            <version>4.5.14</version>
        </dependency>
        <!-- https://mvnrepository.com/artifact/org.json/json -->  ///
        <dependency>
            <groupId>org.json</groupId>
            <artifactId>json</artifactId>
            <version>20220924</version>
        </dependency>

    </dependencies>
    
    
    package org.weatherforecast;

import netscape.javascript.JSObject;

import org.apache.http.HttpEntity;

import org.apache.http.client.methods.CloseableHttpResponse;

import org.apache.http.client.methods.HttpGet;

import org.apache.http.client.utils.URIBuilder;

import org.apache.http.impl.client.CloseableHttpClient;

import org.apache.http.impl.client.HttpClients;

import org.apache.http.util.EntityUtils;

import org.json.JSONArray;

import org.json.JSONObject;


import javax.swing.text.html.parser.Entity;

import java.io.IOException;

import java.net.URISyntaxException;


import java.util.Scanner;


public class WeatherForecastApp {


public static void main(String[] args){

    try {
    
        callWeatherForecastAPI();
        
    } catch (URISyntaxException e) {
    
        throw new RuntimeException(e);
        
    } catch (IOException e) {
    
        throw new RuntimeException(e);
        
    }
}

    public static void callWeatherForecastAPI() throws URISyntaxException, IOException {
    
    System.out.println("hjcsbhjs00");
    
    Scanner sc= new Scanner(System.in);
    
    String location =sc.nextLine();
    

    URIBuilder builder = new URIBuilder("https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/weatherdata/forecast"); //Rest Api

    builder.setParameter("aggregateHours","24");
    builder.setParameter("contentType","json");
    builder.setParameter("unitGroup","metric");
    builder.setParameter("locationMode","single");
    builder.setParameter("locations",location);
    builder.setParameter("key","1PYNQ6AWUDJE9AFERDCHJHSXK");

        HttpGet getData = new HttpGet(builder.build());

    CloseableHttpClient httpClient = HttpClients.createDefault();


    CloseableHttpResponse response =httpClient.execute(getData);
       
       httpClient.close();


        if(response.getStatusLine().getStatusCode()==200) {
           
           HttpEntity responseEnty = response.getEntity();
           
           String result = EntityUtils.toString(responseEnty);
           
           System.out.println("datetimeStr \t \t \t  mint  \t \t maxt  \t \t  visibility  \t \t humidity");

            //Json response Formatting for attributes

            JSONObject responseObject = new JSONObject(result);
            JSONObject locationObject = responseObject.getJSONObject("location");
            JSONArray ValueObject = locationObject.getJSONArray("values");
            for(int i=0;i<ValueObject.length();i++){
                JSONObject value = ValueObject.getJSONObject(i);

                String dateTime = value.getString("datetimeStr");

                Double minTemp= value.getDouble("mint");
                Double maxTemp= value.getDouble("maxt");
                Double humidity = value.getDouble("humidity");
                Double visibility= value.getDouble("visibility");
                System.out.printf("%s \t %f \t %f \t %f \t %f \n", dateTime, minTemp,maxTemp,humidity,visibility);

            }

        }else{
            System.out.println("Something went wrong");
            throw new NoWeatherDataException("Something went wrong !");
        }

    }




}
