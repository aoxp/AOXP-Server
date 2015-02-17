package com.ao.model.map.area;

import com.ao.model.map.Heading;
import com.ao.model.map.Position;
import com.ao.model.map.WorldMap;

public class AreaInfo {
	/*
	 * For a better understanding of areas, please refer to
	 * https://github.com/aoxp/AOXP-Server/wiki/Areas-for-dummies
	 */

	/*
	 * Area size is 9, since that's the larger number that can cover a 100x100
	 * map using less than 16 bits (a VB6 integer)
	 */
	public static final int AREA_SIZE = 9;

	private static final int[] AREAS_RECEIVE;

	static {
		// This assumes maps will always be square
		AREAS_RECEIVE = new int[WorldMap.MAP_WIDTH / AreaInfo.AREA_SIZE + 1];

		for (int i = 1; i <= AREAS_RECEIVE.length; i++) {
			// Set the i bit + the i-1 bit + the i+1 bit (if they exist)
			AREAS_RECEIVE[i] = (1 << i) | (i != 0 ? 1 << (i - 1) : 0)
					| (i != AREAS_RECEIVE.length ? 1 << (i + 1) : 0);
		}
	}

	private int belongsX;
	private int belongsY;

	private int receivesX;
	private int receivesY;

	private int minX;
	private int minY;

	private int id;

	/**
	 * @param belongsX
	 * @param belongsY
	 * @param receivesX
	 * @param receivesY
	 * @param minX
	 * @param minY
	 * @param id
	 */
	private AreaInfo(final int belongsX, final int belongsY,
			final int receivesX, final int receivesY, final int minX,
			final int minY, final int id) {
		super();
		this.belongsX = belongsX;
		this.belongsY = belongsY;
		this.receivesX = receivesX;
		this.receivesY = receivesY;
		this.minX = minX;
		this.minY = minY;
		this.id = id;
	}

	public static int getAreaIdForPos(final Position pos) {
		return getAreaIdForPos(pos.getX(), pos.getY());
	}

	public static int getAreaIdForPos(final int x, final int y) {
		return (x / AREA_SIZE + 1) * (y / AREA_SIZE + 1);
	}

	public void changeCurrentAreTowards(final Heading heading) {
		switch (heading) {
		case NORTH:
			minY -= AREA_SIZE;
			break;

		case SOUTH:
			minY += AREA_SIZE;
			break;

		case WEST:
			minX += AREA_SIZE;
			break;

		case EAST:
			minX += AREA_SIZE;
			break;
		}

		// Update area data
		id = getAreaIdForPos(minX, minY);

		final int bitX = minX / AREA_SIZE + 1;
		receivesX = AREAS_RECEIVE[bitX];
		belongsX = 1 << bitX;

		final int bitY = minY / AREA_SIZE + 1;
		receivesY = AREAS_RECEIVE[bitY];
		belongsY = 1 << bitY;
	}

	public boolean isInSameArea(final Position position) {
		return id == AreaInfo.getAreaIdForPos(position);
	}

	public int getMinX() {
		return minX;
	}

	public int getMinY() {
		return minY;
	}
}