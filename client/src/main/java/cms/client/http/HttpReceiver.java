package cms.client.http;

import cms.client.http.requests.HttpRequest;

import java.io.*;
import java.net.HttpURLConnection;

import static java.lang.Thread.sleep;

class HttpReceiver {




        public static void receive (HttpRequest request) throws IOException {
            int responseCode = request.getCon().getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        request.getCon().getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                System.out.println(response.toString());
            } else {
                System.out.println("request not worked");
            }

        }



    }

