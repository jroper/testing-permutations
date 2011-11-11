package net.vz.jroper.testingpermutations.customsuite;

import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.ParentRunner;
import org.junit.runners.model.InitializationError;

import java.util.List;

/**
 * A named parent runner, with a list of children
 */
public class NamedParentRunner extends ParentRunner<Runner> {
    private final List<Runner> runners;
    private final String name;

    protected NamedParentRunner(Class<?> klass, List<Runner> runners, String name) throws InitializationError {
        super(klass);
        this.runners = runners;
        this.name = name;
    }

    protected List<Runner> getChildren() {
        return runners;
    }

    protected Description describeChild(Runner child) {
        return child.getDescription();
    }

    protected void runChild(Runner child, RunNotifier notifier) {
        child.run(notifier);
    }

    protected String getName() {
        return name;
    }
}