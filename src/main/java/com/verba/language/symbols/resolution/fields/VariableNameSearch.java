package com.verba.language.symbols.resolution.fields;

import com.verba.language.expressions.categories.TypeDeclarationExpression;
import com.verba.language.expressions.categories.TypedExpression;
import com.verba.language.symbols.table.entries.SymbolTableEntry;
import com.verba.language.symbols.table.tables.ScopedSymbolTable;

/**
 * Created by sircodesalot on 14/9/13.
 */
public class VariableNameSearch {
  private final ScopedSymbolTable startingScope;
  private final SymbolTableEntry resolvedEntry;
  private boolean isClosedOver;

  public VariableNameSearch(ScopedSymbolTable startingScope, String name) {
    this.startingScope = startingScope;
    this.resolvedEntry = findEntryRecursive(startingScope, name);
    this.isClosedOver = determineIsClosedOver();
  }

  private SymbolTableEntry findEntryRecursive(ScopedSymbolTable startingTable, String name) {
    if (startingTable.containsKey(name)) {
      return startingTable.get(name).single();
    } else {
      if (startingTable.hasParentTable()) {
        return findEntryRecursive(startingTable.parent(), name);
      } else {
        return null;
      }
    }
  }

  private boolean determineIsClosedOver() {
    if (resolvedEntry == null) {
      return false;
    }

    return (startingScope != resolvedEntry.table());
  }

  private TypeDeclarationExpression getTypeFromSymbolEntry(SymbolTableEntry entry) {
    TypedExpression instance = (TypedExpression)entry.instance();

    if (instance.hasTypeConstraint()) {
      return instance.typeDeclaration();
    } else {
      VariableTypeResolutionMetadata type = entry.metadata().ofType(VariableTypeResolutionMetadata.class).single();
      return type.symbolType();
    }
  }

  public boolean isClosedOver() { return isClosedOver; }
  public boolean nameResolved() { return resolvedEntry != null; }
  public SymbolTableEntry resolvedEntry() { return resolvedEntry; }
  public TypeDeclarationExpression resolvedType() { return getTypeFromSymbolEntry(resolvedEntry); }



}



