package ida.microservices.book.multiplication.service;

import ida.microservices.book.multiplication.domain.Multiplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MultiplicationServiceTest {

    @Mock
    private RandomGeneratorService randomGeneratorService;

    private MultiplicationServiceImpl multiplicationServiceImpl;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        multiplicationServiceImpl = new MultiplicationServiceImpl(randomGeneratorService);
    }

    @Test
    public void createRandomMultiplicationTest() {

        given(randomGeneratorService.generateRandomFactor()).willReturn(5, 5);
        Multiplication multiplication = multiplicationServiceImpl.createRandomMultiplication();

        assertThat(multiplication.getFactorA()).isEqualTo(5);
        assertThat(multiplication.getFactorB()).isEqualTo(5);
        //TODO assertThat(multiplication.getResult()).isEqualTo(25);
    }
}
