package de.simonsator.partyandfriends.minestom.api;

import de.simonsator.partyandfriends.minestom.api.pafplayers.PAFPlayer;
import de.simonsator.partyandfriends.minestom.api.pafplayers.PAFPlayerManager;

import java.util.UUID;

/**
 * @author Simonsator
 * @version 1.0.0 09.04.17
 */
public interface FriendCountPlaceHolder {
	default Integer getFriendCount(UUID pPlayer) {
		return getFriendCount(PAFPlayerManager.getInstance().getPlayer(pPlayer));
	}

	default Integer getFriendCount(String pPlayer) {
		return getFriendCount(PAFPlayerManager.getInstance().getPlayer(pPlayer));
	}

	default Integer getFriendCount(PAFPlayer pPlayer) {
		return pPlayer.getFriends().size();
	}
}
