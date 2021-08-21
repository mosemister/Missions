package org.missions.objective.trigger.event.criteria.match.displayname;

import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.jetbrains.annotations.NotNull;
import org.missions.objective.trigger.event.criteria.EventCriteriaMeta;
import org.missions.objective.trigger.event.criteria.EventsCriteria;
import org.spongepowered.api.data.DataHolder;
import org.spongepowered.api.data.Keys;

/**
 * Criteria for matching a {@link org.spongepowered.api.data.Key} of {@link Keys#DISPLAY_NAME} with the provided
 * information.
 */
public class DisplayNameMatchesEventMeta implements EventCriteriaMeta<DisplayNameMatchesEventCriteria, DataHolder> {

    private String nameToMatch;
    private boolean ignoreCase;

    @Override
    public boolean isCriteriaMet(DataHolder stack) {
        return stack.get(Keys.DISPLAY_NAME).map(component -> {
            String plain = PlainTextComponentSerializer.plainText().serialize(component);
            if (this.ignoreCase) {
                return plain.equalsIgnoreCase(this.nameToMatch);
            } else {
                return plain.equals(this.nameToMatch);
            }
        }).orElse(false);
    }

    /**
     * Checks if the name is to ignore the character case
     *
     * @return if the meta is ignoring the case
     */
    public boolean isIgnoringCase() {
        return this.ignoreCase;
    }

    /**
     * Sets if the check should ignore the character case
     *
     * @param check if it should ignore case
     * @return this, for chaining
     */
    public DisplayNameMatchesEventMeta setIgnoringCase(boolean check) {
        this.ignoreCase = check;
        return this;
    }

    /**
     * Gets the text to compare against
     *
     * @return The string text to compare against, null if not set
     */
    public String getNameToMatch() {
        return nameToMatch;
    }

    /**
     * Sets the text to compare
     *
     * @param nameToMatch the string text to compare against
     * @return this, for chaining
     */
    public DisplayNameMatchesEventMeta setNameToMatch(@NotNull String nameToMatch) {
        this.nameToMatch = nameToMatch;
        return this;
    }

    /**
     * Gets the Criteria for this meta
     *
     * @return the Criteria type
     */
    @Override
    public @NotNull DisplayNameMatchesEventCriteria getCriteria() {
        return EventsCriteria.ITEM_NAME_MATCHES;
    }
}
