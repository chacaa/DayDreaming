package com.xmartlabs.daydreaming;

public enum BuildType {
  STAGING,
  PRODUCTION;

  @Override
  public String toString() {
    switch (this) {
      case STAGING:
        return "staging";
      case PRODUCTION:
        return "production";
      default:
        throw new IllegalStateException("Illegal build type");
    }
  }
}
