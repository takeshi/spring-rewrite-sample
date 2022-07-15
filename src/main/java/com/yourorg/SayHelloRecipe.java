package com.yourorg;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.openrewrite.ExecutionContext;
import org.openrewrite.Option;
import org.openrewrite.Recipe;
import org.openrewrite.internal.lang.NonNull;
import org.openrewrite.java.JavaIsoVisitor;
import org.openrewrite.java.JavaTemplate;
import org.openrewrite.java.tree.J;

public class SayHelloRecipe extends Recipe {
    // Making your recipe immutable helps make them idempotent and eliminates categories of possible bugs
    // Configuring your recipe in this way also guarantees that basic validation of parameters will be done for you by rewrite
    @Option(displayName = "Fully Qualified Class Name",
            description = "A fully-qualified class name indicating which class to add a hello() method.",
            example = "com.yourorg.FooBar")
    @NonNull
    private final String fullyQualifiedClassName;

    public String getFullyQualifiedClassName() {
        return fullyQualifiedClassName;
    }

    // Recipes must be serializable. This is verified by RecipeTest.assertChanged() and RecipeTest.assertUnchanged()
    @JsonCreator
    public SayHelloRecipe(@NonNull @JsonProperty("fullyQualifiedClassName") String fullyQualifiedClassName) {
        this.fullyQualifiedClassName = fullyQualifiedClassName;
    }

    @Override
    public String getDisplayName() {
        return "Say Hello";
    }

    @Override
    public String getDescription() {
        return "Adds a \"hello\" method to the specified class";
    }

    // TODO: Override getVisitor() to return a JavaIsoVisitor to perform the refactoring
}
