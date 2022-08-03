package com.thevortex.allthetweaks;

import java.time.OffsetDateTime;
import java.util.*;

import com.thevortex.allthetweaks.DRP.EnumState;
import com.thevortex.allthetweaks.DRP.State;
import com.thevortex.repack.com.jagrosh.discordipc.IPCClient;
import com.thevortex.repack.com.jagrosh.discordipc.entities.RichPresence.Builder;
import com.thevortex.repack.com.jagrosh.discordipc.exceptions.NoDiscordClientException;

import net.minecraft.core.MappedRegistry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraftforge.api.distmarker.*;
import net.minecraftforge.fml.ModList;

@OnlyIn(Dist.CLIENT)
public class DRP {
	
	private static final IPCClient CLIENT = new IPCClient(AllTheTweaks.IPCC);
	
	private static boolean isEnabled = false;
	
	private static final OffsetDateTime TIME = OffsetDateTime.now();
	public static State currentState = new State(EnumState.STARTUP);
	
	private static int errorCount = 0;
	
	private static final Timer TIMER = new Timer("Discord Rich Presence Timer Thread");
	private static TimerTask timerTask;
	
	static {
		Runtime.getRuntime().addShutdownHook(new Thread(() -> stop(), "Discord Rich Presence Stop Thread"));
	}
	
	public static void start() {
		try {
			CLIENT.connect();
			TIMER.schedule(timerTask = new TimerTask() {
				
				@Override
				public void run() {
					setState(currentState);
					
				}
			}, 1000, 1000 * 120);
			isEnabled = true;
			
		} catch (final NoDiscordClientException ex) {
			
		}
	}
	
	public static void stop() {
		if (timerTask != null) {
			timerTask.cancel();
			timerTask = null;
		}
		try {
			CLIENT.close();
		} catch (final Exception ex) {
		}
		errorCount = 0;
		isEnabled = false;
		
	}
	
	public static void setIdling() {
		setState(new State(EnumState.MENU));
	}
	
	public static void setDimension(ResourceKey<Level> world) {
		setState(getStateFromDimension(world));
	}
	
	
	public static State getStateFromDimension(ResourceKey<Level> world) {
		//func_236063_b_ = .getType()
		AllTheTweaks.LOGGER.debug(world.location().getPath());
					return getStateFromDimension(world.location().getPath());
		
	}
	
	public static void setState(State state) {
		currentState = state;
		final Builder builder = new Builder();
		builder.setDetails(ModList.get().size() + " Mods");
		builder.setState(state.getState().getMessage(state.getReplace()));
		builder.setStartTimestamp(TIME);
		builder.setLargeImage(AllTheTweaks.ATM,AllTheTweaks.DISPLAY);
		
		if (state.getState() == EnumState.STARTUP) {
			builder.setLargeImage("mojang", "Loading");
			builder.setSmallImage(AllTheTweaks.ATM,AllTheTweaks.DISPLAY);
		}
		if (state.getState() == EnumState.MENU) {
			builder.setLargeImage(AllTheTweaks.ATM,AllTheTweaks.DISPLAY);
			builder.setSmallImage("mojang", "(c)");
		}
		if (state.getState() == EnumState.OVERWORLD) {
			builder.setLargeImage(AllTheTweaks.ATM,AllTheTweaks.DISPLAY);
			builder.setSmallImage("overworld", "In the Overworld");
		}
		if (state.getState() == EnumState.NETHER) {
			builder.setLargeImage(AllTheTweaks.ATM,AllTheTweaks.DISPLAY);
			builder.setSmallImage("nether", "In the Nether");
		}
		if (state.getState() == EnumState.END) {
			builder.setLargeImage(AllTheTweaks.ATM,AllTheTweaks.DISPLAY);
			builder.setSmallImage("end", "In the End");
		}
		if (state.getState() == EnumState.MINING) {
			builder.setLargeImage(AllTheTweaks.ATM,AllTheTweaks.DISPLAY);
			builder.setSmallImage("overworld", "In The Mining Dimension");
		}
		if (state.getState() == EnumState.OTHER) {
			builder.setLargeImage(AllTheTweaks.ATM,AllTheTweaks.DISPLAY);
			builder.setSmallImage("nether", "In the Other");
		}
		try {
			CLIENT.sendRichPresence(builder.build());
		} catch (final Exception ex) {
			try {
				CLIENT.connect();
				errorCount = 0;
				CLIENT.sendRichPresence(builder.build());
			} catch (final Exception ex2) {
				try {
					CLIENT.close();
				} catch (final Exception ex3) {
				}
				errorCount++;
				if (errorCount > 10) {
					stop();
				}
			}
		}
	}
	
	public static boolean isEnabled() {
		return isEnabled;
	}
	
	public static State getCurrent() {
		return currentState;
	}



	public static class State {
		
		private final EnumState state;
		private final String replace;
		
		public State(EnumState state) {
			this(state, "");
		}
		
		public State(EnumState state, String replace) {
			this.state = state;
			this.replace = replace;
		}
		
		public EnumState getState() {
			return state;
		}
		
		public String getReplace() {
			return replace;
		}
	}
	
	public static enum EnumState {
		
		STARTUP("Starting Minecraft", AllTheTweaks.ATM,AllTheTweaks.DISPLAY),
		MENU("Main Menu", AllTheTweaks.ATM,AllTheTweaks.DISPLAY),
		OVERWORLD("Dimension: Overworld", AllTheTweaks.ATM,AllTheTweaks.DISPLAY),
		NETHER("Dimension: The Nether", AllTheTweaks.ATM,AllTheTweaks.DISPLAY),
		END("Dimension: The End", AllTheTweaks.ATM,AllTheTweaks.DISPLAY),
		MINING("Dimension: Mining",AllTheTweaks.ATM,AllTheTweaks.DISPLAY),
		OTHER("Dimension: The Other",AllTheTweaks.ATM,AllTheTweaks.DISPLAY),
		DIM("Dimension: %s", AllTheTweaks.ATM,AllTheTweaks.DISPLAY);

		
		private final String message, imagename, imagekey;
		
		private EnumState(String message, String imagename, String imagekey) {
			this.message = message;
			this.imagename = imagename;
			this.imagekey = imagekey;
		}
		
		public String getMessage(String replace) {
			return message.replace("%s", replace);
		}
		
		public String getImageName(String replace) {
			return imagename.replace("%s", replace);
		}
		
		public String getImageKey() {
			return imagekey;
		}
	}

	public static void setDimension(DimensionType dimension) {
		
	}

	public static void setDimension(String path) {

		setState(getStateFromDimension(path));
	}

	private static State getStateFromDimension(String path) {
		
			//func_236063_b_ = .getType()

			switch (path) {
			case "the_nether":
				return new State(EnumState.NETHER);
			case "overworld":
				return new State(EnumState.OVERWORLD);
			case "the_end":
				return new State(EnumState.END);
			case "mining":
				return new State(EnumState.MINING);
			case "the_other":
				return new State(EnumState.OTHER);

				default:
				return new State(EnumState.DIM, path);
			}
	}
	


}