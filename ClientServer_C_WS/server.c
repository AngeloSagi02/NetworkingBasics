/*
 * serverChat.c
 *
 *  Modified on: October 28, 2024
 *      Author: Angelo Saginario 
 */
/*#include <sys/types.h>*/
#include <sys/socket.h>
#include <netinet/in.h>
#include <unistd.h>

#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#define EXIT 2
#define PEER 1
#define YOU 0
#define PROTOPORT   5193 /* Default port number */
#define QLEN        6    /* Size of connection requests queue   */

#define MAX(x, y) (((x) > (y)) ? (x) : (y))
char status = PEER;

int main(int argc, char *argv[]) {
  struct  sockaddr_in sad; /* Struct to store the transport address of the server socket */
  struct  sockaddr_in cad; /* Struct to store the transport address of the client socket */
  int     sd, sd2;         /* Socket descriptors */
  int     port; 	   	   /* Port number */
  socklen_t     alen;      /* Lenght of the client transport address  */
  char    buf[1000];       /* Buffer for sending and receiving data */
  int     len, n;      /* Status variable to count the number of connections from clients */
  fd_set readfds;
  int max_sd;

  /* Create a socket for stream oriented communication */
  sd = socket(PF_INET, SOCK_STREAM, 0);
  if (sd < 0) {
      fprintf(stderr, "socket creation failed\n");
      exit(1);
  }
   printf("Socket id: %d\n", sd );
   /* Clean the memory area that will store the transport address of the local socket (server) */
   memset((char *)&sad, 0, sizeof(sad));	
   
   /* Set the transport address of the local socket (server) */
   sad.sin_family = AF_INET; 
   sad.sin_addr.s_addr = htonl(INADDR_ANY); 
	if (argc > 1) { 		
        port = atoi(argv[1]);   	
   } else {
        port = PROTOPORT;       	
   }
   sad.sin_port = htons((u_short)port);



  /* Bind a transport address (sad) to the local socket (sd) */
  if (bind(sd, (struct sockaddr *)&sad, sizeof(sad)) < 0) {
      fprintf(stderr,"bind failed\n");
      exit(1);
   }

  /* Characterize the socket as a welcome socket (server) */
  if (listen(sd, QLEN) < 0) {
      fprintf(stderr,"listen failed\n");
      exit(1);
  }
  
  alen = sizeof(cad);
  
  sd2=accept(sd, (struct sockaddr *)&cad, &alen);
  
  
  do
    {
        FD_ZERO(&readfds);
        FD_SET(sd2, &readfds);
        FD_SET(STDIN_FILENO, &readfds);

        max_sd = MAX(sd2, STDIN_FILENO)+1;

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
            write(sd2, &len, sizeof(int));
            write(sd2, buf, len);
            break;

        case PEER:
            printf("\nPEER>");
            memset(buf, 0, sizeof(buf));
            read(sd2, &len, sizeof(int));

            n = 0;
            while (n < len)
            {
                n += read(sd2, buf + n, sizeof(buf) - n);
            }
            printf("%s\n", buf);
        }
        if (buf[len - 1] == '.')
            status = EXIT;
            
    } while (status != EXIT);

      
      /* Close the connection socket created by accept() */
    close (sd2);
}
