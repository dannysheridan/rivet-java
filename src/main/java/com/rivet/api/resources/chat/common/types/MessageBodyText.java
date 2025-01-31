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
@JsonDeserialize(builder = MessageBodyText.Builder.class)
public final class MessageBodyText {
    private final Handle sender;

    private final String body;

    private MessageBodyText(Handle sender, String body) {
        this.sender = sender;
        this.body = body;
    }

    @JsonProperty("sender")
    public Handle getSender() {
        return sender;
    }

    @JsonProperty("body")
    public String getBody() {
        return body;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        return other instanceof MessageBodyText && equalTo((MessageBodyText) other);
    }

    private boolean equalTo(MessageBodyText other) {
        return sender.equals(other.sender) && body.equals(other.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.sender, this.body);
    }

    @Override
    public String toString() {
        return ObjectMappers.stringify(this);
    }

    public static SenderStage builder() {
        return new Builder();
    }

    public interface SenderStage {
        BodyStage sender(Handle sender);

        Builder from(MessageBodyText other);
    }

    public interface BodyStage {
        _FinalStage body(String body);
    }

    public interface _FinalStage {
        MessageBodyText build();
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class Builder implements SenderStage, BodyStage, _FinalStage {
        private Handle sender;

        private String body;

        private Builder() {}

        @Override
        public Builder from(MessageBodyText other) {
            sender(other.getSender());
            body(other.getBody());
            return this;
        }

        @Override
        @JsonSetter("sender")
        public BodyStage sender(Handle sender) {
            this.sender = sender;
            return this;
        }

        @Override
        @JsonSetter("body")
        public _FinalStage body(String body) {
            this.body = body;
            return this;
        }

        @Override
        public MessageBodyText build() {
            return new MessageBodyText(sender, body);
        }
    }
}
