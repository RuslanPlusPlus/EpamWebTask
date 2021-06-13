package by.ruslan.web.model.entity;

import java.util.List;

public class SportKind {
    private long kind_id;
    private String kindName;
    private List<EventMember> members; //one to many

    public SportKind(){
    }

    public long getKind_id() {
        return kind_id;
    }

    public void setKind_id(long kind_id) {
        this.kind_id = kind_id;
    }

    public String getKindName() {
        return kindName;
    }

    public void setKindName(String kindName) {
        this.kindName = kindName;
    }

    public List<EventMember> getMembers() {
        return members;
    }

    public void setMembers(List<EventMember> members) {
        this.members = members;
    }
}
