/*
 *  Freeplane - mind map editor
 *  Copyright (C) 2008 Joerg Mueller, Daniel Polansky, Christian Foltin, Dimitry Polivaev
 *
 *  This file is modified by Dimitry Polivaev in 2008.
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.freeplane.core.resources;

import java.awt.event.ActionEvent;

import org.freeplane.core.controller.Controller;
import org.freeplane.core.ui.AFreeplaneAction;
import org.freeplane.core.ui.SelectableAction;

@SelectableAction(checkOnPopup = true)
class ShowSelectionAsRectangleAction extends AFreeplaneAction {
	static final String NAME = "showSelectionAsRectangle";
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	public ShowSelectionAsRectangleAction(final Controller controller) {
		super("ShowSelectionAsRectangleAction", controller);
	}

	public void actionPerformed(final ActionEvent e) {
		ResourceController.getResourceController().toggleSelectionAsRectangle();
	}

	@Override
	public void setSelected() {
		setSelected(ResourceController.getResourceController().isSelectionAsRectangle());
	}
}