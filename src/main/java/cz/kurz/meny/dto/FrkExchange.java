package cz.kurz.meny.dto;

import java.util.Map;

public record FrkExchange(Integer amount, String base, String date, Map<String, Float> rates) {}

