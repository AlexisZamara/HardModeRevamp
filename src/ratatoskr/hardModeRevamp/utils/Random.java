package ratatoskr.hardModeRevamp.utils;

public class Random {
	public static int RandomInt(int min, int max) {
		return min + (int)(Math.random() * ((max - min) + 1));
	}
	public static Double RandomDouble(Double min, Double max) {
		return min + (Math.random() * (max - min));
	}
}
