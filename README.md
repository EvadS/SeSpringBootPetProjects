# SpringBootLearning Demos and snippets
Several Boot skeletons and demo samples.
## Git recommendations 

Git commit text rules 

1. Separate the heading from the body with a blank line
2. Limit your title to 50 characters
3. Capitalize your title
4. Don't put a period at the end of the headline
5. Use an imperative in your headline
6. Go to the next line in the body at 72 characters
7.   In the body, answer the questions what and why, not how

<b>Commit</b> — это по сути <i>information about changes </i>. Как-то правильнее, с моей точки зрения, ощущается вопрос 
 * <b>«Что эти изменения делают»</b>
 * <b>«Что сделают» </b> а не «Что эти изменения сделали». Т.к. это же не фиксированная точка в истории, коммиты можно переставлять местами, сливать, переносить в другие ветки и т.д.

### Clarification 
#### Title and body 
We split the commit with a space into a header and a body.

``` 
git commit -am 'commit title

commit body message'
```
Displaying only the title line
``` 
 git log --oneline 
```
grouping commits by an author, again, for brevity, only shows the title
```
 git shortlog 
```

#### searching by commits 
```
git log --oneline -5 --author 'Evad' --before "Fri Mar 2 2020"
```

#### Заголовок

A well-formed commit header should end the following sentence

---
If applied, this commit will <заголовок коммита>
---

Например:
* If applied, this commit will <b><i> refactor subsystem X for readability</i></b>
* If applied, this commit will <b><i> update getting started documentation</i></b>
* If applied, this commit will <b><i> remove deprecated methods</i></b>
* If applied, this commit will <b><i> release version 1.0.0</i></b>

