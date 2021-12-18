package com.team1.it2hospitalk.service;

import com.team1.it2hospitalk.model.request.TransferFormPayload;

public interface ITransferFormService {

    com.team1.it2hospitalk.model.response.TransferFormDTO createTransferForm(String username, TransferFormPayload formDTO);

}