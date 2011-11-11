package net.vz.jroper.testingpermutations.data;

/**
 * New message data
 */
public class MessageData {
    private final String subject;
    private final String content;

    public MessageData(String subject, String content) {
        this.subject = subject;
        this.content = content;
    }

    public String getSubject() {
        return subject;
    }

    public String getContent() {
        return content;
    }
}
