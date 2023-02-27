package com.github.aranciro.client;

import com.github.aranciro.client.exception.ClientErrorException;
import com.github.aranciro.client.exception.InvalidServerResponseException;
import com.github.aranciro.client.exception.ServerErrorException;
import com.github.aranciro.client.pojo.model.PromptResponse;
import com.github.aranciro.client.pojo.rest.error.ClientError;
import com.github.aranciro.client.pojo.rest.error.ServerError;
import com.github.aranciro.client.pojo.rest.response.ConversationResponse;
import com.squareup.moshi.Moshi;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Objects;
import java.util.Set;

@Slf4j
public final class ClientUtils {
    
    private ClientUtils() {
        throw new IllegalStateException("Cannot instantiate utils class");
    }
    
    @NonNull
    public static Moshi getMoshi() {
        return new Moshi.Builder().build();
    }
    
    @NonNull
    public static PromptResponse getPromptResponse(
            @NonNull final APIServerClient apiServerClient,
            final int code,
            final String bodyString) throws InvalidServerResponseException {
        try {
            ConversationResponse convResponse = getMoshi().adapter(ConversationResponse.class)
                    .fromJson(bodyString);
            Set<ConstraintViolation<ConversationResponse>> violations = validateObject(convResponse);
            if(!violations.isEmpty()) {
                violations.forEach(v -> log.warn("Response body constraint violation: {}",v.toString()));
                String exMsg = String.format("Invalid server response (code: %d)", code);
                throw new InvalidServerResponseException(exMsg);
            }
            apiServerClient.updateConversation(convResponse);
            // TODO parse violatesPolicy correctly (if present)
            return new PromptResponse(Objects.requireNonNull(convResponse).getResponse(), false);
        } catch (InvalidServerResponseException e) {
            throw e;
        } catch (Exception e) {
            String exMsg = String.format("Invalid server response (code: %d)", code);
            throw new InvalidServerResponseException(exMsg);
        }
    }
    
    /*@NonNull
    public static <T> void handleErrorResponse(int code, final String bodyString, @NonNull final Class<T> clazz)
            throws PromptException {
        String pattern = "Client Error (code: %d): %s";
        try {
            T serverError = getMoshi().adapter(T.class).fromJson(bodyString);
            String exMsg = String.format(pattern, code, Objects.requireNonNull(serverError).getError());
            throw new ServerErrorException(exMsg);
        } catch (ServerErrorException e) {
            throw e;
        } catch (Exception e) {
            String exMsg = String.format(pattern, code, "unknown error");
            throw new ServerErrorException(exMsg, e);
        }
    }*/
    
    //TODO merge into a single method with generics
    @NonNull
    public static void handleServerErrorResponse(int code, final String bodyString) throws ServerErrorException {
        String pattern = "Client Error (code: %d): %s";
        try {
            ServerError serverError = getMoshi().adapter(ServerError.class).fromJson(bodyString);
            String exMsg = String.format(pattern, code, Objects.requireNonNull(serverError).getError());
            throw new ServerErrorException(exMsg);
        } catch (ServerErrorException e) {
            throw e;
        } catch (Exception e) {
            String exMsg = String.format(pattern, code, "unknown error");
            throw new ServerErrorException(exMsg, e);
        }
    }
    
    //TODO merge into a single method with generics
    @NonNull
    public static void handleClientErrorResponse(int code, final String bodyString) throws ClientErrorException {
        String pattern = "Client Error (code: %d): %s";
        try {
            ClientError clientError = getMoshi().adapter(ClientError.class).fromJson(bodyString);
            String cause = String.format("%s - %s",
                    Objects.requireNonNull(clientError).getError(),
                    clientError.getMessage());
            String exMsg = String.format(pattern, code, cause);
            throw new ClientErrorException(exMsg);
        } catch (ClientErrorException e) {
            throw e;
        } catch (Exception e) {
            String exMsg = String.format(pattern, code, "unknown error");
            throw new ClientErrorException(exMsg, e);
        }
    }
    
    private static <T> Set<ConstraintViolation<T>> validateObject(final T object) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        return validator.validate(object);
    }
}
