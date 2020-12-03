package dev.dankom.core.rank.format;

public class RankFormat {
    private String text;
    private String replace;

    public RankFormat(String text, String replace) {
        this.text = text;
        this.replace = replace;
    }

    public String format(String rankDisplay) {
        String out = rankDisplay;
        out.replaceAll(getText(), getReplace());
        return out;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getReplace() {
        return replace;
    }

    public void setReplace(String replace) {
        this.replace = replace;
    }
}
