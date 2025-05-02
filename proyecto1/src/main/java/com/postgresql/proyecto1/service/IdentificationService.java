package com.postgresql.proyecto1.service;

import com.postgresql.proyecto1.dto.IdentificationDTO;
import com.postgresql.proyecto1.repo.IdentificationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IdentificationService {

    @Autowired
    private IdentificationRepo repo;

    // Obtener la consulta y acomodarla en una lista (por ser nativa tal cual no funcionaria)
    public List<IdentificationDTO> getAll() {
        List<Object[]> temp = repo.getConsult();
        List<IdentificationDTO> result = new ArrayList<>();

        for (Object[] row : temp) {
            String commonName = (String) row[0];
            Long totalIdentifications = ((Number) row[1]).longValue();
            Long uniqueIdentifiers = ((Number) row[2]).longValue();

            result.add(new IdentificationDTO(commonName, totalIdentifications, uniqueIdentifiers));
        }
        return result;
    }
}
