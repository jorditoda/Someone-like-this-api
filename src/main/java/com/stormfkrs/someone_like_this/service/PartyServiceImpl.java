package com.stormfkrs.someone_like_this.service;

import com.azure.cosmos.models.CosmosPatchOperations;
import com.azure.cosmos.models.PartitionKey;
import com.stormfkrs.someone_like_this.entity.Option;
import com.stormfkrs.someone_like_this.entity.Party;
import com.stormfkrs.someone_like_this.entity.Point;
import com.stormfkrs.someone_like_this.entity.User;
import com.stormfkrs.someone_like_this.repository.PartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import static com.stormfkrs.someone_like_this.constants.Constants.PARTITION_KEY;

@Service
public class PartyServiceImpl implements PartyService {

    @Autowired
    PartyRepository repository;

    @Override
    public Party save(Party party) {
        party.setCreationDate(new Date());
        party.getOptions().forEach(option ->
                option.setId(UUID.randomUUID().toString())
        );
        party.getPoints().forEach(point ->
                point.setId(UUID.randomUUID().toString())
        );
        party.setPartitionKey(PARTITION_KEY);
        Mono<Party> partyMono = repository.save(party);
        return partyMono.block();
    }

    @Override
    public Party getById(String id) {
        Mono<Party> partyMono = repository.findById(id);
        return partyMono.block();
    }

    @Override
    public List<Party> getAll() {
        final Flux<Party> partyFlux = repository.findAll();
        return partyFlux.collectList().block();

    }

    @Override
    public List<Party> findAllByUserId(String userId) {
        final Flux<Party> partyFlux = repository.findAllByJoinedUsersId(userId);
        return partyFlux.collectList().block();
    }

    @Override
    public Party addUserToParty(String partyId, User user) {
        Point point = new Point(UUID.randomUUID().toString(), 0, user, 0);
        CosmosPatchOperations patchOperations = CosmosPatchOperations
                .create()
                .add("/joinedUsers/-", user)
                .add("/points/-", point);
        final Mono<Party> partyMono = repository.save(partyId, new PartitionKey(PARTITION_KEY), Party.class, patchOperations);

        return partyMono.block();
    }


    @Override
    public Party addPointsToUser(String partyId, Point point) {
        CosmosPatchOperations patchOperations = CosmosPatchOperations
                .create()
                .increment("/points/" + point.getPositionInArray() + "/points", 1);

        final Mono<Party> partyMono = repository.save(partyId, new PartitionKey(PARTITION_KEY), Party.class, patchOperations);

        return partyMono.block();
    }

    @Override
    public Party updateOptionsToParty(String partyId, Option option) {
        CosmosPatchOperations patchOperations = CosmosPatchOperations
                .create()
                .set("/options/" + option.getPositionInArray() + "/done", option.getIsDone())
                .set("/options/" + option.getPositionInArray() + "/userCompleted", option.getUserCompleted());

        final Mono<Party> partyMono = repository.save(partyId, new PartitionKey(PARTITION_KEY), Party.class, patchOperations);

        return partyMono.block();
    }

}
