package imecontroller;


import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

import javax.swing.event.ListSelectionListener;


/**
 * This interface extends the IMEController interface as to abide to SOLID principles, and is
 * also a type of ActionListener, ItemListener, and ListSelectionListener.
 */
public interface IMEGUIController extends IMEController, ActionListener,
        ItemListener, ListSelectionListener {

}
