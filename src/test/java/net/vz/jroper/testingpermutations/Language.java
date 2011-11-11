package net.vz.jroper.testingpermutations;

/**
 * A language supported by VZ
 */
public enum Language {
  GERMAN("de"), ENGLISH("en");
  public final String abbreviation;
  Language(String abbreviation) {
    this.abbreviation = abbreviation;
  }
}