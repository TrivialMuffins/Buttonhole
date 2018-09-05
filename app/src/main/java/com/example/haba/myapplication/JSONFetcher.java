package com.example.haba.myapplication;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class JSONFetcher extends AsyncTask<Void,Void,Void> {
    String data="";
    String data_parsed="";
    String single_parsed="";
    @Override
    protected Void doInBackground(Void... voids){
        try {
            URL url = new URL("https://www.trivialmuffins.com/buttonhole/ButtonholeTasks.php?get_all_tasks=True");
            HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line ="";//"{\"1\":{\"rowid\":1,\"task_workgroup\":\"IT\",\"task_name\":\"Stairs\",\"task_description\":\"Make an app that can stream the performance and edit the footage automatically adding subtitles to it.\",\"task_owner\":\"Tom\",\"task_deadline\":\"28\\/09\\/2018\"},\"2\":{\"rowid\":2,\"task_workgroup\":\"costumes\",\"task_name\":\"Red dress\",\"task_description\":\"Make a nice dress that can fit Tyler, Ivana and Ben.\",\"task_owner\":\"Tem\",\"task_deadline\":\"01\\/10\\/2018\"},\"3\":{\"rowid\":3,\"task_workgroup\":\"top\",\"task_name\":\"Avoid bankruptcy\",\"task_description\":\"Get 250.000eur in sponsoring\",\"task_owner\":\"Hadrien\",\"task_deadline\":\"01\\/10\\/2018\"},\"4\":{\"rowid\":4,\"task_workgroup\":\"finances\",\"task_name\":\"Cover the trails\",\"task_description\":\"Cover up maffia connections of Hadrien to get the sponsorship.\",\"task_owner\":\"Inge\",\"task_deadline\":\"05\\/09\\/2018\"}}" ;
            while(line != null){
                line= bufferedReader.readLine();
                data = data + line;
            }
            Log.d("JSONFILE", data);
            JSONObject JO = new JSONObject(data);
            String json_task_name;
            String json_task_owner;
            String json_task_description;
            String json_task_deadline;
            for(int i = 0; i<JO.length();i++){
                JSONObject JO2 = (JSONObject) JO.get(Integer.toString(i+1));
                json_task_name = (String) JO2.get("task_name");
                json_task_owner = (String) JO2.get("task_owner");
                json_task_description = (String) JO2.get("task_description");
                json_task_deadline = (String) JO2.get("task_deadline");
                single_parsed=json_task_owner+ "\n";
                single_parsed= single_parsed+"Task: " + json_task_name+"\n";
                single_parsed=single_parsed+"Description : " + json_task_description+"\n";
                single_parsed=single_parsed+"Deadline : "+ json_task_deadline+"\n\n";
                data_parsed+=single_parsed;
            }
        }
        catch(MalformedURLException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        DisplayTasks.task_textView.setText(this.data_parsed);
    }
}
