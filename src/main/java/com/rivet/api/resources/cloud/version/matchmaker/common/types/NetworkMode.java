package com.rivet.api.resources.cloud.version.matchmaker.common.types;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.util.Locale;

public final class NetworkMode {
  public static final NetworkMode BRIDGE = new NetworkMode(Value.BRIDGE, "bridge");

  public static final NetworkMode HOST = new NetworkMode(Value.HOST, "host");

  private final Value value;

  private final String string;

  NetworkMode(Value value, String string) {
    this.value = value;
    this.string = string;
  }

  public Value getEnumValue() {
    return value;
  }

  @Override
  @JsonValue
  public String toString() {
    return this.string;
  }

  @Override
  public boolean equals(Object other) {
    return (this == other) 
      || (other instanceof NetworkMode && this.string.equals(((NetworkMode) other).string));
  }

  @Override
  public int hashCode() {
    return this.string.hashCode();
  }

  public <T> T visit(Visitor<T> visitor) {
    switch (value) {
      case BRIDGE:
        return visitor.visitBridge();
      case HOST:
        return visitor.visitHost();
      case UNKNOWN:
      default:
        return visitor.visitUnknown(string);
    }
  }

  @JsonCreator(
      mode = JsonCreator.Mode.DELEGATING
  )
  public static NetworkMode valueOf(String value) {
    String upperCasedValue = value.toUpperCase(Locale.ROOT);
    switch (upperCasedValue) {
      case "bridge":
        return BRIDGE;
      case "host":
        return HOST;
      default:
        return new NetworkMode(Value.UNKNOWN, upperCasedValue);
    }
  }

  public enum Value {
    BRIDGE,

    HOST,

    UNKNOWN
  }

  public interface Visitor<T> {
    T visitBridge();

    T visitHost();

    T visitUnknown(String unknownType);
  }
}
