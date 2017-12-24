package ida.microservices.book.multiplication.service;

public interface RandomGeneratorService {

    /**
     *
     * @return a randomly-generated factor. It's always a number between 11and 99.
     */
    int generateRandomFactor();
}
