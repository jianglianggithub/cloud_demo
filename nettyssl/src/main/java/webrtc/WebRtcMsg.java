package webrtc;


public class WebRtcMsg {
    private Integer state;
    private String uid;
    private String condidate;
    private String desc;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getCondidate() {
        return condidate;
    }

    public void setCondidate(String condidate) {
        this.condidate = condidate;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
