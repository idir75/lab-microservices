package ida.microservices.book.multiplication.repository;

import ida.microservices.book.multiplication.domain.Multiplication;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MultiplicationRepository extends CrudRepository<Multiplication, Long> {

    //Optional<Multiplication> findByFactors(int factorA, int factorB);

}
