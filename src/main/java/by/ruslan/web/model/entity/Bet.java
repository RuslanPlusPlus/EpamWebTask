package by.ruslan.web.model.entity;

import java.math.BigDecimal;
import java.sql.Date;

public class Bet {
    public static enum BetType{
        DRAW("DRAW"), WIN("WIN"), /*LOSS("LOSS"),*/ EXACT_SCORE("EXACT_SCORE");

        private String value;

        private BetType(String value){
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    private long betId;
    private BetType type;
    private BigDecimal money;
    private BigDecimal winMoney;
    private String userEmail;
    private long eventId; //one to many
    private long userId; //one to many
    private long member1Id;
    private long member2Id;
    private int member1Score;
    private int member2Score;

    public Bet() {
    }

    public long getBetId() {
        return betId;
    }

    public void setBetId(long betId) {
        this.betId = betId;
    }

    public BetType getType() {
        return type;
    }

    public void setType(BetType type) {
        this.type = type;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public BigDecimal getWinMoney() {
        return winMoney;
    }

    public void setWinMoney(BigDecimal winMoney) {
        this.winMoney = winMoney;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public long getEventId() {
        return eventId;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getMember1Id() {
        return member1Id;
    }

    public void setMember1Id(long member1Id) {
        this.member1Id = member1Id;
    }

    public long getMember2Id() {
        return member2Id;
    }

    public void setMember2Id(long member2Id) {
        this.member2Id = member2Id;
    }

    public int getMember1Score() {
        return member1Score;
    }

    public void setMember1Score(int member1Score) {
        this.member1Score = member1Score;
    }

    public int getMember2Score() {
        return member2Score;
    }

    public void setMember2Score(int member2Score) {
        this.member2Score = member2Score;
    }

    @Override
    public String toString() {
        return "Bet{" +
                "betId=" + betId +
                ", type=" + type +
                ", money=" + money +
                ", winMoney=" + winMoney +
                ", userEmail='" + userEmail + '\'' +
                ", eventId=" + eventId +
                ", userId=" + userId +
                ", member1Id=" + member1Id +
                ", member2Id=" + member2Id +
                ", member1Score=" + member1Score +
                ", member2Score=" + member2Score +
                '}';
    }
}
