package by.ruslan.web.model.entity;

public class EventResult {
    private long eventId; //one to one
    private long winnerId;
    private long loserId;
    private int winnerScore;
    private int loserScore;

    public EventResult() {
    }

    public long getEventId() {
        return eventId;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }

    public long getWinnerId() {
        return winnerId;
    }

    public void setWinnerId(long winnerId) {
        this.winnerId = winnerId;
    }

    public long getLoserId() {
        return loserId;
    }

    public void setLoserId(long loserId) {
        this.loserId = loserId;
    }

    public int getWinnerScore() {
        return winnerScore;
    }

    public void setWinnerScore(int winnerScore) {
        this.winnerScore = winnerScore;
    }

    public int getLoserScore() {
        return loserScore;
    }

    public void setLoserScore(int loserScore) {
        this.loserScore = loserScore;
    }

    @Override
    public String toString() {
        return "EventResult{" +
                "eventId=" + eventId +
                ", winnerId=" + winnerId +
                ", loserId=" + loserId +
                ", winnerScore=" + winnerScore +
                ", loserScore=" + loserScore +
                '}';
    }
}
