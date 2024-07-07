package com.smit_proovitoo.backend.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProcedureDto {

    @NotNull String name;
    @NotNull Long identityCode;
    @NotNull String email;
    @NotNull String reason;
    
}
