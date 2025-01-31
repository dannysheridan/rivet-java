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
@JsonDeserialize(builder = TopicDirect.Builder.class)
public final class TopicDirect {
    private final Handle identityA;

    private final Handle identityB;

    private TopicDirect(Handle identityA, Handle identityB) {
        this.identityA = identityA;
        this.identityB = identityB;
    }

    @JsonProperty("identity_a")
    public Handle getIdentityA() {
        return identityA;
    }

    @JsonProperty("identity_b")
    public Handle getIdentityB() {
        return identityB;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        return other instanceof TopicDirect && equalTo((TopicDirect) other);
    }

    private boolean equalTo(TopicDirect other) {
        return identityA.equals(other.identityA) && identityB.equals(other.identityB);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.identityA, this.identityB);
    }

    @Override
    public String toString() {
        return ObjectMappers.stringify(this);
    }

    public static IdentityAStage builder() {
        return new Builder();
    }

    public interface IdentityAStage {
        IdentityBStage identityA(Handle identityA);

        Builder from(TopicDirect other);
    }

    public interface IdentityBStage {
        _FinalStage identityB(Handle identityB);
    }

    public interface _FinalStage {
        TopicDirect build();
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class Builder implements IdentityAStage, IdentityBStage, _FinalStage {
        private Handle identityA;

        private Handle identityB;

        private Builder() {}

        @Override
        public Builder from(TopicDirect other) {
            identityA(other.getIdentityA());
            identityB(other.getIdentityB());
            return this;
        }

        @Override
        @JsonSetter("identity_a")
        public IdentityBStage identityA(Handle identityA) {
            this.identityA = identityA;
            return this;
        }

        @Override
        @JsonSetter("identity_b")
        public _FinalStage identityB(Handle identityB) {
            this.identityB = identityB;
            return this;
        }

        @Override
        public TopicDirect build() {
            return new TopicDirect(identityA, identityB);
        }
    }
}
