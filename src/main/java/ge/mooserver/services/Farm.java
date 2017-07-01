package ge.mooserver.services;

import jdk.nashorn.internal.runtime.JSONListAdapter;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Farm {
    static final String mainFile = "farm.txt";
    private File farmFile;
    private int currId = 1;
    public Farm() {
        try {
            this.farmFile = Utils.getFileFromResources(mainFile);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(!farmFile.exists()) {
            try {
                farmFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void registerCow(String name, int id) throws IOException, URISyntaxException {
        File f =  Utils.getFileFromResources(id+".txt");
        PrintWriter writer = new PrintWriter(new FileWriter(farmFile, true));
        writer.println(id + " " + name);
        writer.close();

    }

    public void updateCow(int id, long date, double x, double y) throws IOException, URISyntaxException {
        PrintWriter writer = new PrintWriter(new FileWriter(Utils.getFileFromResources(id+".txt"), true));
        writer.println(id + " " + date + " "+x +" "+ y);;
        writer.close();
    }

    public JSONArray getCowInfo(int id) throws IOException {
        JSONArray res = new JSONArray();
        BufferedReader br = new BufferedReader(new FileReader(id+".txt"));

        String line = null;
        while ((line = br.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(line);
            st.nextToken();
            String time = st.nextToken();
            String lat = st.nextToken();
            String lon = st.nextToken();
            JSONObject obj = new JSONObject();
            obj.put("time", time);
            obj.put("lat", lat);
            obj.put("lon", lon);
            res.put(obj);
        }
        br.close();
        return res;
    }

    public JSONArray getCows() throws IOException {
        JSONArray res = new JSONArray();
        ArrayList<JSONObject> arr = new ArrayList<JSONObject>();
        String randomName = "123";;
        BufferedReader br = new BufferedReader(new FileReader(farmFile));
        String line = null;
        while ((line = br.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(line);
            String id = st.nextToken();
            String name = st.nextToken();
            JSONObject obj = new JSONObject();
            obj.put("id", id);
            obj.put("name", name);
            obj.put("path", getCowInfo(Integer.parseInt(id)));
            arr.add(obj);

            res.put(obj);
        }
        br.close();
        System.out.println(res.toString());
        return res;
    }

    public void clear() throws IOException, URISyntaxException {

        BufferedReader br = new BufferedReader(new FileReader(farmFile));
        String line = null;
        while ((line = br.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(line);
            String id = st.nextToken();
            String name = st.nextToken();
            File file = new File(id + ".txt");
            file.delete();
        }
        br.close();
        farmFile.delete();
        this.farmFile = Utils.getFileFromResources(mainFile);

    }

}
