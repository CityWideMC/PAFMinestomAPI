package de.simonsator.partyandfriends.minestom.api.pafplayers;

import java.util.List;
import java.util.UUID;

public interface PAFPlayer {

	String getName();

	List<PAFPlayer> getFriends();

	UUID getUniqueId();

	int getSettingsWorth(int pSettingsID);

	void setSetting(int pSettingsID, int pNewWorth);

	List<PAFPlayer> getRequests();

	boolean hasRequestFrom(PAFPlayer pPlayer);

	boolean isAFriendOf(PAFPlayer pPlayer);

	PAFPlayer getPAFPlayer();

	long getLastOnline();
}
