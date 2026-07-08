# Module 22 - Spring AI - Output Converters - Exercise Instructions

## Exercise Overview

The AI is currently returning unstructured text, which is hard to parse in your code. You need to force the AI to return a strict JSON structure that maps to a Java Record.

---

## Prerequisites
- **Java 25**
- **Maven 3.9+**

---

## Step-by-Step Implementation Guide

### Step 1
Create a `BeanOutputConverter` for your target Record class.

### Step 2
Append the converter's format instructions to your prompt.

### Step 3
Use the converter to parse the AI's string response into a Java object.

> [!IMPORTANT]
> Ensure you compile frequently and check for syntax errors as you build out the implementation.

---

## Running the Exercise

```bash
mvn spring-boot:run
```

---

## Success Criteria

- [ ] The AI returns valid JSON matching the Record schema.
- [ ] The application successfully deserializes it into the Java object.
