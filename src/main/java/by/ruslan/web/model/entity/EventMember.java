package by.ruslan.web.model.entity;

public class EventMember {
    private long member_id;
    private String eventName;

    //Many to one, JoinColumn(name = "kind_id", referencedColumnName = "event_id")
    private long kind_id;

    public EventMember(){
    }

    public long getMember_id() {
        return member_id;
    }

    public void setMember_id(long member_id) {
        this.member_id = member_id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public long getKind_id() {
        return kind_id;
    }

    public void setKind_id(long kind_id) {
        this.kind_id = kind_id;
    }
}
