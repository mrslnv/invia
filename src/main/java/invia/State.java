package invia;

public class State {
    private static final int YOUNGER_CHILD = 3;
    private boolean adultsOK = false;
    private boolean child3OK = false;
    private boolean child6OK = false;
    public String string6;
    public String string3;
    public String stringAdult;
    private static final int OLDER_CHILD = 8;

    public boolean isAdultOk(){
        return adultsOK;
    }

    public void adultsBooked(String text){
        adultsOK = true;
        stringAdult = text;
    }

    public boolean isChildOk(){
        return child3OK && child6OK;
    }

    public boolean childOk(String limit, String text){
        int num = Integer.parseInt(limit);
        if (num >= OLDER_CHILD)
            if (tryBook6()) {
                string6 = text;
                return true;
            }else
                if (tryBook3()) {
                    string3 = text;
                    return true;
                }
        if (num >= YOUNGER_CHILD)
            if (tryBook3()) {
                string3 = text;
                return true;
            }
        return false;
    }

    private boolean tryBook6() {
        if (!child6OK) {
            child6OK = true;
            return true;
        }
        return false;
    }
    private boolean tryBook3() {
        if (!child3OK) {
            child3OK = true;
            return true;
        }
        return false;
    }

    public boolean isOk(){
        return adultsOK && child6OK && child3OK;
    }

    public void reset() {
        adultsOK = false;
        child3OK = false;
        child6OK = false;
        string3 = "";
        string6 = "";
        stringAdult = "";
    }
}
