package net.vz.jroper.testingpermutations;

import net.vz.jroper.testingpermutations.data.GruschelData;
import net.vz.jroper.testingpermutations.data.MessageData;
import org.jsoup.nodes.Document;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 * A type of notification that VZ sends
 */
public enum NotificationType {

  NEW_MESSAGE(new MessageData("Test Subject", "Test content")) {
    public void runAssertions(Document body) {
      assertThat("Test Subject", equalTo(body.getElementById("subject").text()));
      assertThat("Test content", equalTo(body.getElementById("content").text()));
    }
  },

  GRUSCHEL(new GruschelData());

  // ... of course, we actually do support more notification types

  public final Object testData;

  NotificationType(Object testData) {
    this.testData = testData;
  }

  public void runAssertions(Document body) {
  }
}