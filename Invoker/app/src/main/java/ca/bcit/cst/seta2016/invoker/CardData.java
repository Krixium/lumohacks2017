package ca.bcit.cst.seta2016.invoker;

/**
 * CardData.java
 *
 * Description: Data model for the cards on the homepage of the application.
 */
class CardData {
    private String textTitle;
    private String textDesc;

    public CardData(String textTitle, String textDesc) {
        this.textTitle = textTitle;
        this.textDesc = textDesc;
    }

    public String getTextTitle() {
        return textTitle;
    }

    public void setTextTitle(String textTitle) {
        this.textTitle = textTitle;
    }

    public String getTextDesc() {
        return textDesc;
    }

    public void setTextDesc(String textDesc) {
        this.textDesc = textDesc;
    }
}
