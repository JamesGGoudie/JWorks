package models;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;

/**
 * Represents a base problem with a question and answer
 */
public abstract class Problem extends DatabaseObject {

    // Properties
    protected final SimpleStringProperty problemProperty = new SimpleStringProperty("");
    protected final SimpleStringProperty answerProperty = new SimpleStringProperty("");
    
    protected List<String> tags = new ArrayList<String>();
    protected int creatorID;

    /**
     * Creates a new Problem.
     */
    protected Problem() {
        super();
    }

    /**
     * Gets the question of this problem.
     * @return the question component of the problem
     */
    public abstract String getProblem();

    /**
     * Gets the answer to this problem.
     * @return The answer to the problem.
     */
    public abstract String getAnswer();

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
     * @return True iff all of the tags in the string are present (even partially) in the problem as tags, questions,
     * or answers.
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

            // Attempt to match by question and answer
            if (getProblem().toLowerCase().contains(tags[i].toLowerCase())
                    || getAnswer().toLowerCase().contains(tags[i].toLowerCase())) {
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
