package com.rivet.api.resources.identity.common.types;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.rivet.api.core.ObjectMappers;
import java.util.Objects;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonDeserialize(builder = GlobalEventChatThreadRemove.Builder.class)
public final class GlobalEventChatThreadRemove {
    private final UUID threadId;

    private GlobalEventChatThreadRemove(UUID threadId) {
        this.threadId = threadId;
    }

    @JsonProperty("thread_id")
    public UUID getThreadId() {
        return threadId;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        return other instanceof GlobalEventChatThreadRemove && equalTo((GlobalEventChatThreadRemove) other);
    }

    private boolean equalTo(GlobalEventChatThreadRemove other) {
        return threadId.equals(other.threadId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.threadId);
    }

    @Override
    public String toString() {
        return ObjectMappers.stringify(this);
    }

    public static ThreadIdStage builder() {
        return new Builder();
    }

    public interface ThreadIdStage {
        _FinalStage threadId(UUID threadId);

        Builder from(GlobalEventChatThreadRemove other);
    }

    public interface _FinalStage {
        GlobalEventChatThreadRemove build();
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class Builder implements ThreadIdStage, _FinalStage {
        private UUID threadId;

        private Builder() {}

        @Override
        public Builder from(GlobalEventChatThreadRemove other) {
            threadId(other.getThreadId());
            return this;
        }

        @Override
        @JsonSetter("thread_id")
        public _FinalStage threadId(UUID threadId) {
            this.threadId = threadId;
            return this;
        }

        @Override
        public GlobalEventChatThreadRemove build() {
            return new GlobalEventChatThreadRemove(threadId);
        }
    }
}
