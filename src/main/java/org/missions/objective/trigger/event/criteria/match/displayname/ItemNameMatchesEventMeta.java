package org.missions.objective.trigger.event.criteria.match.displayname;

import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.jetbrains.annotations.NotNull;
import org.missions.objective.trigger.event.criteria.EventCriteriaMeta;
import org.missions.objective.trigger.event.criteria.EventsCriteria;
import org.spongepowered.api.data.Keys;
import org.spongepowered.api.data.SerializableDataHolder;

public class ItemNameMatchesEventMeta implements EventCriteriaMeta<ItemNameMatchesEventCriteria> {

    private String nameToMatch;
    private boolean ignoreCase;

    public boolean isCriteriaMet(SerializableDataHolder stack) {
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

    public ItemNameMatchesEventMeta setIgnoringCase(boolean check) {
        this.ignoreCase = check;
        return this;
    }

    public String getNameToMatch() {
        return nameToMatch;
    }

    public ItemNameMatchesEventMeta setNameToMatch(String nameToMatch) {
        this.nameToMatch = nameToMatch;
        return this;
    }

    @Override
    public @NotNull ItemNameMatchesEventCriteria getCriteria() {
        return EventsCriteria.ITEM_NAME_MATCHES;
    }
}
