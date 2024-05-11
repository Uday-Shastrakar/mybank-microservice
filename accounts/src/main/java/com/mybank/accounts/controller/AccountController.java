package com.mybank.accounts.controller;

import com.mybank.accounts.constants.AccountConstants;
import com.mybank.accounts.dto.CustomerDto;
import com.mybank.accounts.dto.ErrorResponseDto;
import com.mybank.accounts.dto.ResponseDto;
import com.mybank.accounts.service.IAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "CRUD REST APIs for account in myBank",
        description = "CRUD REST APIs In myBank to CREATE,UPDATE,FETCH and DELETE account details")
@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class AccountController {

    private IAccountService iAccountService;

    @Operation(
            summary = "create Account  Rest API",
            description = "Rest API to create new customer & account inside mybank"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http status code created"
    )
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@RequestBody CustomerDto customerDto) {
        iAccountService.createAccount(customerDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountConstants.STATUS_201, AccountConstants.MESSAGE_201));
    }

    @Operation(
            summary = "fetch Account  Rest API",
            description = "Rest API to fetch  customer & account inside mybank"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http status code ok"
    )
    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> fetchAccountDetails(@RequestParam String mobileNumber) {
        CustomerDto customerDto = iAccountService.fetchAccount(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(customerDto);
    }


    @Operation(
            summary = "update Account  Rest API",
            description = "Rest API to update existing customer & account inside mybank"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Http status code updated"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Http status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAccountDetails(@Valid @RequestBody CustomerDto customerDto) {
        boolean isUpdated = iAccountService.updateAccount(customerDto);
        if (isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(AccountConstants.STATUS_200, AccountConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(AccountConstants.STATUS_500, AccountConstants.MESSAGE_500));
        }
    }

    @Operation(
            summary = "delete Account  Rest API",
            description = "Rest API to delete existing customer & account inside mybank"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Http status code deleted"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Http status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAccountDetails(@RequestParam
                                                            @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
                                                            String mobileNumber) {
        boolean isDeleted = iAccountService.deleteAccount(mobileNumber);
        if (isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(AccountConstants.STATUS_200, AccountConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(AccountConstants.STATUS_500, AccountConstants.MESSAGE_500));
        }
    }


}
