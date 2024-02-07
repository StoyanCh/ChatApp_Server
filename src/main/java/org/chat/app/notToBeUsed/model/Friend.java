package org.chat.app.notToBeUsed.model;

import javax.persistence.*;
@Entity
public class Friend {
    @Id
    private long friendId;
    private long userId;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public long getFriendId() {
        return friendId;
    }

    public void setFriendId(long friendId) {
        this.friendId = friendId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
