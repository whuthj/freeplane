package org.docear.plugin.bibtex.listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.TreeMap;


import net.sf.jabref.BibtexDatabase;
import net.sf.jabref.BibtexEntry;
import net.sf.jabref.export.DocearReferenceUpdateController;

import org.docear.plugin.bibtex.ReferencesController;
import org.freeplane.features.map.INodeSelectionListener;
import org.freeplane.features.map.MapModel;
import org.freeplane.features.map.NodeModel;

public class MapViewListener implements MouseListener, INodeSelectionListener {
	
	private void handleEvent() {
		ReferencesController referencesController = ReferencesController.getController();
		
		if (referencesController.getInChange() != null) {
			referencesController.setInChange(null);
			ReferencesController.getController().getJabrefWrapper().getBasePanel().runCommand("save");
		}
		
		if (referencesController.getInAdd() != null) {
			addToNodes(referencesController.getInAdd());
			referencesController.setInAdd(null);			
			ReferencesController.getController().getJabrefWrapper().getBasePanel().runCommand("save");
		}
		
		BibtexEntry[] selectedEntries = ReferencesController.getController().getJabrefWrapper().getBasePanel().getSelectedEntries(); 
		if (selectedEntries != null && selectedEntries.length == 1) {
			BibtexEntry entry = selectedEntries[0];
			if (entry.getCiteKey()!=null && entry.getCiteKey().length()>0) {
				return;
			}
			String author = entry.getField("author");  
			if (author != null && author.length()>0) {
				ReferencesController.getController().getJabRefAttributes().generateBibtexEntry(entry);
			}
		}
	}

	private void addToNodes(MapModel mapModel) {
		DocearReferenceUpdateController.lock();
		
		BibtexDatabase database = ReferencesController.getController().getJabrefWrapper().getDatabase();
		
		TreeMap<String, BibtexEntry> entryNodeTuples = new TreeMap<String, BibtexEntry>();
				
		for (BibtexEntry entry : database.getEntries()) {
			String s = entry.getField("docear_add_to_node");
			if (s != null) {
				String[] nodeIds = s.split(",");
				for (String nodeId : nodeIds) {
					entryNodeTuples.put(nodeId, entry);
					NodeModel node = mapModel.getNodeForID(nodeId);
					if (node != null) {
						ReferencesController.getController().getJabRefAttributes().setReferenceToNode(entry, node);
					}					
				}
			}
			entry.setField("docear_add_to_node", null);
		}		
				
		DocearReferenceUpdateController.unlock();
	}
	
	public void mouseClicked(MouseEvent e) {
		handleEvent();
	}

	public void mousePressed(MouseEvent e) {
	}


	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}


	public void mouseExited(MouseEvent e) {
	}


	public void onDeselect(NodeModel node) {
	}


	public void onSelect(NodeModel node) {
		handleEvent();
	}

}