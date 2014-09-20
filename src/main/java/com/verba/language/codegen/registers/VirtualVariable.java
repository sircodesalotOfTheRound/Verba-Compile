package com.verba.language.codegen.registers;

/**
 * Created by sircodesalot on 14/9/19.
 */
public class VirtualVariable {
  public final int variableNumber;
  public String type;

  public VirtualVariable(int variableNumber, String type) {
    this.variableNumber = variableNumber;
    this.type = type;
  }

  public int variableNumber() { return this.variableNumber; }
  public String type() { return this.type; }
}
