package org.missions.objective.trigger.event.criteria.match.displayname;

import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.jetbrains.annotations.NotNull;
import org.missions.objective.trigger.event.criteria.EventCriteriaMeta;
import org.missions.objective.trigger.event.criteria.EventsCriteria;
import org.spongepowered.api.data.DataHolder;
import org.spongepowered.api.data.Keys;

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

    public boolean isIgnoringCase() {
        return this.ignoreCase;
    }

    public DisplayNameMatchesEventMeta setIgnoringCase(boolean check) {
        this.ignoreCase = check;
        return this;
    }

    public String getNameToMatch() {
        return nameToMatch;
    }

    public DisplayNameMatchesEventMeta setNameToMatch(@NotNull String nameToMatch) {
        this.nameToMatch = nameToMatch;
        return this;
    }

    @Override
    public @NotNull DisplayNameMatchesEventCriteria getCriteria() {
        return EventsCriteria.ITEM_NAME_MATCHES;
    }
}
