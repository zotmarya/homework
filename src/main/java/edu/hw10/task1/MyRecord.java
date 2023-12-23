package edu.hw10.task1;

public record MyRecord(String name, @Min(value = 0) @Max(value = 120) int age) {
}
