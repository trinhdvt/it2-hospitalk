package com.team1.it2hospitalk.service;

import com.team1.it2hospitalk.model.response.ChannelDTO;
import com.team1.it2hospitalk.model.response.MessageDTO;

import java.util.List;

public interface IMsgServices {

    List<MessageDTO> getAllMessagesInRoom(Integer channelId);

    List<ChannelDTO> getAllMyChannel(String username);

    MessageDTO createNewMessage(int roomID, String username, String message);
}