package models;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class ProblemSet extends DatabaseObject implements Taggable {

    protected int maxAttempts;
    protected Date startTime;
    protected Date endTime;
    protected int creatorID;
    
    protected List<String> tags = new ArrayList<String>();


    protected ProblemSet() {
        super();
        this.startTime = Date.from(Instant.now());
        this.endTime = Date.from(Instant.now());
    }

    /**
     * Retrieves a list of Problems that the problem set contains.
     * @return the Problems in the problem set
     */
    public abstract List<Problem> getQuestions();

    /**
     * Returns the maximum number of attempts for this Problem Set.
     * @return the maximum number of attempts for this Problem Set. -1 represents no limit.
     */
    public int getMaxAttempts() {
        return maxAttempts;
    };

    /**
     * Sets the maximum number of attempts for this problem set
     * @param maxAttempts the maxmimum number of attempts for this problem set
     */
    public void setMaxAttempts(int maxAttempts) {
        this.maxAttempts = maxAttempts;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public int getCreatorID() {
        return creatorID;
    }

    public void setCreatorID(int creatorID) {
        this.creatorID = creatorID;
    }
    
    public void addTags(List<String> newTags) {
      for (String tag : newTags) {
        if (!(this.tags.contains(tag))) {
          this.tags.add(tag);
        }
      }
    }
    
    public List<String> getTags() {
      return this.tags;
    }
    
    public void removeTags(List<String> tagsToRemove) {
      this.tags.removeAll(tagsToRemove);
    }

    /**
     * Returns whether or not the tag string requested matches this problem.
     * @param tagString a space separated list of tags
     * @return True iff all of the tags in the string are present (even partially) in the problem as tags or id.
     */
    public boolean matchesSearchString(String tagString) {
        // Split args into individual tags
        String[] tags = tagString.split(" ");

        // Iterate for partial tags
        for (int i = 0; i < tags.length; i++) {
            boolean matchedTag = false;

            // Attempt to match by id
            if (String.valueOf(getId()).equals(tags[i])) {
                matchedTag = true;
            }

            // Match by tags
            for (String t : getTags()) {
                if (t.toLowerCase().contains(tags[i].toLowerCase())) {
                    matchedTag = true;
                    break;
                }
            }

            if (!matchedTag) {
                return false;
            }
        }

        return true;
    }
}
