package uk.co.grahamcox.dirt.webapp;

/**
 * Message to supply a greeting with
 */
public class GreetingMessage {
    /** The greeting to say greeting to */
    private String greeting;

    /**
     * Get the greeting
     * @return the greeting
     */
    public String getGreeting() {
        return greeting;
    }

    /**
     * Set the greeting
     * @param greeting the greeting
     */
    public void setGreeting(final String greeting) {
        this.greeting = greeting;
    }
}
