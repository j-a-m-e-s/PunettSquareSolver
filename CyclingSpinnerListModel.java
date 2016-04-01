import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.SpinnerListModel;

/*
 * Creates a spinner that loops from A to Z.
 * Also skips characters used by the other spinners.
 */
public class CyclingSpinnerListModel extends SpinnerListModel {
	/*
	 * Keeps track of the first and last values of the list
	 */
	Object firstValue, lastValue;
	private static final int MaxParent = 2;
	/*
	 * Button
	 */
	JButton[][] myLinkedButton;
	JSlider[] myLinkedSlider;
	char[] myCharUsedList;
	int currentSpinnerList;

	/*
	 * Constructor method. Calls parent to actually create the object. Saves the
	 * first value and last value for wraparound;
	 * 
	 * @param values list of object (String) displayed in the spinner.
	 */
	public CyclingSpinnerListModel(Object[] values) {
		super(values);
		firstValue = values[0];
		lastValue = values[values.length - 1];
	}

    /*
     * save the buttons linked to this Spinner.
     * @param LinkedButton	List of Button associated with Spinner.
     */
    public void setLinkedButtons(JButton[][] LinkedButton){
    	myLinkedButton = LinkedButton;
    }
    
	/*
	 * save set of Spinners.
	 * 
	 * @param LinkedSlider set of sliders that are linked together.
	 */
	public void setLinkedSlider(JSlider[] LinkedSlider) {
		myLinkedSlider = LinkedSlider;
	}

	/*
	 * set the current Spinner index
	 * 
	 * @param i Index of slider
	 */
	public void setCurrentSpinnerList(int i) {
		currentSpinnerList = i;
	}

	/*
	 * set the list of characters used by the spinners.
	 * 
	 * @param charUsedLIst Stores character used in each slider
	 */
	public void setCharUsedList(char[] charUsedList) {
		myCharUsedList = charUsedList;
	}

	/*
	 * Overloads SpinnerListModel#getNextValue() Get next value. If null go to
	 * head of list Check to see if the character is being used. If it is, get
	 * next value. set corresponding character in character used.
	 * 
	 * @return object to be in the spinner (non-Javadoc)
	 * 
	 * @see javax.swing.SpinnerListModel#getNextValue()
	 */
	public Object getNextValue() {
		Object value;
		do {
			value = super.getNextValue();
			if (value == null) {
				value = firstValue;
			}
		} while (inUsed((String) value, currentSpinnerList));
		setLinkedButton((String) value);
		return value;
	}

	/*
	 * Overloads SpinnerListModel#getPreviousValue() Get previous value. If null
	 * go to end of list Check to see if the character is being used. If it is,
	 * get previous value. set corresponding character in character used.
	 * 
	 * @return object to be in the spinner (non-Javadoc)
	 * 
	 * @see javax.swing.SpinnerListModel#getPreviousValue()
	 */
	public Object getPreviousValue() {
		Object value;
		do {
			value = super.getPreviousValue();
			if (value == null) {
				value = lastValue;
			}
		} while (inUsed((String) value, currentSpinnerList));
		setLinkedButton((String) value);
		return value;
	}

	/*
	 * check if the current character is being used by other spinner. set the
	 * current spinner's character so current character can be reused.
	 * 
	 * @param value String of spinner
	 * 
	 * @param index index of current spinner
	 * 
	 * @return true if value is already being used false otherwise.
	 */
	private boolean inUsed(String value, int index) {
		for (int i = 0; i < myCharUsedList.length; i++) {
			if (value.charAt(0) == myCharUsedList[i]) {
				super.setValue(value);
				myCharUsedList[index] = value.charAt(0);
				return true;
			}
		}
		myCharUsedList[index] = value.charAt(0);
		return false;
	}

	/*
	 * set the text in the linked button to reflect current spinner state. Need
	 * to keep upper or lower case Consistency,
	 * 
	 * @param value String to be put into buttons.
	 */
	private void setLinkedButton(String value) {
		for (int i = 0; i < MaxParent; i++)
			for (int j = 0; j < 2; j++) {
				if (Character.isUpperCase(myLinkedButton[i][j].getText().charAt(0)))
					myLinkedButton[i][j].setText(value);
				else {
					String str = new String(value);
					myLinkedButton[i][j].setText(str.toLowerCase());
				}
			}

	}
}
