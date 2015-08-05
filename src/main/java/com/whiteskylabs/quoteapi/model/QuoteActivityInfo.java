package com.whiteskylabs.quoteapi.model;


public class QuoteActivityInfo {

    private Quote quote;
    private String activity;
    private EntityInfo[] activityEntities;

    public Quote getQuote() {
        return quote;
    }

    public void setQuote(Quote quote) {
        this.quote = quote;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public EntityInfo[] getActivityEntities() {
        return activityEntities;
    }

    public void setActivityEntities(EntityInfo[] activityEntities) {
        this.activityEntities = activityEntities;
    }

    public EntityInfo getActivityEntity(String name) {
        for (EntityInfo entityInfo : this.activityEntities) {
            if (entityInfo.getName().equalsIgnoreCase(name)) {
                return entityInfo;
            }
        }

        return null;
    }

}
