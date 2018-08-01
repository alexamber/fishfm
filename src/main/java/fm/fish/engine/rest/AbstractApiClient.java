package fm.fish.engine.rest;

import retrofit2.Call;
import retrofit2.HttpException;
import retrofit2.Response;

import java.io.IOException;

public class AbstractApiClient {

    protected static <T> Response<T> send(Call<T> call, int expectedCode) {
        Response<T> response;
        try {
            response = call.execute();
        } catch (IOException e) {
            throw new RuntimeException(String.format("Failed to send request: %s", getCallInfo(call)), e);
        }
        if (expectedCode != response.code()) {
            throw new HttpException(response);
        }
        return response;
    }

    private static <T> String getCallInfo(Call<T> call) {
        return call.request().method() + " " + call.request().url();
    }

}
