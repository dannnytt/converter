package org.example.converter;

public class ADTControl {

    private int pin = 10;
    private int pout = 16;
    private static final int accuracy = 10;

    public History history = new History();
    public Editor editor = new Editor();

    public enum State { EDIT, CONVERTED };
    private State state;

    public int getPin() {
        return pin;
    }

    public void setPin(int value) {
        pin = value;
    }

    public int getPout() {
        return pout;
    }

    public void setPout(int value) {
        pout = value;
    }

    public State getState() {
        return state;
    }

    public void setState(State value) {
        state = value;
    }

    public ADTControl() {
        setPin(pin);
        setPout(pout);
        setState(State.EDIT);
    }

    public String doCommand(Editor.Commands command) {

        if (command == Editor.Commands.exec) {
            double r = ADTConvertP10.dval(editor.getNumber(), getPin());
            String res = ADTConvert10P.convertToBase(r, getPout(), acc());
            setState(State.CONVERTED);
            history.addRecord(getPin(), getPout(), editor.getNumber(), res);
            return res;
        } else {
            setState(State.EDIT);
            return editor.doEdit(command);
        }
    }

    private int acc() {
        return (int) Math.round(editor.acc() * Math.log(getPin()) / Math.log(getPout()) + 0.5);
    }

}
