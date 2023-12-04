#include <stdio.h>
#include <string.h>
#include <ctype.h>
#include <stdlib.h>

void E();
void EP();
void T();
void TP();
void F();
void increment();
char str[20], ip;
int i = 0;

int main()
{
    printf("Enter the string: ");
    scanf("%s", str);
    str[strlen(str)] = '\0'; // Add null terminator at the end of the string
    ip = str[i];
    E();

    if (ip == '\0')
    {
        printf("String is grammatically correct\n");
    }
    else
    {
        printf("String is not grammatically correct\n");
    }

    return 0;
}

void increment()
{
    ip = str[++i];
    while (ip == ' ')
    {
        ip = str[++i];
    }
}

void E()
{
    T();
    EP();
}

void EP()
{
    if (ip == '+')
    {
        increment();
        T();
        EP();
    }
}

void T()
{
    F();
    TP();
}

void TP()
{
    if (ip == '*')
    {
        increment();
        F();
        TP();
    }
}

void F()
{
    if (ip == '(')
    {
        increment();
        E();
        if (ip == ')')
        {
            increment();
        }
        else
        {
            printf("String is not grammatically correct\n");
            exit(1);
        }
    }
    else if (isalpha(ip))
    {
        increment();
        while (isalpha(ip) || isdigit(ip))
        {
            increment();
        }
    }
    else
    {
        printf("String is not grammatically correct\n");
        exit(1);
    }
}
