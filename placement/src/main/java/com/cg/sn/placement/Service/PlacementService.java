package com.cg.sn.placement.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.sn.placement.Entity.Placement;
import com.cg.sn.placement.Repository.PlacementRepository;

import java.util.List;

@Service
public class PlacementService {
    private final PlacementRepository placementRepository;

    @Autowired
    public PlacementService(PlacementRepository placementRepository) {
        this.placementRepository = placementRepository;
    }

    public List<Placement> getAllPlacements() {
        return placementRepository.findAll();
    }
}
