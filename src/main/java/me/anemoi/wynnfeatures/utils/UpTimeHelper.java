/*
 * Thanks to https://github.com/AviciaGuild for avomod which this is based on.
 * https://github.com/AviciaGuild/avomod/blob/main/LICENSE.txt
 * changed and ported to 1.12.2 by Anemoi/ForBai
 */
package me.anemoi.wynnfeatures.utils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class UpTimeHelper {
    private final java.util.Comparator<Map.Entry<String, JsonElement>> mapComparator =
            Comparator.comparingInt(m -> m.getValue().getAsJsonObject().get("age").getAsInt());
    private JsonObject upTimeData = null;

    public UpTimeHelper() {
        try {
            this.upTimeData = new Gson().fromJson(getData("https://www.avicia.cf/api/up"), JsonObject.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Map.Entry<String, JsonElement>> getWorldUpTimeData() {
        if (upTimeData == null) {
            return null;
        }
        ArrayList<Map.Entry<String, JsonElement>> worldUpTimes = new ArrayList<>(upTimeData.entrySet());
        worldUpTimes.sort(mapComparator);
        return worldUpTimes;
    }

    public String getNewestWorld() {
        ArrayList<Map.Entry<String, JsonElement>> worldUpTimeData = getWorldUpTimeData();
        if (worldUpTimeData == null) {
            return null;
        }
        if (worldUpTimeData.size() > 0) {
            return worldUpTimeData.get(0).getKey();
        }
        return null;
    }

    public boolean isUp(String world) {
        String formattedWorld = getFormattedWorld(world);
        return upTimeData != null && upTimeData.has(formattedWorld);
    }

    /**
     * @param world which world to get the age of
     * @return age in minutes
     */
    public int getAge(String world) {
        String formattedWorld = getFormattedWorld(world);
        if (isUp(formattedWorld)) {
            JsonObject worldData = upTimeData.getAsJsonObject(formattedWorld);
            Instant worldStartInstant = Instant.parse(worldData.get("startTime").getAsString());
            return (int) (((System.currentTimeMillis() - worldStartInstant.toEpochMilli()) / 60000));
        }
        return 0;
    }

    public static String getData(String url) {
        OkHttpClient client = new OkHttpClient().newBuilder().connectTimeout(5, TimeUnit.SECONDS).build();
        Request request = new Request.Builder()
                .url(url)
                .method("GET", null)
                .addHeader("Accept", "application/json")
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (response.body() == null) {
                return null;
            }
            return response.body().string();
        } catch (Exception e) {
            return null;
        }
    }

    public static String getFormattedWorld(String world) {
        String formattedWorld = world;
        if (world.matches("^\\d+")) {
            formattedWorld = "WC" + world;
        }
        return formattedWorld.toUpperCase(Locale.ROOT);
    }
}
