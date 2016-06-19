package kr.corearoad.bean;

import org.springframework.stereotype.Component;

/**
 * Created by Naver on 2016-06-19.
 */
@Component
public class ChatRoom {
    private String roomId;
    private String roomName;
    private int roomUserCount;
    private String roomUserList;
    private String roomCreateDateTime;

    public ChatRoom() {
    }

    public ChatRoom(String roomId, String roomName, int roomUserCount, String roomUserList, String roomCreateDateTime) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.roomUserCount = roomUserCount;
        this.roomUserList = roomUserList;
        this.roomCreateDateTime = roomCreateDateTime;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public int getRoomUserCount() {
        return roomUserCount;
    }

    public void setRoomUserCount(int roomUserCount) {
        this.roomUserCount = roomUserCount;
    }

    public String getRoomUserList() {
        return roomUserList;
    }

    public void setRoomUserList(String roomUserList) {
        this.roomUserList = roomUserList;
    }

    public String getRoomCreateDateTime() {
        return roomCreateDateTime;
    }

    public void setRoomCreateDateTime(String roomCreateDateTime) {
        this.roomCreateDateTime = roomCreateDateTime;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ChatRoom{");
        sb.append("roomId='").append(roomId).append('\'');
        sb.append(", roomName='").append(roomName).append('\'');
        sb.append(", roomUserCount=").append(roomUserCount);
        sb.append(", roomUserList='").append(roomUserList).append('\'');
        sb.append(", roomCreateDateTime='").append(roomCreateDateTime).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
