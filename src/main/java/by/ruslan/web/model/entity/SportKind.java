package by.ruslan.web.model.entity;

public class SportKind {
    private long kindId;
    private String kindName;

    public SportKind(){
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
        return "SportKind{" +
                "kind_id=" + kindId +
                ", kindName='" + kindName +
                '}';
    }
}
