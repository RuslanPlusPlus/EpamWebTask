package by.ruslan.web.model.entity;

public class EventResult {
    private long event_id; //one to one
    private long winner_id;
    private long loser_id;
    private int winner_score;
    private int loser_score;

    public EventResult() {
    }

    public long getEvent_id() {
        return event_id;
    }

    public void setEvent_id(long event_id) {
        this.event_id = event_id;
    }

    public long getWinner_id() {
        return winner_id;
    }

    public void setWinner_id(long winner_id) {
        this.winner_id = winner_id;
    }

    public long getLoser_id() {
        return loser_id;
    }

    public void setLoser_id(long loser_id) {
        this.loser_id = loser_id;
    }

    public int getWinner_score() {
        return winner_score;
    }

    public void setWinner_score(int winner_score) {
        this.winner_score = winner_score;
    }

    public int getLoser_score() {
        return loser_score;
    }

    public void setLoser_score(int loser_score) {
        this.loser_score = loser_score;
    }
}
