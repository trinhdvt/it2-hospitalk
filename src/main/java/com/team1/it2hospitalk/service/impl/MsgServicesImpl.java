package com.team1.it2hospitalk.service.impl;

import com.team1.it2hospitalk.exception.NotFoundError;
import com.team1.it2hospitalk.exception.UnauthorizedError;
import com.team1.it2hospitalk.model.entity.Channel;
import com.team1.it2hospitalk.model.entity.Message;
import com.team1.it2hospitalk.model.entity.User;
import com.team1.it2hospitalk.model.response.ChannelDTO;
import com.team1.it2hospitalk.model.response.MessageDTO;
import com.team1.it2hospitalk.repository.ChannelRepository;
import com.team1.it2hospitalk.repository.MessageRepository;
import com.team1.it2hospitalk.repository.UserRepository;
import com.team1.it2hospitalk.service.IMsgServices;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Transactional
public class MsgServicesImpl implements IMsgServices {

    private final ChannelRepository channelRepo;
    private final UserRepository userRepo;
    private final MessageRepository msgRepo;

    @Override
    public List<MessageDTO> getAllMessagesInRoom(Integer channelId) {
        return msgRepo.getAllByChannel_Id(channelId)
                .stream()
                .map(MessageDTO::from)
                .sorted(Comparator.comparing(MessageDTO::getCreatedAt))
                .collect(Collectors.toList());
    }

    @Override
    public List<ChannelDTO> getAllMyChannel(String username) {
        User user = userRepo.findByUsername(username);

        return user.getChannels().stream()
                .map(ChannelDTO::toChannelDTO)
                .distinct()
                .collect(Collectors.toList());

    }

    @Override
    public MessageDTO createNewMessage(int roomID, String username, String message) {
        Channel channel = channelRepo.findById(roomID).orElseThrow(() -> new NotFoundError("Channel not found"));
        User user = userRepo.findByUsername(username);

        //
        if (channel.getUsers().stream().anyMatch(u -> u.getUsername().equals(username))) {
            Message msg = Message.builder()
                    .creator(user)
                    .channel(channel)
                    .body(message)
                    .build();

            msg = msgRepo.saveAndFlush(msg);
            return MessageDTO.from(msg);
        }

        throw new UnauthorizedError("You are not authorized to send message in this channel");
    }
}