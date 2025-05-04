package com.postgresql.proyecto1.dto;

// Clase para los datos de la consulta de las identificaciones
public class IdentificationDTO {
    private String commonName;
    private Long totalIdentifications;
    private Long uniqueIdentifiers;

    public IdentificationDTO(String commonName, Long totalIdentifications, Long uniqueIdentifiers) {
        this.commonName = commonName;
        this.totalIdentifications = totalIdentifications;
        this.uniqueIdentifiers = uniqueIdentifiers;
    }

    // Getters; A veces no detecta bien el lombok por lo que es necesario
    public String getCommonName() {
        return commonName;
    }

    public Long getTotalIdentifications() {
        return totalIdentifications;
    }

    public Long getUniqueIdentifiers() {
        return uniqueIdentifiers;
    }
}