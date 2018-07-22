package fm.fish.pojo.openweather.forecast;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;
import java.util.List;

@Generated("com.robohorse.robopojogenerator")
public class WeatherForecast {

    @SerializedName("city")
    private City city;

    @SerializedName("cnt")
    private int cnt;

    @SerializedName("cod")
    private String cod;

    @SerializedName("message")
    private double message;

    @SerializedName("list")
    private List<ListItem> list;

    public City getCity() {
        return city;
    }

    public int getCnt() {
        return cnt;
    }

    public String getCod() {
        return cod;
    }

    public double getMessage() {
        return message;
    }

    public List<ListItem> getList() {
        return list;
    }

    @Override
    public String toString() {
        return
                "WeatherForecast{" +
                        "city = '" + city + '\'' +
                        ",cnt = '" + cnt + '\'' +
                        ",cod = '" + cod + '\'' +
                        ",message = '" + message + '\'' +
                        ",list = '" + list + '\'' +
                        "}";
    }
}