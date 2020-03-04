/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author
 * @version 1.0
 */

class Sorceress extends Hero
{
	private static final int MIN_ADD = 25;
	private static final int MAX_ADD = 50;

	//-----------------------------------------------------------------
	public Sorceress()
	{
		super("Sorceress", 75, 5, .7, 25, 50, .3," casts a spell of fireball at ", new SpecialCureWounds(MIN_ADD, MAX_ADD));
	}
}