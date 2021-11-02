package com.team1.it2hospitalk.service;

import com.team1.it2hospitalk.model.response.ChannelDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IChannelServices {

    List<ChannelDTO> getAllChannels(Pageable pageable);

    List<ChannelDTO> getAllChannels(int hospitalId, Pageable pageable);

    List<ChannelDTO> getAllChannelCreatedBy(String username, Pageable pageable);
}
