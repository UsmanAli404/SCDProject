import java.util.ArrayList;
import java.util.List;

// Class to represent the UML Class
class UMLClass {
    String name; // Name of the class
    String visibility; // Visibility (public, private, protected)
    boolean isAbstract; // Indicates if the class is abstract
    List<Attribute> attributes; // List of attributes in the class
    List<Method> methods; // List of methods in the class
    List<Relationship> relationships; // Relationships with other classes

    public UMLClass(String name, String visibility, boolean isAbstract) {
        this.name = name;
        this.visibility = visibility;
        this.isAbstract = isAbstract;
        this.attributes = new ArrayList<>();
        this.methods = new ArrayList<>();
        this.relationships = new ArrayList<>();
    }

    // Add an attribute to the class
    public void addAttribute(String name, String type, String visibility, boolean isStatic, String defaultValue) {
        attributes.add(new Attribute(name, type, visibility, isStatic, defaultValue));
    }

    // Add a method to the class
    public void addMethod(String name, String returnType, String visibility, boolean isStatic, boolean isAbstract, List<Parameter> parameters) {
        methods.add(new Method(name, returnType, visibility, isStatic, isAbstract, parameters));
    }

    // Add a relationship to another class
    public void addRelationship(UMLClass targetClass, String type, String multiplicity, String navigability) {
        relationships.add(new Relationship(type, targetClass, multiplicity, navigability));
    }

    // Delete an attribute by name
    public void deleteAttribute(String name) {
        attributes.removeIf(attribute -> attribute.name.equals(name));
    }

    // Delete a method by name
    public void deleteMethod(String name) {
        methods.removeIf(method -> method.name.equals(name));
    }

    // Delete a relationship
    public void deleteRelationship(UMLClass targetClass) {
        relationships.removeIf(relationship -> relationship.targetClass.equals(targetClass));
    }

    // Edit class name
    public void setClassName(String newName) {
        this.name = newName;
    }

    // Edit class visibility
    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    // Edit class abstract status
    public void setAbstractStatus(boolean isAbstract) {
        this.isAbstract = isAbstract;
    }

    // Edit an attribute's information
    public void editAttribute(String oldName, String newName, String type, String visibility, boolean isStatic, String defaultValue) {
        for (Attribute attribute : attributes) {
            if (attribute.name.equals(oldName)) {
                attribute.name = newName;
                attribute.type = type;
                attribute.visibility = visibility;
                attribute.isStatic = isStatic;
                attribute.defaultValue = defaultValue;
            }
        }
    }

    // Edit a method's information
    public void editMethod(String oldName, String newName, String returnType, String visibility, boolean isStatic, boolean isAbstract, List<Parameter> parameters) {
        for (Method method : methods) {
            if (method.name.equals(oldName)) {
                method.name = newName;
                method.returnType = returnType;
                method.visibility = visibility;
                method.isStatic = isStatic;
                method.isAbstract = isAbstract;
                method.parameters = parameters;
            }
        }
    }

    // Edit a relationship's information
    public void editRelationship(UMLClass targetClass, String newType, String newMultiplicity, String newNavigability) {
        for (Relationship relationship : relationships) {
            if (relationship.targetClass.equals(targetClass)) {
                relationship.type = newType;
                relationship.multiplicity = newMultiplicity;
                relationship.navigability = newNavigability;
            }
        }
    }
}

// Attribute class representing fields in a UML Class
class Attribute {
    String name; // Name of the attribute
    String type; // Type of the attribute
    String visibility; // Visibility (public, private, protected)
    boolean isStatic; // If the attribute is static
    String defaultValue; // Default value of the attribute

    public Attribute(String name, String type, String visibility, boolean isStatic, String defaultValue) {
        this.name = name;
        this.type = type;
        this.visibility = visibility;
        this.isStatic = isStatic;
        this.defaultValue = defaultValue;
    }
}

// Method class representing methods in a UML Class
class Method {
    String name; // Name of the method
    String returnType; // Return type of the method
    String visibility; // Visibility (public, private, protected)
    boolean isStatic; // If the method is static
    boolean isAbstract; // If the method is abstract
    List<Parameter> parameters; // Parameters for the method

    public Method(String name, String returnType, String visibility, boolean isStatic, boolean isAbstract, List<Parameter> parameters) {
        this.name = name;
        this.returnType = returnType;
        this.visibility = visibility;
        this.isStatic = isStatic;
        this.isAbstract = isAbstract;
        this.parameters = parameters;
    }
}

// Parameter class representing parameters of a method
class Parameter {
    String name; // Name of the parameter
    String type; // Type of the parameter

    public Parameter(String name, String type) {
        this.name = name;
        this.type = type;
    }
}

// Relationship class representing relationships between classes
class Relationship {
    String type; // Type of the relationship (e.g., inheritance, association)
    UMLClass targetClass; // Target class of the relationship
    String multiplicity; // Multiplicity (e.g., 1..*, 0..1)
    String navigability; // Navigability (e.g., bidirectional, unidirectional)

    public Relationship(String type, UMLClass targetClass, String multiplicity, String navigability) {
        this.type = type;
        this.targetClass = targetClass;
        this.multiplicity = multiplicity;
        this.navigability = navigability;
    }
}

// UML Class Registry - maintains a list of all UML Classes
class UMLClassRegistry {
    List<UMLClass> classList; // List of all UML classes

    public UMLClassRegistry() {
        this.classList = new ArrayList<>();
    }

    // Add a new class to the registry
    public void addClass(UMLClass umlClass) {
        classList.add(umlClass);
    }

    // Remove a class from the registry by name
    public void deleteClass(String className) {
        classList.removeIf(umlClass -> umlClass.name.equals(className));
    }

    // Add a relationship between two classes
    public void addRelationship(String sourceClassName, String targetClassName, String relationshipType, String multiplicity, String navigability) {
        UMLClass sourceClass = findClassByName(sourceClassName);
        UMLClass targetClass = findClassByName(targetClassName);
        if (sourceClass != null && targetClass != null) {
            sourceClass.addRelationship(targetClass, relationshipType, multiplicity, navigability);
        }
    }

    // Remove a relationship between two classes
    public void deleteRelationship(String sourceClassName, String targetClassName) {
        UMLClass sourceClass = findClassByName(sourceClassName);
        UMLClass targetClass = findClassByName(targetClassName);
        if (sourceClass != null && targetClass != null) {
            sourceClass.deleteRelationship(targetClass);
        }
    }

    // Find a class by name
    public UMLClass findClassByName(String name) {
        return classList.stream()
                .filter(c -> c.name.equals(name))
                .findFirst()
                .orElse(null);
    }

    // Get the list of all classes
    public List<UMLClass> getClasses() {
        return classList;
    }
}

