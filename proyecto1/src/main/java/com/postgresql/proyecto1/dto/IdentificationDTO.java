package com.postgresql.proyecto1.dto;

public class IdentificationDTO {
    private String commonName;
    private Long totalIdentifications;
    private Long uniqueIdentifiers;

    public IdentificationDTO(String commonName, Long totalIdentifications, Long uniqueIdentifiers) {
        this.commonName = commonName;
        this.totalIdentifications = totalIdentifications;
        this.uniqueIdentifiers = uniqueIdentifiers;
    }

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