package kr.corearoad.bean;

import org.springframework.stereotype.Component;

/**
 * Created by Naver on 2016-06-19.
 */
@Component
public class ChatMessanger {

    private String messengerId;
    private String messengerRoomId;
    private String messengerSendTime;
    private String messengerReadCount;
    private String messengerSendUserId;

    public String getMessengerId() {
        return messengerId;
    }

    public void setMessengerId(String messengerId) {
        this.messengerId = messengerId;
    }

    public String getMessengerRoomId() {
        return messengerRoomId;
    }

    public void setMessengerRoomId(String messengerRoomId) {
        this.messengerRoomId = messengerRoomId;
    }

    public String getMessengerSendTime() {
        return messengerSendTime;
    }

    public void setMessengerSendTime(String messengerSendTime) {
        this.messengerSendTime = messengerSendTime;
    }

    public String getMessangerReadCount() {
        return messengerReadCount;
    }

    public void setMessangerReadCount(String messangerReadCount) {
        this.messengerReadCount = messangerReadCount;
    }

    public String getMessengerSendUserId() {
        return messengerSendUserId;
    }

    public void setMessengerSendUserId(String messengerSendUserId) {
        this.messengerSendUserId = messengerSendUserId;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ChatMessanger{");
        sb.append("messengerId='").append(messengerId).append('\'');
        sb.append(", messengerRoomId='").append(messengerRoomId).append('\'');
        sb.append(", messengerSendTime='").append(messengerSendTime).append('\'');
        sb.append(", messangerReadCount='").append(messengerReadCount).append('\'');
        sb.append(", messengerSendUserId='").append(messengerSendUserId).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
