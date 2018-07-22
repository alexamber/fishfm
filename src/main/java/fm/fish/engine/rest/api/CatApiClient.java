package fm.fish.engine.rest.api;

import fm.fish.engine.rest.AbstractApiClient;
import fm.fish.pojo.openweather.catfact.CatFact;

import static org.apache.http.HttpStatus.SC_OK;

public class CatApiClient extends AbstractApiClient {

    public static CatFact getCatFact() {
        return send(CatApi.get().getFact(), SC_OK).body();
    }
}
