package hello.hellospring.domain;

public class Resource {
    private int id;
    private String group;
    private String code;
    private boolean value;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Resource{" +
                "id=" + id +
                ", group='" + group + '\'' +
                ", code='" + code + '\'' +
                ", value=" + value +
                '}';
    }
}
