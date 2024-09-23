package com.stormfkrs.someone_like_this.controller;

import com.stormfkrs.someone_like_this.entity.Option;
import com.stormfkrs.someone_like_this.entity.Party;
import com.stormfkrs.someone_like_this.entity.Point;
import com.stormfkrs.someone_like_this.entity.User;
import com.stormfkrs.someone_like_this.service.PartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/party")
//@Api(description = "REST API for Party operations", tags = {"Party"})
public class PartyController {

    @Autowired
    PartyService service;

    @PostMapping()
    public ResponseEntity<Party> saveParty(@RequestBody Party party) {

        final Party partySaved = service.save(party);
        return ResponseEntity.ok(partySaved);
    }

    @GetMapping()
    public ResponseEntity<List<Party>> getParties() {

        final List<Party> parties = service.getAll();
        return ResponseEntity.ok(parties);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Party> getPartyById(@PathVariable String id) {

        final Party partySaved = service.getById(id);
        return ResponseEntity.ok(partySaved);
    }

    @GetMapping(value = "/user/{userId}")
    public ResponseEntity<List<Party>> getPartiesByUserId(@PathVariable String userId) {
        List<Party> parties = service.findAllByUserId(userId);

        return ResponseEntity.ok(parties);
    }

    @PutMapping(value = "/{partyId}/user")
    public ResponseEntity<Party> addUserToParty(@PathVariable String partyId, @RequestBody User user) {
        Party party = service.addUserToParty(partyId, user);

        return ResponseEntity.ok(party);
    }

    @PutMapping(value = "/{partyId}/point")
    public ResponseEntity<Party> addPointsToUser(@PathVariable String partyId, @RequestBody Point point) {
        Party party = service.addPointsToUser(partyId, point);

        return ResponseEntity.ok(party);
    }

    @PutMapping(value = "/{partyId}/option")
    public ResponseEntity<Party> updateOptionsToParty(@PathVariable String partyId, @RequestBody Option option) {
        Party party = service.updateOptionsToParty(partyId, option);

        return ResponseEntity.ok(party);
    }
}
