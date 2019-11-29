package com.duny.fcr.repo;

import com.duny.fcr.entity.Sponsor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SponsorRepo extends JpaRepository<Sponsor,Long> {
    String GET_SPONSOR_LIST = "select name from sponsor";

    @Query(nativeQuery = true,value = GET_SPONSOR_LIST)
    List<String> getSponsorNames();

    Sponsor findSponsorByName(String name);
}
