package webrtc;

public enum RtcState {
    offer_condidate(1),
    offer(2),
    answer(3),
    ;


    public final int state;

    RtcState(Integer state) {
        this.state = state;
    }

    public Integer getState() {
        return state;
    }


}
