package de.devknochen;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.option.KeyBinding;
import org.lwjgl.glfw.GLFW;

public class QUITter implements ModInitializer {
	private static KeyBinding quickExitKey;

	@Override
	public void onInitialize() {
		quickExitKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.quitter.quickexit",
				GLFW.GLFW_KEY_X,
				"category.quitter"
		));

		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if (quickExitKey.wasPressed()) {
				if (MinecraftClient.getInstance().world != null) {
					MinecraftClient clientInstance = MinecraftClient.getInstance();
					clientInstance.world.disconnect();
					clientInstance.disconnect();
					clientInstance.setScreen(new TitleScreen());
				}
			}
		});
	}
}
