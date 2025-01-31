package com.rivet.api.resources.chat.requests;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.rivet.api.core.ObjectMappers;
import com.rivet.api.resources.chat.common.types.QueryDirection;
import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.Optional;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonDeserialize(builder = GetThreadHistoryRequest.Builder.class)
public final class GetThreadHistoryRequest {
    private final Optional<OffsetDateTime> ts;

    private final double count;

    private final Optional<QueryDirection> queryDirection;

    private GetThreadHistoryRequest(
            Optional<OffsetDateTime> ts, double count, Optional<QueryDirection> queryDirection) {
        this.ts = ts;
        this.count = count;
        this.queryDirection = queryDirection;
    }

    /**
     * @return RFC3339 timestamp.
     */
    @JsonProperty("ts")
    public Optional<OffsetDateTime> getTs() {
        return ts;
    }

    /**
     * @return How many messages to collect in each direction. If querying <code>rivet.api.chat.common#QueryDirection$before_and_after</code>, <code>rivet.api.chat.common#QueryDirection$chat_messages</code> will be <code>count * 2</code>.
     */
    @JsonProperty("count")
    public double getCount() {
        return count;
    }

    /**
     * @return Represents which direction to query messages from relative to the given timestamp.
     */
    @JsonProperty("query_direction")
    public Optional<QueryDirection> getQueryDirection() {
        return queryDirection;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        return other instanceof GetThreadHistoryRequest && equalTo((GetThreadHistoryRequest) other);
    }

    private boolean equalTo(GetThreadHistoryRequest other) {
        return ts.equals(other.ts) && count == other.count && queryDirection.equals(other.queryDirection);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.ts, this.count, this.queryDirection);
    }

    @Override
    public String toString() {
        return ObjectMappers.stringify(this);
    }

    public static CountStage builder() {
        return new Builder();
    }

    public interface CountStage {
        _FinalStage count(double count);

        Builder from(GetThreadHistoryRequest other);
    }

    public interface _FinalStage {
        GetThreadHistoryRequest build();

        _FinalStage ts(Optional<OffsetDateTime> ts);

        _FinalStage ts(OffsetDateTime ts);

        _FinalStage queryDirection(Optional<QueryDirection> queryDirection);

        _FinalStage queryDirection(QueryDirection queryDirection);
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class Builder implements CountStage, _FinalStage {
        private double count;

        private Optional<QueryDirection> queryDirection = Optional.empty();

        private Optional<OffsetDateTime> ts = Optional.empty();

        private Builder() {}

        @Override
        public Builder from(GetThreadHistoryRequest other) {
            ts(other.getTs());
            count(other.getCount());
            queryDirection(other.getQueryDirection());
            return this;
        }

        /**
         * <p>How many messages to collect in each direction. If querying <code>rivet.api.chat.common#QueryDirection$before_and_after</code>, <code>rivet.api.chat.common#QueryDirection$chat_messages</code> will be <code>count * 2</code>.</p>
         * @return Reference to {@code this} so that method calls can be chained together.
         */
        @Override
        @JsonSetter("count")
        public _FinalStage count(double count) {
            this.count = count;
            return this;
        }

        /**
         * <p>Represents which direction to query messages from relative to the given timestamp.</p>
         * @return Reference to {@code this} so that method calls can be chained together.
         */
        @Override
        public _FinalStage queryDirection(QueryDirection queryDirection) {
            this.queryDirection = Optional.of(queryDirection);
            return this;
        }

        @Override
        @JsonSetter(value = "query_direction", nulls = Nulls.SKIP)
        public _FinalStage queryDirection(Optional<QueryDirection> queryDirection) {
            this.queryDirection = queryDirection;
            return this;
        }

        /**
         * <p>RFC3339 timestamp.</p>
         * @return Reference to {@code this} so that method calls can be chained together.
         */
        @Override
        public _FinalStage ts(OffsetDateTime ts) {
            this.ts = Optional.of(ts);
            return this;
        }

        @Override
        @JsonSetter(value = "ts", nulls = Nulls.SKIP)
        public _FinalStage ts(Optional<OffsetDateTime> ts) {
            this.ts = ts;
            return this;
        }

        @Override
        public GetThreadHistoryRequest build() {
            return new GetThreadHistoryRequest(ts, count, queryDirection);
        }
    }
}
