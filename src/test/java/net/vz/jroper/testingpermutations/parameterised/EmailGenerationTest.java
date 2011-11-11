package net.vz.jroper.testingpermutations.parameterised;

import net.vz.jroper.testingpermutations.Language;
import net.vz.jroper.testingpermutations.NotificationType;
import net.vz.jroper.testingpermutations.Platform;
import net.vz.jroper.testingpermutations.Variant;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import static org.junit.Assert.fail;

@RunWith(Parameterized.class)
public class EmailGenerationTest {
    private final Variant variant;
    private final NotificationType type;
    private final Platform platform;
    private final Language language;

    public EmailGenerationTest(Variant variant, NotificationType type, Platform platform, Language language) {
        this.variant = variant;
        this.type = type;
        this.platform = platform;
        this.language = language;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> generateParameters() {
        Collection<Object[]> params = new ArrayList<Object[]>();
        for (Variant variant : Variant.values()) {
            for (NotificationType type : NotificationType.values()) {
                for (Platform platform : Platform.values()) {
                    for (Language language : Language.values()) {
                        params.add(new Object[]{variant, type, platform, language});
                    }
                }
            }
        }
        return params;
    }

    @Test
    public void noResourceKeysShouldBeMissing() {
        // Fail randomly, so we can see colourful examples of what things look like
        if (rand.nextInt(9) == 1) {
            fail();
        }
    }

    @Test
    public void notificationSpecificAssertionsShouldPass() {
        // Do nothing, this is just an example
    }

    private static final Random rand = new Random(1);
}
