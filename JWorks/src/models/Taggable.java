package models;

import java.util.List;

/**
 * Interface to represent objects that have tags
 */
public interface Taggable {
    /**
     * Returns true iff the object's properties matches the given search string's terms.
     * @param searchString terms separated by spaces.
     * @return true iff the object's properties matches the given search string's terms.
     */
    boolean matchesSearchString(String searchString);

    /**
     * Adds the tags in the given list to the object, unless it already exists.
     * @param newTags the new tags to add to the object
     */
    void addTags(List<String> newTags);

    /**
     * Retrieves the tags of the object in a list.
     * @return a list of tags.
     */
    List<String> getTags();

    /**
     * Removes the tags in the given list from the object.
     * @param tagsToRemove a list of tags to remove.
     */
    void removeTags(List<String> tagsToRemove);
}
