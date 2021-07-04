package by.ruslan.web.model.entity;

/**
 * The {@code EventResult} class describes the EventResult entity
 *
 * @author Ruslan Nedvedskiy
 */
public class EventResult {
    private long eventId;
    private long winnerId;
    private long loserId;
    private int winnerScore;
    private int loserScore;
    private String winnerName;
    private String loserName;

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

    public String getWinnerName() {
        return winnerName;
    }

    public void setWinnerName(String winnerName) {
        this.winnerName = winnerName;
    }

    public String getLoserName() {
        return loserName;
    }

    public void setLoserName(String loserName) {
        this.loserName = loserName;
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
