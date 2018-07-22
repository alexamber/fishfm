package fm.fish.engine.rest;

import retrofit2.Call;
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
        verifyResponseCode(call, response, expectedCode);
        return response;
    }

    private static <T> String getCallInfo(Call<T> call) {
        return call.request().method() + " " + call.request().url();
    }

    private static void verifyResponseCode(Call call, Response response, int expectedCode) {
        if (expectedCode != response.code()) {
            throw new RuntimeException(String.format("Request: %s%nExpected: %d. Got: %d.%n%s",
                    getCallInfo(call), expectedCode, response.code(), response));
        }
    }
}
