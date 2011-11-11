package net.vz.jroper.testingpermutations;

/**
 * A VZ platform
 */
public enum Platform {
  SCHUELERVZ("http://www.schuelervz.net"), STUDIVZ("http://www.studivz.net"), FREUNDEVZ("http://www.freundevz.net");
  public final String baseUrl;
  Platform(String baseUrl) {
    this.baseUrl = baseUrl;
  }
}