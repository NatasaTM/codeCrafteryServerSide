package com.codecraftery.Code.craftery.server.side.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Natasa Todorov Markovic
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminDto {
    private String email;
    private String name;
}
