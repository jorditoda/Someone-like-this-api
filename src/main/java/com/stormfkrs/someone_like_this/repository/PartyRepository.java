package com.stormfkrs.someone_like_this.repository;


import com.azure.spring.data.cosmos.repository.Query;
import com.azure.spring.data.cosmos.repository.ReactiveCosmosRepository;
import com.stormfkrs.someone_like_this.entity.Party;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface PartyRepository extends ReactiveCosmosRepository<Party, String> {

    @Query(value = "Select * from Party p Where Exists (Select Value u from u In p.joinedUsers where u.id = @userId )")
    Flux<Party> findAllByJoinedUsersId(String userId);

}
