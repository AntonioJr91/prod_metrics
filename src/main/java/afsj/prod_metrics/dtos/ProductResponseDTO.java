package afsj.prod_metrics.dtos;

import afsj.prod_metrics.enums.UnitOfMeasure;

public record ProductResponseDTO(String name, UnitOfMeasure unitOfMeasure) {
}
