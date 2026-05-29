package com.github.stellarwind23.template.util;

public record StrPair(float Strength1, float Strength2) {

    public static StrPair of(float strength) {
        return new StrPair(strength, strength);
    }

    public StrPair multiply(StrPair other) {
        return new StrPair(this.Strength1 * other.Strength1, this.Strength2 * other.Strength2);
    }

    public StrPair multiply(float multiply) {
        return new StrPair(this.Strength1 * multiply, this.Strength2 * multiply);
    }
}