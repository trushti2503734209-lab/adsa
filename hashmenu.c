#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define TABLE_SIZE 11
#define MAX_INPUT 256

typedef struct Node {
    char *key;
    struct Node *next;
} Node;

static Node *hash_table[TABLE_SIZE];

unsigned int hash_string(const char *str) {
    unsigned long hash = 5381;
    int c;

    while ((c = (unsigned char)*str++) != '\0') {
        hash = ((hash << 5) + hash) + c;
    }

    return (unsigned int)(hash % TABLE_SIZE);
}

static void read_line(char *buffer, size_t size) {
    if (fgets(buffer, (int)size, stdin) == NULL) {
        buffer[0] = '\0';
        return;
    }
    buffer[strcspn(buffer, "\r\n")] = '\0';
}

static Node *create_node(const char *key) {
    Node *node = malloc(sizeof(Node));
    if (node == NULL) {
        fprintf(stderr, "Memory allocation failed.\n");
        exit(EXIT_FAILURE);
    }
    node->key = strdup(key);
    if (node->key == NULL) {
        free(node);
        fprintf(stderr, "Memory allocation failed.\n");
        exit(EXIT_FAILURE);
    }
    node->next = NULL;
    return node;
}

static int contains_key(const char *key) {
    unsigned int index = hash_string(key);
    Node *current = hash_table[index];
    while (current != NULL) {
        if (strcmp(current->key, key) == 0) {
            return 1;
        }
        current = current->next;
    }
    return 0;
}

static void insert_string(const char *key) {
    if (key[0] == '\0') {
        printf("Cannot insert an empty string.\n");
        return;
    }
    if (contains_key(key)) {
        printf("String '%s' is already in the table.\n", key);
        return;
    }

    unsigned int index = hash_string(key);
    Node *node = create_node(key);
    node->next = hash_table[index];
    hash_table[index] = node;
    printf("Inserted '%s' at index %u.\n", key, index);
}

static void print_table(void) {
    printf("\nHash table contents (positions 0 through 10):\n");
    for (unsigned int i = 0; i < TABLE_SIZE; ++i) {
        printf("[%2u]:", i);
        Node *current = hash_table[i];
        if (current == NULL) {
            printf(" (empty)\n");
            continue;
        }
        while (current != NULL) {
            printf(" %s", current->key);
            if (current->next != NULL) {
                printf(" ->");
            }
            current = current->next;
        }
        printf("\n");
    }
}

static void print_ascii_codes(const char *str) {
    if (str == NULL || str[0] == '\0') {
        printf("No string provided.\n");
        return;
    }
    printf("ASCII codes for '%s':\n", str);
    for (size_t i = 0; str[i] != '\0'; ++i) {
        printf("'%c' = %u\n", str[i], (unsigned char)str[i]);
    }
    printf("\n");
}

static void free_table(void) {
    for (unsigned int i = 0; i < TABLE_SIZE; ++i) {
        Node *current = hash_table[i];
        while (current != NULL) {
            Node *next = current->next;
            free(current->key);
            free(current);
            current = next;
        }
        hash_table[i] = NULL;
    }
}

static void show_menu(void) {
    printf("\nHash Table Menu\n");
    printf("1. Insert an ASCII string\n");
    printf("2. Print hash table\n");
    printf("3. Print ASCII codes for a string\n");
    printf("4. Exit\n");
    printf("Choose an option: ");
}

int main(void) {
    char input[MAX_INPUT];
    int choice = 0;

    printf("Menu-driven Hash Table Example\n");

    while (1) {
        show_menu();
        read_line(input, sizeof(input));
        if (sscanf(input, "%d", &choice) != 1) {
            printf("Invalid option. Please enter a number between 1 and 4.\n");
            continue;
        }

        switch (choice) {
            case 1:
                printf("Enter ASCII string to insert: ");
                read_line(input, sizeof(input));
                insert_string(input);
                break;

            case 2:
                print_table();
                break;

            case 3:
                printf("Enter ASCII string to display its ASCII codes: ");
                read_line(input, sizeof(input));
                print_ascii_codes(input);
                break;

            case 4:
                free_table();
                printf("Exiting.\n");
                return 0;

            default:
                printf("Please choose 1 through 4.\n");
                break;
        }
    }
}
