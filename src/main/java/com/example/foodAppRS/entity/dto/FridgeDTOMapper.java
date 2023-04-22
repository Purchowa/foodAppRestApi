package com.example.foodAppRS.entity.dto;

import com.example.foodAppRS.entity.Fridge;
import org.springframework.stereotype.Service;

import java.util.function.Function;
@Service
public class FridgeDTOMapper implements Function<Fridge, FridgeDTO> {
    @Override
    public FridgeDTO apply(Fridge fridge) {
        return new FridgeDTO(fridge.getProduct().getName(),
                fridge.getExpirationDate());
    }
}
