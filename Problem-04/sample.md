# Markdown README Reference

This file is a reusable reference for writing README.md files using Markdown.

---

## Headings

# Heading 1
## Heading 2
### Heading 3
#### Heading 4
##### Heading 5
###### Heading 6

---

## Text Formatting

**Bold text**
*Italic text*
***Bold and Italic text***
~~Strikethrough text~~

---

## Lists

### Unordered List
- Item One
- Item Two
  - Sub Item
  - Sub Item

### Ordered List
1. First
2. Second
3. Third

---

## Links

[GitHub](https://github.com)

---

## Images

![Alt Text](image-url.png)

---

## Inline Code

Use `git status` to check status

---

## Bash Code Block

```bash
git status
git add .
git commit -m "message"
git push
```

---

## Java Code Block

```java
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World");
    }
}
```

---

## Other Code Blocks

```json
{
  "name": "project"
}
```

```html
<h1>Hello</h1>
```

```css
body {
  background-color: white;
}
```

---

## Blockquote

> This is a blockquote

---

## Horizontal Line

---

## Table

| Name | Tech | Status |
|-----|------|--------|
| App | Angular | Done |
| API | Spring | In Progress |

---

## Task List

- [x] Setup project
- [ ] Write README
- [ ] Deploy

---

## Emojis

🚀 ✅ ❌ 📌 🛠️✔️

---

## Escaping Characters

\*Not italic\*
\# Not a heading

---

## Folder Structure

```
src/
 ├── main/
 └── test/
```

---

## Collapsible Section

<details>
<summary>Click to expand</summary>

Hidden content here

</details>

---

## README Template

# Project Name

## Description
Project description goes here

## Technologies
- Java
- Spring Boot
- Angular

## How to Run

```bash
npm install
ng serve
```

## Author
Your Name
