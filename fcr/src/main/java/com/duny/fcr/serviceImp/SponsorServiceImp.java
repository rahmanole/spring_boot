package com.duny.fcr.serviceImp;

import com.duny.fcr.entity.Sponsor;
import com.duny.fcr.repo.SponsorRepo;
import com.duny.fcr.service.SponserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SponsorServiceImp implements SponserService {
    @Autowired
    SponsorRepo sponsorRepo;
//
//    public Sponsor findSponsorByName(String name){
//        return
//    }
}
