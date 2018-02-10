package ida.microservices.book.multiplication.controller;

import ida.microservices.book.multiplication.domain.MultiplicationResultAttempt;
import ida.microservices.book.multiplication.service.MultiplicationService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/results")
final class MultiplicationResultAttemptController {

    private  final MultiplicationService multiplicationService;

    @Autowired
    MultiplicationResultAttemptController(final MultiplicationService multiplicationService) {
        this.multiplicationService = multiplicationService;
    }

    @RequiredArgsConstructor
    @NoArgsConstructor(force = true)
    @Getter
    static final class ResultResponse {
        private final boolean correct;
    }

    @PostMapping
    ResponseEntity<MultiplicationResultAttempt> postResult(@RequestBody MultiplicationResultAttempt multiplicationResultAttempt) {
        boolean isCorrect = multiplicationService.checkAttempt(multiplicationResultAttempt);
        MultiplicationResultAttempt attemptCopy = new MultiplicationResultAttempt(
                multiplicationResultAttempt.getUser(),
                multiplicationResultAttempt.getMultiplication(),
                multiplicationResultAttempt.getResultAttempt(),
                isCorrect);
        return ResponseEntity.ok(attemptCopy);
    }

    @GetMapping
    ResponseEntity<List<MultiplicationResultAttempt>> getStatistics(@RequestParam("alias") String alias) {
        return ResponseEntity.ok(multiplicationService.getStatsForUser(alias));
    }

    @GetMapping("/{resultId}")
    ResponseEntity<MultiplicationResultAttempt> getResultById(final @PathVariable("resultId") Long resultId) {
        return ResponseEntity.ok(multiplicationService.getResultById(resultId));
    }
}
