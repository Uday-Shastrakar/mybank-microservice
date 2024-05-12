package com.mybank.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(
        name = "Account",
        description = "Schema to hold  Account information"
)
public class AccountDto {
    @Schema(description = "account number of mybank")
    private Long accountNumber;
    @Schema(description = "account type of account", example = "current")
    private String accountType;
    @Schema(description = "branch address of mybank", example = "123 new york")
    private String branchAddress;
}
