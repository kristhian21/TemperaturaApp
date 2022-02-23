package edu.escuelaing.arep.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class ConcurrencyTest extends  Thread{

    private static final String USER_AGENT = "Mozilla/5.0";
    private String GET_URL = "https://conversor-arep.herokuapp.com/";

    public ConcurrencyTest(String adicionURL) {
        GET_URL += adicionURL;
    }

    public static void main(String[] args) {
        ArrayList<ConcurrencyTest> temp = new ArrayList<>();
        for(int i =0;i<15;i++){
            temp.add(new ConcurrencyTest("celsius?celsius="+i));
        }
        for(int i =0;i<15;i++){
            temp.add(new ConcurrencyTest("fahrenheit?fahrenheit="+i));
        }
        for (ConcurrencyTest i : temp)i.start();
    }

    @Override
    public void run(){

        try{
            URL obj = new URL(GET_URL);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", USER_AGENT);
            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                System.out.println(response.toString());
            } else {
                System.out.println("GET request not worked");
            }
            System.out.println("GET DONE");
        }catch(IOException e){
            System.out.println("Somthing isn't working");
        }
    }

}
