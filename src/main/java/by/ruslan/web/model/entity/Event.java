package by.ruslan.web.model.entity;

import java.sql.Date;
import java.util.List;

public class Event {

    public static enum EventStatus{
        ACTIVE("ACTIVE"), COMPLETED("COMPLETED");

        private String value;

        private EventStatus(String value){
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    private long event_id;
    private long sport_kind_id;
    private String eventName;
    private Date date;
    private EventStatus status;
    private List<EventMember> members; //one to many
    private boolean readyToBet;
    private boolean readyToMakeResult;

    public Event(){
    }

    public long getEvent_id() {
        return event_id;
    }

    public void setEvent_id(long event_id) {
        this.event_id = event_id;
    }

    public long getSport_kind_id() {
        return sport_kind_id;
    }

    public void setSport_kind_id(long sport_kind_id) {
        this.sport_kind_id = sport_kind_id;
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

    public boolean isReadyToBet() {
        return readyToBet;
    }

    public void setReadyToBet(boolean readyToBet) {
        this.readyToBet = readyToBet;
    }

    public boolean isReadyToMakeResult() {
        return readyToMakeResult;
    }

    public void setReadyToMakeResult(boolean readyToMakeResult) {
        this.readyToMakeResult = readyToMakeResult;
    }
}