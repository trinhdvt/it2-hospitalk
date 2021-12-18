package com.team1.it2hospitalk.service;

import com.team1.it2hospitalk.model.request.TransferFormPayload;
import com.team1.it2hospitalk.model.response.TransferFormDTO;

import java.util.List;

public interface ITransferFormService {

    com.team1.it2hospitalk.model.response.TransferFormDTO createTransferForm(String username, TransferFormPayload formDTO);

    List<TransferFormDTO> getCreatedTransferFromByUser(String username);

    List<TransferFormDTO> getReceivedTransferFromByUser(String username);
}