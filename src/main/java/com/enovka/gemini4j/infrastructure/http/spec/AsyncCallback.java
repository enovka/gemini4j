package com.enovka.gemini4j.infrastructure.http.spec;

/**
 * Callback interface for handling asynchronous HTTP responses.  Provides methods for handling
 * successful completion, errors, and cancellations. This interface allows clients to define
 * custom logic for processing asynchronous results without blocking the main thread.
 *
 * @param <T> The type of the response object.
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.2.0
 */
public interface AsyncCallback<T> {

    /**
     * Called when the asynchronous operation completes successfully. This method will be invoked
     * on the completion thread and should contain the logic for processing the successful result.
     *
     * @param result The result of the asynchronous operation.
     * @since 0.2.0
     */
    void onSuccess(T result);

    /**
     * Called when the asynchronous operation fails with an exception. This method will be invoked
     * on the completion thread and should handle the exception appropriately, such as logging the
     * error or displaying an error message to the user.
     *
     * @param exception The exception thrown during the asynchronous operation.
     * @since 0.2.0
     */
    void onError(Throwable exception);

    /**
     * Called when the asynchronous operation is cancelled. This method will be invoked on the
     * completion thread if the `CompletableFuture` representing the asynchronous operation is
     * cancelled.  Implementations should handle any necessary cleanup or resource release in this
     * method.  Note that this method might not be called if the cancellation occurs before the
     * asynchronous operation starts.
     *
     * @since 0.2.0
     */
    default void onCanceled() {}
}