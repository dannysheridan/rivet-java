package com.rivet.api.resources.chat.common.types;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.rivet.api.core.ObjectMappers;
import com.rivet.api.resources.identity.common.types.Handle;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonDeserialize(builder = IdentityTypingStatus.Builder.class)
public final class IdentityTypingStatus {
    private final Handle identity;

    private final TypingStatus status;

    private IdentityTypingStatus(Handle identity, TypingStatus status) {
        this.identity = identity;
        this.status = status;
    }

    @JsonProperty("identity")
    public Handle getIdentity() {
        return identity;
    }

    @JsonProperty("status")
    public TypingStatus getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        return other instanceof IdentityTypingStatus && equalTo((IdentityTypingStatus) other);
    }

    private boolean equalTo(IdentityTypingStatus other) {
        return identity.equals(other.identity) && status.equals(other.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.identity, this.status);
    }

    @Override
    public String toString() {
        return ObjectMappers.stringify(this);
    }

    public static IdentityStage builder() {
        return new Builder();
    }

    public interface IdentityStage {
        StatusStage identity(Handle identity);

        Builder from(IdentityTypingStatus other);
    }

    public interface StatusStage {
        _FinalStage status(TypingStatus status);
    }

    public interface _FinalStage {
        IdentityTypingStatus build();
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class Builder implements IdentityStage, StatusStage, _FinalStage {
        private Handle identity;

        private TypingStatus status;

        private Builder() {}

        @Override
        public Builder from(IdentityTypingStatus other) {
            identity(other.getIdentity());
            status(other.getStatus());
            return this;
        }

        @Override
        @JsonSetter("identity")
        public StatusStage identity(Handle identity) {
            this.identity = identity;
            return this;
        }

        @Override
        @JsonSetter("status")
        public _FinalStage status(TypingStatus status) {
            this.status = status;
            return this;
        }

        @Override
        public IdentityTypingStatus build() {
            return new IdentityTypingStatus(identity, status);
        }
    }
}
