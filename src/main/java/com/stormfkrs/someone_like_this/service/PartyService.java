package com.stormfkrs.someone_like_this.service;

import com.stormfkrs.someone_like_this.entity.Option;
import com.stormfkrs.someone_like_this.entity.Party;
import com.stormfkrs.someone_like_this.entity.Point;
import com.stormfkrs.someone_like_this.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PartyService {


    Party save(Party party);

    Party getById(String id);

    List<Party> getAll();

    List<Party> findAllByUserId(String userId);

    Party addUserToParty(String partyId, User user);

//    Party updateParty(Party party);

    Party addPointsToUser(String partyId, Point point);

    Party updateOptionsToParty(String partyId, Option option);
}
