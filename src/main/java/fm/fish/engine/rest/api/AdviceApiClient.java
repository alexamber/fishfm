package fm.fish.engine.rest.api;

import fm.fish.engine.rest.AbstractApiClient;
import fm.fish.pojo.advice.Advice;

import static org.apache.http.HttpStatus.SC_OK;

public class AdviceApiClient extends AbstractApiClient {

    public static Advice getAdvice() {
        return send(AdviceApi.get().getAdvice(), SC_OK).body();
    }
}
