package com.team1.it2hospitalk.service.impl;

import com.team1.it2hospitalk.model.response.ChannelDTO;
import com.team1.it2hospitalk.repository.ChannelRepository;
import com.team1.it2hospitalk.service.IChannelServices;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ChannelServicesImpl implements IChannelServices {

    private final ChannelRepository channelRepo;

    @Override
    public List<ChannelDTO> getAllChannels(Pageable pageable) {
        return channelRepo.findAll(pageable)
                .map(ChannelDTO::toChannelDTO)
                .getContent();
    }

    @Override
    public List<ChannelDTO> getAllChannelCreatedBy(String username, Pageable pageable) {
        return channelRepo.getChannelByCreator_Username(username, pageable)
                .map(ChannelDTO::toChannelDTO)
                .getContent();
    }
}
