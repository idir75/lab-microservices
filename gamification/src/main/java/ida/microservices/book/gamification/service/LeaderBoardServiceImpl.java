package ida.microservices.book.gamification.service;

import ida.microservices.book.gamification.domain.LeaderBoardRow;
import ida.microservices.book.gamification.repository.ScoreCardRepository;

import java.util.List;

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
