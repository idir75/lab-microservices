package ida.microservices.book.gamification.client;

import ida.microservices.book.gamification.client.dto.MultiplicationResultAttempt;

public interface MultiplicationResultAttemptClient {

    MultiplicationResultAttempt retrieveMultiplicationResultAttemptById(final Long multiplicationId);
}
