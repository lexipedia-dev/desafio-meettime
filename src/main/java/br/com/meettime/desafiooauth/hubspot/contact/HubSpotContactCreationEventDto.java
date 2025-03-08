package br.com.meettime.desafiooauth.hubspot.contact;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HubSpotContactCreationEventDto {

    @JsonProperty("objectId")
    private Long objectId;

    @JsonProperty("propertyName")
    private String propertyName;

    @JsonProperty("propertyValue")
    private String propertyValue;

    @JsonProperty("changeSource")
    private String changeSource;

    @JsonProperty("eventId")
    private Long eventId;

    @JsonProperty("subscriptionId")
    private Long subscriptionId;

    @JsonProperty("portalId")
    private Long portalId;

    @JsonProperty("appId")
    private Long appId;

    @JsonProperty("occurredAt")
    private Long occurredAt;

    @JsonProperty("eventType")
    private String eventType;

    @JsonProperty("attemptNumber")
    private Integer attemptNumber;

    public HubSpotContactCreationEventDto() {}

    public HubSpotContactCreationEventDto(Long objectId, String propertyName, String propertyValue, String changeSource, Long eventId,
                                          Long subscriptionId, Long portalId, Long appId, Long occurredAt, String eventType, Integer attemptNumber) {
        this.objectId = objectId;
        this.propertyName = propertyName;
        this.propertyValue = propertyValue;
        this.changeSource = changeSource;
        this.eventId = eventId;
        this.subscriptionId = subscriptionId;
        this.portalId = portalId;
        this.appId = appId;
        this.occurredAt = occurredAt;
        this.eventType = eventType;
        this.attemptNumber = attemptNumber;
    }

    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getPropertyValue() {
        return propertyValue;
    }

    public void setPropertyValue(String propertyValue) {
        this.propertyValue = propertyValue;
    }

    public String getChangeSource() {
        return changeSource;
    }

    public void setChangeSource(String changeSource) {
        this.changeSource = changeSource;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public Long getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(Long subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public Long getPortalId() {
        return portalId;
    }

    public void setPortalId(Long portalId) {
        this.portalId = portalId;
    }

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public Long getOccurredAt() {
        return occurredAt;
    }

    public void setOccurredAt(Long occurredAt) {
        this.occurredAt = occurredAt;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public Integer getAttemptNumber() {
        return attemptNumber;
    }

    public void setAttemptNumber(Integer attemptNumber) {
        this.attemptNumber = attemptNumber;
    }

    @Override
    public String toString() {
        return "HubSpotContactCreationEventDto{" +
                "objectId=" + objectId +
                ", propertyName='" + propertyName + '\'' +
                ", propertyValue='" + propertyValue + '\'' +
                ", changeSource='" + changeSource + '\'' +
                ", eventId=" + eventId +
                ", subscriptionId=" + subscriptionId +
                ", portalId=" + portalId +
                ", appId=" + appId +
                ", occurredAt=" + occurredAt +
                ", eventType='" + eventType + '\'' +
                ", attemptNumber=" + attemptNumber +
                '}';
    }
}
