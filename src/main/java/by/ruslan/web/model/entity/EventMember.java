package by.ruslan.web.model.entity;

/**
 * The {@code EventMember} class describes the EventMember entity
 *
 * @author Ruslan Nedvedskiy
 */
public class EventMember {
    private long memberId;
    private String memberName;
    private long kindId;
    private String kindName;

    public EventMember(){
    }

    public long getMemberId() {
        return memberId;
    }

    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public long getKindId() {
        return kindId;
    }

    public void setKindId(long kindId) {
        this.kindId = kindId;
    }

    public String getKindName() {
        return kindName;
    }

    public void setKindName(String kindName) {
        this.kindName = kindName;
    }

    @Override
    public String toString() {
        return "EventMember{" +
                "memberId=" + memberId +
                ", memberName='" + memberName + '\'' +
                ", kindId=" + kindId +
                '}';
    }
}
