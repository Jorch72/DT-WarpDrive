package cr0s.warpdrive.data;

import java.util.UUID;

import cr0s.warpdrive.block.movement.TileEntityShipCore;

public class StarMapEntry extends GlobalPosition {
	public StarMapEntryType type;
	public UUID uuid;
	public int maxX, maxY, maxZ;
	public int minX, minY, minZ;
	public int volume;
	public double isolationRate = 0.0D;
	public String name = "default";
	
	public enum StarMapEntryType {
		UNDEFINED(0),
		SHIP(1),		// a ship core
		JUMPGATE(2),	// a jump gate
		PLANET(3),		// a planet (a transition plane allowing to move to another dimension)
		STAR(4),		// a star
		STRUCTURE(5),	// a structure from WorldGeneration (moon, asteroid field, etc.)
		WARP_ECHO(6);	// remains of a warp
		
		private final int code;
		
		StarMapEntryType(int code) {
			this.code = code;
		}
		
		public int getType() {
			return code;
		}
	}
	
	public StarMapEntry(
			final StarMapEntryType type, final UUID uuid,
			final int dimensionId, final int x, final int y, final int z,
			final int maxX, final int maxY, final int maxZ,
			final int minX, final int minY, final int minZ,
			final int volume, final double isolationRate,
			final String name) {
		super(dimensionId, x, y, z);
		this.type = type;
		this.uuid = uuid;
		this.maxX = maxX;
		this.maxY = maxY;
		this.maxZ = maxZ;
		this.minX = minX;
		this.minY = minY;
		this.minZ = minZ;
		this.volume = volume;
		this.isolationRate = isolationRate;
		this.name = name;
	}
	
	public StarMapEntry(TileEntityShipCore core) {
		this(
			StarMapEntryType.SHIP, core.uuid,
			core.getWorldObj().provider.dimensionId, core.xCoord, core.yCoord, core.zCoord,
			core.maxX, core.maxY, core.maxZ,
			core.minX, core.minY, core.minZ,
			core.shipMass, core.isolationRate,
			core.shipName);
	}
	
	@Override
	public int hashCode() {
		return dimensionId << 24 + (x >> 10) << 12 + y << 10 + (z >> 10);
	}
}