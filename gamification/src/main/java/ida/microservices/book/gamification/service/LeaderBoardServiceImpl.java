package ida.microservices.book.gamification.service;

import ida.microservices.book.gamification.domain.LeaderBoardRow;
import ida.microservices.book.gamification.repository.ScoreCardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class LeaderBoardServiceImpl implements LeaderBoardService {

    private ScoreCardRepository scoreCardRepository;

    LeaderBoardServiceImpl(ScoreCardRepository scoreCardRepository) {
        this.scoreCardRepository = scoreCardRepository;
    }

    @Override
    public List<LeaderBoardRow> getCurrentLeaderBoard() {
        return scoreCardRepository.findFirst10();
    }
}
