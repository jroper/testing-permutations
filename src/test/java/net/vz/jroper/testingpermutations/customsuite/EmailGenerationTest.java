package net.vz.jroper.testingpermutations.customsuite;

import net.vz.jroper.testingpermutations.Language;
import net.vz.jroper.testingpermutations.NotificationType;
import net.vz.jroper.testingpermutations.Platform;
import net.vz.jroper.testingpermutations.Variant;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.Suite;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.fail;

@RunWith(EmailGenerationTest.EmailGenerationRunner.class)
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


    public static class EmailGenerationRunner extends Suite {
        public EmailGenerationRunner(Class<?> klass) throws InitializationError {
            super(klass, createChildren(klass));
        }

        private static List<Runner> createChildren(Class<?> klass) throws InitializationError {
            List<Runner> variants = new ArrayList<Runner>();
            for (Variant variant : Variant.values()) {
                List<Runner> types = new ArrayList<Runner>();
                for (NotificationType type : NotificationType.values()) {
                    List<Runner> platforms = new ArrayList<Runner>();
                    for (Platform platform : Platform.values()) {
                        List<Runner> languages = new ArrayList<Runner>();
                        for (Language language : Language.values()) {
                            languages.add(new TestRunner(klass, variant, type, platform, language));
                        }
                        platforms.add(new NamedParentRunner(klass, languages, platform.name()));
                    }
                    types.add(new NamedParentRunner(klass, platforms, type.name()));
                }
                variants.add(new NamedParentRunner(klass, types, variant.name()));
            }
            return variants;
        }
    }

    private static class TestRunner extends BlockJUnit4ClassRunner {
        private final Variant variant;
        private final NotificationType type;
        private final Platform platform;
        private final Language language;

        private TestRunner(Class<?> klass, Variant variant, NotificationType type,
                           Platform platform, Language language) throws InitializationError {
            super(klass);
            this.variant = variant;
            this.type = type;
            this.platform = platform;
            this.language = language;
        }

        public Object createTest() throws Exception {
            return new EmailGenerationTest(variant, type, platform, language);
        }

        protected String getName() {
            return language.name();
        }

        protected String testName(final FrameworkMethod method) {
            return String.format(method.getName() + "[%s-%s-%s-%s]",
                    variant.name(), type.name(), platform.name(), language.name());
        }

        protected void validateConstructor(List<Throwable> errors) {
        }

        protected Statement classBlock(RunNotifier notifier) {
            return childrenInvoker(notifier);
        }
    }
}
