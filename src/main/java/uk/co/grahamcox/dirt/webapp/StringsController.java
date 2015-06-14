package uk.co.grahamcox.dirt.webapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

/**
 * Controller to load the appropriate strings for the user
 */
@Controller
@RequestMapping("/resources/messages/messages")
public class StringsController {
    /** The logger to use */
    private static final Logger LOG = LoggerFactory.getLogger(StringsController.class);

    /** The base for serving strings files from */
    private final String stringsBase;

    /** The list of locales that we support */
    private final List<String> locales;

    /**
     * Construct the controller
     * @param stringsBase the base to serve strings files from
     * @param localesFile the file listing the supported locales
     * @throws IOException if an error occurs reading the locales file
     */
    public StringsController(final String stringsBase, final Resource localesFile) throws IOException {
        this.stringsBase = stringsBase;

        locales = new ObjectMapper().readValue(localesFile.getInputStream(), List.class);
    }

    /**
     * Get the messages file for the given locale
     * @param locale the locale to get the messages file for
     * @return the messages file
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getMessagesFile(final Locale locale) {
        String stringsFile = Stream.of(
            locale.getLanguage() + "_" + locale.getCountry(),
            locale.getLanguage())
            .filter(locales::contains)
            .findFirst()
            .map(selectedLocale -> stringsBase + "/strings_" + selectedLocale + ".json")
            .orElseGet(() -> stringsBase + "/strings.json");
        LOG.debug("Selected strings file {} for locale {}", stringsFile, locale);
        return stringsFile;
    }
}
