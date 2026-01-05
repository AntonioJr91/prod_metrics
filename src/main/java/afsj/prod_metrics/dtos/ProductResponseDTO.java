package afsj.prod_metrics.dtos;

import afsj.prod_metrics.enums.UnitOfMeasure;

import java.util.UUID;

public record ProductResponseDTO(UUID id, String name, UnitOfMeasure unitOfMeasure) {
}
