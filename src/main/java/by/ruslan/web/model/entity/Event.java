package by.ruslan.web.model.entity;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

/**
 * The {@code Event} class describes the Event entity
 *
 * @author Ruslan Nedvedskiy
 */
public class Event {

    /**
     * The {@code EventStatus} enum represents possible event types
     *
     * @author Ruslan Nedvedskiy
     */
    public enum EventStatus{
        ACTIVE("ACTIVE"), COMPLETED("COMPLETED");

        private String value;

        EventStatus(String value){
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    private long eventId;
    private long sportKindId;
    private String eventName;
    private String sportKindName;
    private Date date;
    private Time time;
    private EventStatus status;
    private List<EventMember> members; //one to many
    private EventResult eventResult;
    private boolean readyToBet;
    private boolean readyToAddResult;

    public Event(){
    }

    public long getEventId() {
        return eventId;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }

    public long getSportKindId() {
        return sportKindId;
    }

    public void setSportKindId(long sportKindId) {
        this.sportKindId = sportKindId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public EventStatus getStatus() {
        return status;
    }

    public void setStatus(EventStatus status) {
        this.status = status;
    }

    public List<EventMember> getMembers() {
        return members;
    }

    public void setMembers(List<EventMember> members) {
        this.members = members;
    }

    public EventResult getEventResult() {
        return eventResult;
    }

    public void setEventResult(EventResult eventResult) {
        this.eventResult = eventResult;
    }

    public String getSportKindName() {
        return sportKindName;
    }

    public void setSportKindName(String sportKindName) {
        this.sportKindName = sportKindName;
    }

    public boolean isReadyToBet() {
        return readyToBet;
    }

    public void setReadyToBet(boolean readyToBet) {
        this.readyToBet = readyToBet;
    }

    public boolean isReadyToAddResult() {
        return readyToAddResult;
    }

    public void setReadyToAddResult(boolean readyToAddResult) {
        this.readyToAddResult = readyToAddResult;
    }

    @Override
    public String toString() {
        return "Event{" +
                "eventId=" + eventId +
                ", sportKindId=" + sportKindId +
                ", eventName='" + eventName + '\'' +
                ", date=" + date +
                ", time=" + time +
                ", status=" + status +
                ", members=" + members +
                ", eventResult=" + eventResult +
                ", readyToBet=" + readyToBet +
                ", readyToMakeResult=" + readyToAddResult +
                '}';
    }
}
