package ge.mooserver.services;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Random;
import java.util.StringTokenizer;

public class Farm {
    static final String mainFile = "farm.txt";
    private File farmFile;
    static int currId = 1;
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
        writer.println(id + " " + name+ " 38.5");
        writer.close();
        updateCow(id,System.currentTimeMillis(), 41.688064,44.768989);

    }

    public void updateCow(int id, long date, double x, double y) throws IOException, URISyntaxException {
        PrintWriter writer = new PrintWriter(new FileWriter(Utils.getFileFromResources(id+".txt"), true));
        writer.println(id + " " + date + " "+x +" "+ y);;
        writer.close();
    }

    private static boolean getRandomBoolean() {
        return Math.random() < 0.5;
        //I tried another approaches here, still the same result

    }

    private static double getRandomNum(double rangeMin, double rangeMax){
        Random r = new Random();
        return rangeMin + (rangeMax - rangeMin) * r.nextDouble();
    }

    public JSONArray getCowInfo(int id) throws IOException {
        JSONArray res = new JSONArray();
        ArrayList<JSONObject> arr = new ArrayList<JSONObject>();
        BufferedReader br = new BufferedReader(new FileReader(id+".txt"));

        String line = null;
        double lastLat = 0;
        double lastLon = 0;
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
            arr.add(obj);
            if(arr.size() > 10) {
                arr.remove(0);
            }
            lastLat = Double.parseDouble(lat);
            lastLon = Double.parseDouble(lon);

        }

        for (int i = 0; i <arr.size() ; i++) {
            res.put(arr.get(i));
        }

        System.out.println("curr id: " + currId);
        if(currId == 2) {
            System.out.println("updated");
            try {

                long time = System.currentTimeMillis();
                lastLat += getRandomNum(-0.001, 0.001);
                lastLon += getRandomNum(-0.001, 0.001);
                updateCow(id, time, lastLat,lastLon);
                JSONObject obj = new JSONObject();
                obj.put("time", time + "");
                obj.put("lat", "" +lastLat);
                obj.put("lon", "" + lastLon);
                res.put(obj);
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }

        br.close();
        return res;
    }

    public JSONArray getCows() throws IOException {
        currId++;

        JSONArray res = new JSONArray();
        ArrayList<JSONObject> arr = new ArrayList<JSONObject>();
        String randomName = "123";;
        BufferedReader br = new BufferedReader(new FileReader(farmFile));
        String line = null;
        while ((line = br.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(line);
            String id = st.nextToken();
            String name = st.nextToken();
            String temp = st.nextToken();
            JSONObject obj = new JSONObject();
            obj.put("id", id);
            obj.put("name", name);
            obj.put("path", getCowInfo(Integer.parseInt(id)));
            obj.put("temp", temp);
            arr.add(obj);

            res.put(obj);
        }
        br.close();
        System.out.println(res.toString());
        if(currId  == 4) {
            currId = 0;
        }
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


