package fm.fish.pojo.openweather.forecast;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class City {

    @SerializedName("country")
    private String country;

    @SerializedName("coord")
    private Coord coord;

    @SerializedName("name")
    private String name;

    @SerializedName("id")
    private int id;

    @SerializedName("population")
    private int population;

    public String getCountry() {
        return country;
    }

    public Coord getCoord() {
        return coord;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getPopulation() {
        return population;
    }

    @Override
    public String toString() {
        return
                "City{" +
                        "country = '" + country + '\'' +
                        ",coord = '" + coord + '\'' +
                        ",name = '" + name + '\'' +
                        ",id = '" + id + '\'' +
                        ",population = '" + population + '\'' +
                        "}";
    }
}