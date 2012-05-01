/*
    AO-XP Server (XP stands for Cross Platform) is a Java implementation of Argentum Online's server 
    Copyright (C) 2009 Juan Martín Sotuyo Dodero. <juansotuyo@gmail.com>

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

package com.ao.model.message;

/**
 * Defines available server messages.
 */
public enum Message {
    DONT_SEE_ANYTHING,
    NPC_SWING,
    NPC_KILL_USER,
    BLOCKED_WITH_SHIELD_USER,
    BLOCKED_WITH_SHIELD_OTHER,
    USER_SWING,
    SAFE_MODE_ON,
    SAFE_MODE_OFF,
    RESUSCITATION_SAFE_OFF,
    RESUSCITATION_SAFE_ON,
    NOBILITY_LOST,
    CANT_USE_WHILE_MEDITATING,
    NPC_HIT_USER,
    USER_HIT_NPC,
    USER_ATTACKED_SWING,
    USER_HITTED_BY_USER,
    USER_HITTED_USER,
    WORK_REQUEST_TARGET,
    HAVE_KILLED_USER,
    USER_KILL,
    EARN_EXP,
    HOME,
    CANCEL_HOME,
    FINISH_HOME;
	
	/**
	 * The amount of existing messages.
	 */
	public static final int AMOUNT = Message.values().length;
	public static final Message[] VALUES = Message.values();
}
