package uk.co.grahamcox.dirt.webapp;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * Controller to say hello to someone
 */
@Controller
public class HelloController {
    /**
     * Say hello
     * @param message the input message
     * @return the greeting
     */
    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public GreetingMessage greeting(final HelloMessage message) {
        GreetingMessage greetingMessage = new GreetingMessage();
        greetingMessage.setGreeting("Hello, " + message.getName());
        return greetingMessage;
    }
}
