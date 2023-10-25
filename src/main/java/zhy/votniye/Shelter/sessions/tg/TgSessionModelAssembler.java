package zhy.votniye.Shelter.sessions.tg;

import zhy.votniye.Shelter.models.domain.Owner;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TgSessionModelAssembler {

    private Owner owner;
    private final Pattern textPattern = Pattern.compile("[А-я\\w\\s-]+");
    private final Pattern emailPattern = Pattern.compile("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&’*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$");

    /**
     * Sets data to object's field corresponding to current step of the state.
     * Calling {@link #compareToPattern(Pattern, String)} with corresponding pattern if needed.
     *
     * @param data
     * @param step
     * @return 1 if step succeeded, -1 in case of failure, 0 if last step succeeded.
     */
    public byte updateOwner(String data, int step) {
        switch (step) {
            case 1:
                owner = new Owner();

                if (compareToPattern(textPattern, data)) {
                    owner.setLastName(data);
                    return 1;
                } else return -1;
            case 2:
                if (compareToPattern(textPattern, data)) {
                    owner.setFirstName(data);
                    return 1;
                } else return -1;
            case 3:
                if (compareToPattern(textPattern, data)) {
                    owner.setMiddleName(data);
                    return 1;
                } else return -1;
            case 4:
                try {
                    owner.setPhoneNumber(Long.parseLong(data.replaceAll("\\D", "")));
                } catch (NumberFormatException e) {
                    return -1;
                }
                return 1;
            case 5:
                owner.setAddress(data);
                return 1;
            case 6:
                if (compareToPattern(emailPattern, data)) {
                    owner.setEmail(data);
                    return 1;
                } else return -1;
            case 7:
                owner.setComment(data);
                return 0;
            default:
                throw new IllegalStateException("Unexpected value on step " + step);
        }
    }

    public Owner getOwner() {
        return owner;
    }

    public void destroy() {
        owner = null;
    }

    /**
     * Compares provided string to provided pattern.
     * @param pattern
     * @param data
     * @return true if string matches pattern, false if not.
     */
    private boolean compareToPattern(Pattern pattern, String data) {
        if (data == null || data.isEmpty()) return false;
        Matcher matcher = pattern.matcher(data);
        return matcher.matches();
    }
}
