package ida.microservices.book.multiplication.service;

import ida.microservices.book.multiplication.domain.Multiplication;
import ida.microservices.book.multiplication.domain.MultiplicationResultAttempt;
import ida.microservices.book.multiplication.domain.User;
import ida.microservices.book.multiplication.event.EventDispatcher;
import ida.microservices.book.multiplication.event.MultiplicationSolvedEvent;
import ida.microservices.book.multiplication.repository.MultiplicationResultAttemptRepository;
import ida.microservices.book.multiplication.repository.UserRepository;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.awt.*;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.booleanThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

public class MultiplicationServiceImplTest {

    private MultiplicationServiceImpl multiplicationServiceImpl;

    @Mock
    private RandomGeneratorService randomGeneratorService;

    @Mock
    private MultiplicationResultAttemptRepository attemptRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private EventDispatcher eventDispatcher;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        multiplicationServiceImpl = new MultiplicationServiceImpl(randomGeneratorService, attemptRepository, userRepository, eventDispatcher);
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
        final String userAlias = "ida";
        Multiplication multiplication = new Multiplication(50, 60);

        User user = new User(userAlias);
        MultiplicationResultAttempt resultAttempt = new MultiplicationResultAttempt(user, multiplication, 3000, false);
        MultiplicationResultAttempt verifiedAttempt = new MultiplicationResultAttempt(user, multiplication, 3000, true);

        MultiplicationSolvedEvent event = new MultiplicationSolvedEvent(resultAttempt.getId(), resultAttempt.getUser().getId(), true);

        given(userRepository.findByAlias(userAlias)).willReturn(Optional.empty());

        boolean attemptResult = multiplicationServiceImpl.checkAttempt(resultAttempt);
        assertThat(attemptResult).isTrue();
        verify(attemptRepository).save(verifiedAttempt);
        verify(eventDispatcher).send(eq(event));
    }

    @Test
    public  void checkWrongAttemptTest() {
        final String userAlias = "ida";
        Multiplication multiplication = new Multiplication(50, 60);
        User user = new User(userAlias);
        MultiplicationResultAttempt attempt = new MultiplicationResultAttempt(user, multiplication,3010, false);
        MultiplicationSolvedEvent event = new MultiplicationSolvedEvent(attempt.getId(), attempt.getUser().getId(), false);
        given(userRepository.findByAlias(userAlias)).willReturn(Optional.empty());

        boolean attemptResult = multiplicationServiceImpl.checkAttempt(attempt);
        assertThat(attemptResult).isFalse();
        verify(attemptRepository).save(attempt);
        verify(eventDispatcher).send(eq(event));
    }

    @Test
    public void retrieveStatsTest() {
        final String userAlias = "ida";
        Multiplication multiplication = new Multiplication(50, 60);
        User user = new User(userAlias);
        MultiplicationResultAttempt attempt1 = new MultiplicationResultAttempt(user, multiplication, 3010,false);
        MultiplicationResultAttempt attempt2 = new MultiplicationResultAttempt(user, multiplication, 3051, false);
        List<MultiplicationResultAttempt> latestAttempts = Lists.newArrayList(attempt1, attempt2);

        given(userRepository.findByAlias(userAlias)).willReturn(Optional.empty());

        given(attemptRepository.findTop5ByUserAliasOrderByIdDesc(userAlias)).willReturn(latestAttempts);

        List<MultiplicationResultAttempt> latestAttemptsResult = multiplicationServiceImpl.getStatsForUser(userAlias);

        assertThat(latestAttemptsResult).isEqualTo(latestAttempts);
    }
}
