package ida.microservices.book.multiplication.service;

import ida.microservices.book.multiplication.domain.Multiplication;
import ida.microservices.book.multiplication.domain.MultiplicationResultAttempt;
import ida.microservices.book.multiplication.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.booleanThat;

public class MultiplicationServiceImplTest {

    private MultiplicationServiceImpl multiplicationServiceImpl;

    @Mock
    private RandomGeneratorService randomGeneratorService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        multiplicationServiceImpl = new MultiplicationServiceImpl (randomGeneratorService);
    }

    @Test
    public  void createRandomMultiplicationTest(){
        given(randomGeneratorService.generateRandomFactor()).willReturn(50,60);
        Multiplication multiplication = multiplicationServiceImpl.createRandomMultiplication();
        assertThat(multiplication.getFactorA()).isEqualTo(50);
        assertThat(multiplication.getFactorB()).isEqualTo(60);
    }

    @Test
    public void checkCorrectAttemptTest() {
        Multiplication multiplication = new Multiplication(50, 60);
        User user = new User("ida");
        MultiplicationResultAttempt resultAttempt = new MultiplicationResultAttempt(user, multiplication, 3000);

        boolean attemptResult = multiplicationServiceImpl.checkAttempt(resultAttempt);
        assertThat(attemptResult).isTrue();
    }

    @Test
    public  void checkWrongAttemptTest() {
        Multiplication multiplication = new Multiplication(50, 60);
        User user = new User("ida");
        MultiplicationResultAttempt resultAttempt = new MultiplicationResultAttempt(user, multiplication,2222);

        boolean attemptResult = multiplicationServiceImpl.checkAttempt(resultAttempt);
        assertThat(attemptResult).isFalse();
    }
}
