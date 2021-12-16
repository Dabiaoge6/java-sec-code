package org.spring.demo;

public class AttackResult {

  private boolean lessonCompleted;
  private String feedback;
  private String output;

  public AttackResult(boolean lessonCompleted, String feedback, String output) {
    this.lessonCompleted = lessonCompleted;
    this.feedback = feedback;
    this.output = output;
  }

  public boolean assignmentSolved() {
    return lessonCompleted;
  }

  public boolean isLessonCompleted() {
    return lessonCompleted;
  }

  public void setLessonCompleted(boolean lessonCompleted) {
    this.lessonCompleted = lessonCompleted;
  }

  public String getFeedback() {
    return feedback;
  }

  public void setFeedback(String feedback) {
    this.feedback = feedback;
  }

  public String getOutput() {
    return output;
  }

  public void setOutput(String output) {
    this.output = output;
  }
}
