package com.catchlogger.backend.service;

import com.catchlogger.backend.dto.CatchCreateRequest;
import com.catchlogger.backend.dto.CatchUpdateRequest;
import com.catchlogger.backend.model.Catch;
import com.catchlogger.backend.repository.CatchRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CatchService {

    private final CatchRepository catchRepository;

    @Autowired
    public CatchService(CatchRepository catchRepository) {
        this.catchRepository = catchRepository;
    }

    public List<Catch> getAllCatches() {
        return catchRepository.findAll();
    }

    public Optional<Catch> getCatchById(Long id) {
        return catchRepository.findById(id);
    }

    public Catch createCatch(Catch newCatch) {
        return catchRepository.save(newCatch);
    }

    public Catch createCatch(CatchCreateRequest request) {

        Catch newCatch = Catch.builder()
                .species(request.getSpecies())
                .name(request.getName())
                .weight(request.getWeight())
                .distance(request.getDistance())
                .location(request.getLocation())
                .notes(request.getNotes())
                .rigInfo(request.getRigInfo())
                .baitInfo(request.getBaitInfo())
                .caughtAt(LocalDateTime.now())
                .build();

        return catchRepository.save(newCatch);
    }

    public Optional<Catch> updateCatch(Long id, CatchUpdateRequest request) {

        Optional<Catch> existingCatchOptional = catchRepository.findById(id);

        if (existingCatchOptional.isEmpty()) {
            return Optional.empty();
        }

        Catch existingCatch = existingCatchOptional.get();

        if (request.getName() != null) {
            existingCatch.setName(request.getName());
        }
        if (request.getSpecies() != null) {
            existingCatch.setSpecies(request.getSpecies());
        }
        if (request.getWeight() != null) {
            existingCatch.setWeight(request.getWeight());
        }
        if (request.getDistance() != null) {
            existingCatch.setDistance(request.getDistance());
        }
        if (request.getLocation() != null) {
            existingCatch.setLocation(request.getLocation());
        }
        if (request.getNotes() != null) {
            existingCatch.setNotes(request.getNotes());
        }
        if (request.getRigInfo() != null) {
            existingCatch.setRigInfo(request.getRigInfo());
        }
        if (request.getBaitInfo() != null) {
            existingCatch.setBaitInfo(request.getBaitInfo());
        }

        return Optional.of(catchRepository.save(existingCatch));
    }

    public boolean deleteCatch(Long id) {
        if (catchRepository.existsById(id)) {
            catchRepository.deleteById(id);
            return true;
        }
        return false;
    }
}