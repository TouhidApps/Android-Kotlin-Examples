
package com.touhidapps.jsondataparsing.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Collection {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("category_id")
    @Expose
    private String categoryId;
    @SerializedName("descr")
    @Expose
    private String descr;
    @SerializedName("answer")
    @Expose
    private Object answer;
    @SerializedName("total_up_vote")
    @Expose
    private String totalUpVote;
    @SerializedName("total_down_vote")
    @Expose
    private String totalDownVote;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public Object getAnswer() {
        return answer;
    }

    public void setAnswer(Object answer) {
        this.answer = answer;
    }

    public String getTotalUpVote() {
        return totalUpVote;
    }

    public void setTotalUpVote(String totalUpVote) {
        this.totalUpVote = totalUpVote;
    }

    public String getTotalDownVote() {
        return totalDownVote;
    }

    public void setTotalDownVote(String totalDownVote) {
        this.totalDownVote = totalDownVote;
    }

}
