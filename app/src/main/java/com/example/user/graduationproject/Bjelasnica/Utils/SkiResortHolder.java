package com.example.user.graduationproject.Bjelasnica.Utils;

public class SkiResortHolder {
    private static SkiResort skiResort;

    public static void setSkiResort(final SkiResort skiResort) {
        SkiResortHolder.skiResort = skiResort;
    }

    public static SkiResort getSkiResort() {
        return skiResort;
    }

}
