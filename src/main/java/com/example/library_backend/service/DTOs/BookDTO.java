package com.example.library_backend.service.DTOs;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BookDTO(@NotBlank String title, @NotBlank String author, @Min(0) @Max(1000) int pages, @NotNull Boolean read) {
}
