package org.spring.demo;

public enum Fruits {
  PEAR("pear"){
    @Override
    public String taste() {
      return "pear sweet";
    }
  },
  APPLE("apple"){
    @Override
    public String taste() {
      return "apple delicious";
    }
  },
  BANANA("banana"){
    @Override
    public String taste() {
      return "banana soft glutinous";
    }
  };
  private String type;

  Fruits(String type) {
    this.type = type;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public abstract String taste();
}
