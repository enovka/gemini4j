package com.enovka.gemini4j.resource.spec.base;

import com.enovka.gemini4j.resource.exception.ResourceException;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Consumer;

/**
 * Represents an asynchronous response from a Gemini API resource operation. Provides a convenient and
 * type-safe way to handle results and exceptions arising from asynchronous API calls.  Extends
 * {@link CompletableFuture} and offers specialized methods for handling success, errors, and
 * cancellations, promoting a functional programming style and enhancing code readability.
 *
 * @param <T> The type of the response object.
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.2.0
 */
public class AsyncResponse<T> extends CompletableFuture<T> {

    private AsyncResponse() {}

    AsyncResponse(CompletableFuture<T> future) {
        future.whenComplete(this::complete);
    }

    private void complete(T result, Throwable throwable) {
        if (throwable != null) {
            this.completeExceptionally(throwable);
        } else {
            this.complete(result);
        }
    }

    /**
     * Creates a new AsyncResponse instance that is already completed exceptionally with the given
     * Throwable.  Useful for immediately representing a failed asynchronous operation.
     *
     * @param throwable The exception that caused the failure.  Should not be null.
     * @param <U>       The type of the response object (used for type inference).
     * @return A new AsyncResponse instance in an exceptionally completed state.
     * @since 0.2.0
     */
    public static <U> AsyncResponse<U> fromException(Throwable throwable) {
        AsyncResponse<U> asyncResponse = new AsyncResponse<>();
        asyncResponse.completeExceptionally(throwable);
        return asyncResponse;
    }

    /**
     * Creates a new AsyncResponse instance from an existing CompletableFuture.  This allows you to
     * wrap an existing asynchronous operation in an AsyncResponse for easier handling.
     *
     * @param future The CompletableFuture to wrap.  Should not be null.
     * @param <U>    The type of the response object (used for type inference).
     * @return A new AsyncResponse instance that mirrors the state of the provided CompletableFuture.
     * @since 0.2.0
     */
    public static <U> AsyncResponse<U> fromFuture(CompletableFuture<U> future) {
        return new AsyncResponse<>(future);
    }

    /**
     * Attaches a success handler to this asynchronous operation. The provided consumer will be
     * executed if the operation completes successfully, receiving the result as its argument.
     * This method provides a functional approach to handling successful asynchronous responses.
     *
     * @param successHandler The consumer to execute on successful completion.  Should not be null.
     * @return This AsyncResponse instance to allow method chaining.
     */
    public AsyncResponse<T> onSuccess(Consumer<T> successHandler) {
        this.thenAccept(successHandler);
        return this;
    }

    /**
     * Attaches an error handler to this asynchronous operation. The provided consumer will be
     * executed if the operation completes exceptionally, receiving the thrown exception as its
     * argument.  This allows for specialized exception handling within the context of asynchronous
     * Gemini API calls.
     *
     * @param errorHandler The consumer to execute on exceptional completion.  Should not be null.
     * @return This AsyncResponse instance to allow method chaining.
     */
    public AsyncResponse<T> onError(Consumer<ResourceException> errorHandler) {
        this.exceptionally(throwable -> {
            ResourceException exception = (throwable instanceof ResourceException) ?
                    (ResourceException) throwable :
                    new ResourceException(throwable);
            errorHandler.accept(exception);
            return null;
        });
        return this;
    }

    /**
     * Attaches a cancellation handler to this asynchronous operation. The provided Runnable will be
     * executed if the operation is cancelled. This allows for cleanup actions in response to
     * cancellations.
     *
     * @param cancellationHandler The Runnable to execute if the operation is cancelled. Should not be null.
     * @return This AsyncResponse instance to allow method chaining.
     */
    public AsyncResponse<T> onCanceled(Runnable cancellationHandler) {
        this.whenComplete((result, throwable) -> {
            if (isCancelled()) {
                cancellationHandler.run();
            }
        });
        return this;
    }

    /**
     * Retrieves the result of the asynchronous operation, waiting if necessary for completion
     * within the specified timeout. Handles potential exceptions during the wait, throwing a
     * ResourceException wrapping the underlying cause.
     *
     * @param timeout The maximum time to wait for the result. Must be non-negative.
     * @param unit    The time unit for the timeout. Should not be null.
     * @return The result of the asynchronous operation.
     * @throws ResourceException If an error occurs during the wait, including interruption, timeout,
     *                           or execution exception.
     * @throws ResourceException If the operation is completed exceptionally with a ResourceException.
     */
    protected T getResponse(long timeout, TimeUnit unit) throws ResourceException {
        try {
            return this.get(timeout, unit);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new ResourceException("Thread interrupted while waiting for response.", e);
        } catch (ExecutionException e) {
            Throwable cause = e.getCause();
            throw new ResourceException(cause != null ? cause.getMessage() : e.getMessage(), cause);
        } catch (TimeoutException e) {
            throw new ResourceException("Request timed out.", e);
        }
    }
}