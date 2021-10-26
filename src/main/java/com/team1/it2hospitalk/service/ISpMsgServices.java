package com.team1.it2hospitalk.service;

import com.team1.it2hospitalk.model.entity.SupportMsg;
import com.team1.it2hospitalk.model.response.SupportMsgDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ISpMsgServices {

    List<SupportMsgDTO> getListOfSpMsg(Pageable pageable);
}
