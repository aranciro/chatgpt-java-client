package com.github.aranciro.client.callback;

import com.github.aranciro.client.APIServerClient;
import com.github.aranciro.client.ClientUtils;
import com.github.aranciro.client.exception.InvalidServerResponseException;
import com.github.aranciro.client.exception.ServerCallException;
import com.github.aranciro.client.pojo.model.PromptResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public abstract class AbstractConversationCallback implements Callback {
    
    @NonNull
    private final APIServerClient client;
    
    @Override
    public void onFailure(@NotNull final Call call, @NotNull final IOException e) {
        this.onOtherException(e);
    }
    
    @Override
    public void onResponse(@NotNull final Call call, @NotNull final Response response) {
        try {
            int code = response.code();
            String bodyString = this.getResponseBody(response).string();
            log.debug("RESPONSE BODY: {}", bodyString);
            if (!response.isSuccessful() && (code >= 400 && code < 500)) {
                ClientUtils.handleClientErrorResponse(code, bodyString);
            } else if (!response.isSuccessful() && (code >= 500 && code < 600)) {
                ClientUtils.handleServerErrorResponse(code, bodyString);
            } else {
                @NonNull PromptResponse resp = ClientUtils.getPromptResponse(this.client, code, bodyString);
                this.onPromptResponse(resp);
            }
        } catch (InvalidServerResponseException e) {
            this.onInvalidServerResponseException(e);
        } catch (Exception e) {
            this.onOtherException(e);
        } finally {
            response.close();
        }
    }
    
    private void onOtherException(@NonNull final Exception e) {
        this.onServerCallException(new ServerCallException("Error during call to server API", e));
    }
    
    public abstract void onServerCallException(@NonNull final ServerCallException e);
    
    public abstract void onInvalidServerResponseException(@NonNull final InvalidServerResponseException e);
    
    public abstract void onPromptResponse(@NonNull final PromptResponse promptResponse);
    
    /**
     * Convenience method to deceive the compiler: this wraps response.body() because it is marked as nullable, but
     * documentation says that it's never null when the {@link Response} is passed to the
     * {@link Callback#onResponse(Call, Response)} method.
     *
     * @param response The {@link Response} from which to extract the {@link ResponseBody}.
     * @return a non-null {@link ResponseBody} instance.
     */
    @NonNull
    private ResponseBody getResponseBody(@NonNull final Response response) {
        return response.body();
    }
}
