package com.example.foodAppRS.service;

import com.example.foodAppRS.entity.Fridge;
import com.example.foodAppRS.entity.dto.FridgeDTO;
import com.example.foodAppRS.entity.dto.FridgeDTOMapper;
import com.example.foodAppRS.repository.FridgeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FridgeService {
    private final FridgeRepository fridgeRepository;
    private final FridgeDTOMapper fridgeDTOMapper;

    @Autowired
    public FridgeService(FridgeRepository fridgeRepository, FridgeDTOMapper fridgeDTOMapper) {
        this.fridgeRepository = fridgeRepository;
        this.fridgeDTOMapper = fridgeDTOMapper;
    }

    public List<FridgeDTO> selectAllFridges() {
        return fridgeRepository.findAll()
                .stream()
                .map(fridgeDTOMapper)
                .toList();
    }

    /*public Optional<Fridge> selectFridgeById(Integer id) {
        return Optional.of(fridgeRepository.findById(id)).get();
    }

    public FridgeDTO addToFridge(Fridge fridge) {
        Fridge save = fridgeRepository.save(fridge);
        return new FridgeDTO(save.getProduct().getName(),
                save.getExpirationDate());
    }*/
}
