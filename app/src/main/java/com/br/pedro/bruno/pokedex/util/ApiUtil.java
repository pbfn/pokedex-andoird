package com.br.pedro.bruno.pokedex.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class ApiUtil {

    public static String getDados(String url){

        String res = "";

        try {
            URL api = new URL(url);

            int codigoResposta;
            HttpURLConnection connection;
            InputStream inputStream;

            connection = (HttpURLConnection) api.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(15000);
            connection.setConnectTimeout(15000);
            connection.connect();

            codigoResposta = connection.getResponseCode();

            if(codigoResposta<HttpURLConnection.HTTP_BAD_REQUEST){
                inputStream = connection.getInputStream();
            }else{
                inputStream = connection.getErrorStream();
            }

            res = converterInputStreamToString(inputStream);
            inputStream.close();
            connection.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException exception) {
            exception.printStackTrace();
        }


        return res;
    }


    private static String converterInputStreamToString(InputStream inputStream){

        StringBuffer buffer = new StringBuffer();

        try {
            BufferedReader br;
            String linha;
            br = new BufferedReader(new InputStreamReader(inputStream));
            while ((linha = br.readLine())!= null){
                buffer.append(linha);
            }
            br.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        return buffer.toString();

    }

}
