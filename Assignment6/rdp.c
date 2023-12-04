#include <stdio.h>
#include <ctype.h>
#include <string.h>
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
    int op = 0;
    while (op != 1)
    {
        printf("Enter the string: ");
        scanf("%s", str);
        str[strlen(str)] = '\0';
        ip = str[i];
        E();
        if (ip == '\0')
        {
            printf("String is valid\n");
        }
        else
        {
            printf("String is invalid\n");
        }
        printf("Do you want to exit? (1/0): ");
        scanf("%d", &op);
    }
}
void E()
{
    T();
    EP();
}
void increment()
{
    ip = str[++i];
    while (ip == ' ') // Skip over any whitespace
    {
        ip = str[++i];
    }
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
    if (ip == 'i')
    {
        increment();
    }
    else if (ip == '(')
    {
        increment();
        E();
        if (ip == ')')
            increment();
    }
    else if (isalpha(ip))
    {
        increment();
        while (isalpha(ip) || isdigit(ip))
        {
            increment();
        }
    }
}