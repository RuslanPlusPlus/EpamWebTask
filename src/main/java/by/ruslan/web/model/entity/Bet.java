package by.ruslan.web.model.entity;

import java.math.BigDecimal;
import java.sql.Date;

public class Bet {
    public static enum BetType{
        DRAW("DRAW"), WIN("WIN"), LOSS("LOSS"), EXACT_SCORE("EXACT_SCORE");

        private String value;

        private BetType(String value){
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    private long bet_id;
    private BetType type;
    private BigDecimal money;
    private BigDecimal win_money;
    private Date betDate;
    private String userEmail;
    private long event_id; //one to many
    private long user_id; //one to many
    private long member1_id;
    private long member2_id;
    private int member1_score;
    private long member2_score;

    public Bet() {
    }

    public long getBet_id() {
        return bet_id;
    }

    public void setBet_id(long bet_id) {
        this.bet_id = bet_id;
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

    public Date getBetDate() {
        return betDate;
    }

    public void setBetDate(Date betDate) {
        this.betDate = betDate;
    }

    public BigDecimal getWin_money() {
        return win_money;
    }

    public void setWin_money(BigDecimal win_money) {
        this.win_money = win_money;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public long getEvent_id() {
        return event_id;
    }

    public void setEvent_id(long event_id) {
        this.event_id = event_id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public long getMember1_id() {
        return member1_id;
    }

    public void setMember1_id(long member1_id) {
        this.member1_id = member1_id;
    }

    public long getMember2_id() {
        return member2_id;
    }

    public void setMember2_id(long member2_id) {
        this.member2_id = member2_id;
    }

    public int getMember1_score() {
        return member1_score;
    }

    public void setMember1_score(int member1_score) {
        this.member1_score = member1_score;
    }

    public long getMember2_score() {
        return member2_score;
    }

    public void setMember2_score(long member2_score) {
        this.member2_score = member2_score;
    }
}
