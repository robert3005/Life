package life;

import java.util.Observable;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ChangeValue extends Observable implements ChangeListener {

	public void stateChanged(final ChangeEvent expn) {
		final JSlider source = (JSlider) expn.getSource();
		if (!source.getValueIsAdjusting()) {
			setChanged();
			notifyObservers(source.getValue());
		}
	}
}