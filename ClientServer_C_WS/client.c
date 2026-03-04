/*
 * clientChat.c
 *
 *  Modified on: October 28, 2024
 *      Author: Angelo Saginario

/*#include <sys/types.h>*/
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <unistd.h>

#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#define EXIT 2
#define PEER 1
#define YOU 0
#define PROTOPORT 5193        /* Default server port number */
#define LOCALHOST "127.0.0.1" /* Default server address */

#define MAX(x, y) (((x) > (y)) ? (x) : (y))
char status = YOU;

int main(int argc, char *argv[])
{
    struct sockaddr_in sad; /* Struct to host the transport address of the remote socket */
    int sd;                 /* Socket descriptor              	  */
    int port;               /* Port number                  		  */
    int n, len;             /* Number of read bytes                */
    char buf[1000];         /* Buffer for data reading and writing */
    char *host;
    fd_set readfds;
    int max_sd;

    /* Create a socket for stream oriented communication */
    sd = socket(PF_INET, SOCK_STREAM, 0);
    if (sd < 0)
    {
        fprintf(stderr, "socket creation failed\n");
        exit(1);
    }

    /* Clean the memory area that will store the transport address of the remote socket (server) */
    memset((char *)&sad, 0, sizeof(sad));

    /* Set the transport address of the remote socket (server)) */
    sad.sin_family = AF_INET;
    if (argc > 2)
    {
        port = atoi(argv[2]);
    }
    else
    {
        port = PROTOPORT;
    }
    if (port > 0)
        sad.sin_port = htons((u_short)port);
    else
    {
        fprintf(stderr, "bad port number %s\n", argv[2]);
        exit(1);
    }

    /* Check for the existence of an IP address on the command line */
    if (argc > 1)
    {
        host = argv[1]; /* if host argument is specified */
    }
    else
    {
        host = LOCALHOST;
    }

    sad.sin_addr.s_addr = inet_addr(host);

    /* Connect the local socket (sd) with the remote one identified by the transport address sad */
    if (connect(sd, (struct sockaddr *)&sad, sizeof(sad)) < 0)
    {
        fprintf(stderr, "connect failed\n");
        exit(1);
    }

    
    do
    {
        FD_ZERO(&readfds);
        FD_SET(sd, &readfds);
        FD_SET(STDIN_FILENO, &readfds);

        max_sd = MAX(sd, STDIN_FILENO)+1;

        int activity = select(max_sd + 1, &readfds, NULL, NULL, NULL);
        if (activity < 0) {
            perror("select");
            exit(1);
        }

        if (FD_ISSET(STDIN_FILENO, &readfds)) status = YOU;
        else status = PEER;

        switch (status)
        {
        case YOU:
            memset(buf, 0, sizeof(buf));

            scanf("\n%[^\n]s", buf);
            len = strlen(buf);
            write(sd, &len, sizeof(int));
            write(sd, buf, len);
            break;

        case PEER:
            printf("\nPEER>");
            memset(buf, 0, sizeof(buf));
            read(sd, &len, sizeof(int));

            n = 0;
            while (n < len)
            {
                n += read(sd, buf + n, sizeof(buf) - n);
            }
            printf("%s\n", buf);
        }
        if (buf[len - 1] == '.')
            status = EXIT;
            
    } while (status != EXIT);

    exit(0);
}