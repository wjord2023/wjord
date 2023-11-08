# C语言备忘录

```C
#include <stdio.h>
#include <string.h>

int main() {
    char studentID[9];
    char password[7];

    fgets(studentID, sizeof(studentID), stdin);
    size_t len = strlen(studentID);
    if (len > 0 && studentID[len - 1] == '\n') {
        studentID[len - 1] = '\0';
        len--;
    }
    size_t start = len - 6;
    strncpy(password, &studentID[start], 6);
    password[6] = '\0';
    printf("%s\n", password);
    return 0;
}

```

